package validation;

import entity.User;
import utils.StringUtil;

import javax.validation.ValidationException;


public class    UserValidation extends AbstractValidation {
    public void validateLogin(String userName, String password) throws ValidationException {

        if( StringUtil.isNullOrEmpty(userName) )
            getMessageDes().add("userName không được để trống");

        if( StringUtil.isNullOrEmpty(password) )
            getMessageDes().add("password không được để trống");

        /**/
        if (!isValid())
            throw new ValidationException(this.buildValidationMessage());
    }

    public void validateUpsertUser(User user, String actionType) throws ValidationException {

        if (user == null) {
            getMessageDes().add("user không hợp lệ");
            throw new ValidationException(this.buildValidationMessage());
        }

        if( "INSERT".equals(actionType) && StringUtil.isNullOrEmpty(user.getUserName()) )
            getMessageDes().add("userName không được để trống");

        if( StringUtil.isNullOrEmpty(user.getPassword()) )
            getMessageDes().add("password không được để trống");

        if( StringUtil.isNullOrEmpty(user.getFullName()) )
            getMessageDes().add("fullName không được để trống");

        if( StringUtil.isNullOrEmpty(user.getRoleName()))
            getMessageDes().add("roleName không được để trống");

        /**/
        if (!isValid())
            throw new ValidationException(this.buildValidationMessage());
    }

}
