package com.elcom.id.controller;



import com.elcom.constant.ResourcePath;
import com.elcom.id.auth.CustomUserDetails;
import com.elcom.id.auth.jwt.JwtTokenProvider;
import com.elcom.id.messaging.rabbitmq.RabbitMQClient;
import com.elcom.id.model.User;
import com.elcom.id.service.AuthService;
import com.elcom.id.service.UserService;
import com.elcom.id.utils.JWTutils;
import com.elcom.id.validation.UserValidation;
import com.elcom.message.MessageContent;
import com.elcom.message.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import com.elcom.id.model.dto.AuthorizationResponseDTO;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;


@RestController
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);


    @Autowired
    private RabbitMQClient rabbitMQClient;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Autowired
    AuthenticationManager authenticationManager;

    //Login
    @RequestMapping(value ="/user/login", method = RequestMethod.POST)
    public ResponseMessage userLogin( @RequestBody Map<String, Object> bodyParam) {
        ResponseMessage response = null;
        if (bodyParam == null || bodyParam.isEmpty()) {
            response = new ResponseMessage(HttpStatus.BAD_REQUEST.value(), "Invalid param value",
                    new MessageContent(HttpStatus.BAD_REQUEST.value(), "Invalid param value", null));
        } else {
            String username = (String) bodyParam.get("username");
            String password = (String) bodyParam.get("password");

            String invalidData = new UserValidation().validateLogin(username, password);
            if (invalidData != null) {
                response = new ResponseMessage(HttpStatus.BAD_REQUEST.value(), invalidData,
                        new MessageContent(HttpStatus.BAD_REQUEST.value(), invalidData, null));
            } else {
                // Check exist account with user_name
                User existUser = userService.findByUsername(username);
                if (existUser == null) {
                    invalidData = "Tài khoản không tồn tại";
                    return new ResponseMessage(HttpStatus.NOT_FOUND.value(), invalidData,
                            new MessageContent(HttpStatus.NOT_FOUND.value(), invalidData, null));
                } else {

                    // Xác thực thông tin người dùng Request lên, nếu không xảy ra exception tức là thông tin hợp lệ
                    Authentication authentication = null;
                    try {
                        authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
                    } catch (AuthenticationException ex) {
                        LOGGER.error(ex.toString());
                        invalidData = "Tài khoản hoặc mật khẩu không đúng";
                        return new ResponseMessage(HttpStatus.UNAUTHORIZED.value(), invalidData,
                                new MessageContent(HttpStatus.UNAUTHORIZED.value(), invalidData, null));
                    }
                    CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
                        SecurityContextHolder.getContext().setAuthentication(authentication);

                        // Trả về jwt cho người dùng.
                        String accessJwt = tokenProvider.generateToken(userDetails);
                        String refreshJwt = JWTutils.createToken(userDetails.getUser().getId().toString());
                        //LoginResponse loginResponse = new LoginResponse(accessJwt, refreshJwt);
                        AuthorizationResponseDTO responseDTO = new AuthorizationResponseDTO(userDetails, accessJwt, refreshJwt);
                        response = new ResponseMessage(new MessageContent(responseDTO));

                }
            }
        }
        return response;
    }

    //Authentication
    public ResponseMessage authorized(String requestPath, Map<String, String> headerParam) {
        ResponseMessage response = null;
        if (headerParam == null || headerParam.isEmpty()) {
            response = new ResponseMessage(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase(),
                    new MessageContent(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase(), null));
        } else {
            String bearerToken = headerParam.get("authorization");
            // Kiểm tra xem header Authorization có chứa thông tin jwt không
            if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
                try {
                    long start = System.currentTimeMillis();
                    String jwt = bearerToken.substring(7);
                    LOGGER.info("jwt => " + jwt);
                    Long id = tokenProvider.getUserIdFromJWT(jwt);
                    long end = System.currentTimeMillis();
                    LOGGER.info("Parse token in: {} ms", (end-start), jwt);
                    UserDetails userDetails = authService.loadUserById(id);
                    end = System.currentTimeMillis();
                    LOGGER.info("Get UserDetails in: {} ms", (end-start), jwt);
                    if (userDetails != null) {
                        User user = ((CustomUserDetails) userDetails).getUser();
                        
                            UsernamePasswordAuthenticationToken authentication
                                    = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                            end = System.currentTimeMillis();
                            LOGGER.info("Save authentication for Spring security in: {} ms", (end-start), jwt);
                            AuthorizationResponseDTO responseDTO = new AuthorizationResponseDTO((CustomUserDetails) authentication.getPrincipal(), null, null);
                            end = System.currentTimeMillis();
                            LOGGER.info("Authen JWT Token in: {}ms for {}", (end-start), jwt);
                            response = new ResponseMessage(new MessageContent(responseDTO));

                    } else {
                        response = new ResponseMessage(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(),
                                new MessageContent(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), null));
                    }
                } catch (Exception ex) {
                    LOGGER.error("failed on set user authentication", ex);
                    response = new ResponseMessage(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase(),
                            new MessageContent(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase(), null));
                }
            } else {
                response = new ResponseMessage(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase(),
                        new MessageContent(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase(), null));
            }
        }
        return response;
    }

}
