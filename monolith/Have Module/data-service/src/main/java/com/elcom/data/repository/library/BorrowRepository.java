package com.elcom.data.repository.library;


import com.elcom.data.model.library.Book;
import com.elcom.data.model.library.Borrow;
import com.elcom.data.model.library.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {


    List<Borrow> findByUserId(User userId);


    List<Borrow> findByBookId(Book bookId);

    @Query("select count(a)  from Borrow a where a.borrowedDate between :startDate and :endDate ")
    Integer borrowAmountInTime(@RequestParam Date startDate,@RequestParam Date endDate);

    @Query("select  a.bookId.bookName, count(a.bookId) from Borrow a where a.borrowedDate between  :startDate and :endDate group by a.bookId order by count(a.bookId) DESC")
    List<Object[]> maxBookInTime(@RequestParam Date startDate, @RequestParam Date endDate, Pageable pageable);

    @Query("select a.bookId.bookName, count(a.bookId) as amount from Borrow a where a.borrowedDate between :startDate and :endDate group by a.bookId ")
    List<Object[]> borrowInTime(@RequestParam Date startDate, @RequestParam Date endDate);

}
