package com.elcom.loan.repository;



import com.elcom.loan.model.Borrow;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {


    List<Borrow> findByUsername(String username);

    List<Borrow> findByBookName(String  bookName);

/*    @Query("select count(a)  from Borrow a where a.borrowedDate between :startDate and :endDate ")
    Integer borrowAmountInTime(@RequestParam Date startDate,@RequestParam Date endDate);*/

    @Query("select  a.bookName, count(a.bookName) as amount from Borrow a where a.borrowedDate between  :startDate and :endDate group by a.bookName order by count(a.bookName) DESC")
    List<Object[]> maxBookInTime( Date startDate,  Date endDate, Pageable pageable);

    @Query("select a.bookName, count(a.bookName) as amount from Borrow a where a.borrowedDate between :startDate and :endDate group by a.bookName ")
    List<Object[]> borrowInTime( Date startDate, Date endDate);

    @Query("select a.username, a.bookName, a.limitDate from Borrow a where CURRENT_DATE > a.limitDate")
    List<Object[]> expiredBorrow();


}
