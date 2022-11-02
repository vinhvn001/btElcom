package com.elcom.data.service;



import com.elcom.data.model.library.Book;
import com.elcom.data.model.library.Borrow;
import com.elcom.data.model.library.User;

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

   List<Object[]> maxBookInTime(Date startDate, Date endDate);

   List<Object[]> borrowInTime(Date startDate, Date endDate );

}
