package com.elcom.loan.service.impl;



import com.elcom.loan.model.Borrow;
import com.elcom.loan.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.elcom.loan.repository.BorrowRepository;

import java.util.Date;
import java.util.List;

@Service
public class BorrowServiceImpl implements BorrowService {

    @Autowired
    private BorrowRepository borrowRepository;


    @Override
    public List<Borrow> findAll() {
        return borrowRepository.findAll();
    }

    @Override
    public Borrow findById(Long id) {
        return borrowRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Borrow borrowedList) {
         borrowRepository.save(borrowedList);
    }

    @Override
    public void remove(Borrow borrowedList) {
        borrowRepository.delete(borrowedList);
    }

    @Override
    public List<Borrow> findByBookName(String bookName) {
        return borrowRepository.findByBookName(bookName);
    }

    @Override
    public Integer borrowAmountInTime(Date startDate, Date endDate) {
        return null;
    }



    @Override
    public List<Borrow> findByUsername(String username) {

        return borrowRepository.findByUsername(username);
    }

//    @Override
//    public Integer borrowAmountInTime(Date startDate, Date endDate) {
//        return borrowRepository.borrowAmountInTime(startDate, endDate);
//    }
//
    @Override
    public List<Object[]> maxBookInTime(Date startDate, Date endDate) {
        Pageable paging =  PageRequest.of(0,1);
         return borrowRepository.maxBookInTime(startDate, endDate,paging);
    }

    @Override
    public List<Object[]> borrowInTime(Date startDate, Date endDate){
        return borrowRepository.borrowInTime(startDate, endDate);
    }
}
