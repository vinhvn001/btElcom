package com.elcom.loan.service;



import com.elcom.loan.model.Borrow;

import java.util.Date;
import java.util.List;

public interface BorrowService {

    List<Borrow> findAll();

    Borrow findById(Long id);


    void save(Borrow borrowedList);

    void remove(Borrow borrowedList);

   List<Borrow>findByUsername(String username);

   List<Borrow>findByBookName(String bookName);

   Integer borrowAmountInTime(Date startDate, Date endDate);

   List<Object[]> maxBookInTime(Date startDate, Date endDate);

   List<Object[]> borrowInTime(Date startDate, Date endDate );

}
