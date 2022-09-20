package elcom.ex1.librarybooks.service;

import elcom.ex1.librarybooks.entity.library.Book;
import elcom.ex1.librarybooks.entity.library.Borrow;
import elcom.ex1.librarybooks.entity.library.User;

import java.util.Date;
import java.util.List;

public interface BorrowService {

    List<Borrow> findAll();

    Borrow findById(Long id);


    void save(Borrow borrowedList);

    void remove(Borrow borrowedList);

   List<Borrow>findByUserId(User userId);

   List<Borrow>findByBookId(Book bookId);

   Integer borrowAmountInTime(Date startDate, Date endDate);


    Integer maxBookIdInTime(Date startDate, Date endDate);
}
