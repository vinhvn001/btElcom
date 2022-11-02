package elcom.ex1.librarybooks.controller;


import elcom.ex1.librarybooks.entity.library.Book;
import elcom.ex1.librarybooks.entity.library.Borrow;
import elcom.ex1.librarybooks.entity.library.User;
import elcom.ex1.librarybooks.service.BookService;
import elcom.ex1.librarybooks.service.BorrowService;
import elcom.ex1.librarybooks.utils.JSONConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.ValidationException;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/borrow")
public class BorrowController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BorrowController.class);
    @Autowired
    private final BorrowService borrowService;
    @Autowired
    BookService bookService;



    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Borrow>> findAll(){
        List<Borrow> borrowedLists = borrowService.findAll();
        if(borrowedLists.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(borrowedLists,HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Borrow> getById(@PathVariable Long id) {
        LOGGER.info("id[{}]", id);
        if(id == null || id.equals(0L))
            throw new ValidationException("id không được để trống");
        Borrow borrowList = borrowService.findById(id);
        if(borrowList == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(borrowList,HttpStatus.OK);
    }

    @GetMapping(value = "/findByUserId")
    public ResponseEntity<List<Borrow>> getByUserId(@RequestParam(value = "userId", required = false)@RequestBody  User userId){
        LOGGER.info("userId[{}]", userId);

       List<Borrow> borrowList =  borrowService.findByUserId(userId);
        if(borrowList == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(borrowList,HttpStatus.OK);
    }

    @GetMapping(value= "/findByBookId")
    public ResponseEntity<List<Borrow>> getByBookId(@RequestParam(value = "bookId", required = false) Book bookId){
        LOGGER.info("bookId[{}]", bookId);

        List<Borrow> borrowList =  borrowService.findByBookId(bookId);
        if(borrowList == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(borrowList,HttpStatus.OK);
    }

    @GetMapping(value = "/findBorrowAmountInTime")

    public ResponseEntity<Integer>findBorrowAmountInTime(@RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd")Date startDate,
                                                         @RequestParam(value = "endDate", required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate){
        LOGGER.info("startDate[{}], endDate[{}]", startDate , endDate);

        Integer borrowAmount = borrowService.borrowAmountInTime(startDate, endDate);
        if(borrowAmount == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return  new ResponseEntity<>(borrowAmount, HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<Borrow> create(@RequestBody Borrow borrowedList, UriComponentsBuilder builder){
        LOGGER.info("{}", JSONConverter.toJSON(borrowedList));

        if( borrowedList.getUserId() == null || borrowedList.getBookId() == null || borrowedList.getLimitDate() == null )
            throw new ValidationException("Không được để trống các field");

        Book book = bookService.findById(borrowedList.getBookId().getId());


        if(book.getBookAmount() < 1){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        book.borrowedOne();
        bookService.update(book);

        borrowService.save(borrowedList);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/{id}").buildAndExpand(borrowedList.getId()).toUri());
        return new ResponseEntity<>(borrowedList, HttpStatus.CREATED);
    }

    @PutMapping(value ="/{id}")
    public ResponseEntity<Borrow> update(@PathVariable Long id, @RequestBody Borrow borrowedList ){
        LOGGER.info("id[{}] - {} ", id, JSONConverter.toJSON(borrowedList));

        if (id == null || borrowedList.getBookId() ==null || borrowedList.getUserId()== null
                || borrowedList.getLimitDate() == null)
            throw new ValidationException("Không được để trống field");

        Borrow currentList = borrowService.findById(id);
        if(currentList == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        currentList.setId(borrowedList.getId());
        currentList.setBookId(borrowedList.getBookId());
        currentList.setUserId(borrowedList.getUserId());

        borrowService.save(currentList);
        return new ResponseEntity<>(currentList,HttpStatus.OK);
    }

    @DeleteMapping(value = "/list/{id}")
    public ResponseEntity<Borrow> delete(@PathVariable Long id){

        LOGGER.info("delete() --> id[{}]", id);

        if (id == null || id.equals(0L))
            throw new ValidationException("id không được để trống");

        Borrow borrowedList = borrowService.findById(id);

        if ( borrowedList==null )
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        borrowService.remove(borrowedList);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value="/return/{id}")
    public ResponseEntity<Borrow> returnBorrow(@PathVariable Long id){
        LOGGER.info("return() --> id{{}}", id);

        Borrow borrow = borrowService.findById(id);
        if(borrow == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
        Book book =bookService.findById(borrow.getBookId().getId());

        book.returnOne();
        bookService.update(book);
        Date currentDate = new Date();
        borrow.setReturnDate(currentDate);
        borrowService.save(borrow);

        return new ResponseEntity<>(borrow,HttpStatus.OK );
    }

    @GetMapping(value="/theMostBorrowedBook")
    public ResponseEntity<List<Object[]>> findMostBorrowedBook(@RequestParam(value = "startDate", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd")Date startDate,
                                                                @RequestParam(value = "endDate", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate )
    {
        LOGGER.info("startDate[{}], endDate[{}]", startDate , endDate);

        List<Object[]> response = borrowService.maxBookInTime(startDate, endDate);
        if(response == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value= "/borrowInTime")
    public ResponseEntity<List<Object[]>> borrowInTime(@RequestParam(value = "startDate", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd")Date startDate,
                                                       @RequestParam(value = "endDate", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate)
    {
        LOGGER.info("startDate[{}], endDate[{}]", startDate , endDate);

        List<Object[]> response = borrowService.borrowInTime(startDate, endDate);
        if(response == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }
}
