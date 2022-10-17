package com.elcom.data.service.impl;


import com.elcom.data.model.library.Book;
import com.elcom.data.model.library.Borrow;
import com.elcom.data.model.library.User;
import com.elcom.data.repository.library.BorrowRepository;
import com.elcom.data.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    public List<Borrow> findByBookId(Book bookId) {
        return borrowRepository.findByBookId(bookId);
    }

    @Override
    public List<Borrow> findByUserId(User userId) {

        return borrowRepository.findByUserId(userId);
    }

    @Override
    public Integer borrowAmountInTime(Date startDate, Date endDate) {
        return borrowRepository.borrowAmountInTime(startDate, endDate);
    }

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
