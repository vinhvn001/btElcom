package elcom.ex1.librarybooks.service.impl;

import elcom.ex1.librarybooks.entity.library.Borrow;
import elcom.ex1.librarybooks.repository.library.BorrowRepository;
import elcom.ex1.librarybooks.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowServiceImpl implements BorrowService {

    @Autowired
    private BorrowRepository borrowRepository;

   /* @Autowired
    public BorrowedListServiceImpl(BorrowedListRepository borrowedListRepository) {
        this.borrowedListRepository = borrowedListRepository;
    }*/

    @Override
    public List<Borrow> findAll() {
        return borrowRepository.findAll();
    }

    @Override
    public Borrow findById(Long id) {
        return borrowRepository.findById(id).orElse(null);
    }

    @Override
    public List<Borrow> findByBorrowerId(Long borrowerId) {
        return borrowRepository.findByBorrowerId(borrowerId);
    }

    @Override
    public void save(Borrow borrowedList) {
         borrowRepository.save(borrowedList);
    }

    @Override
    public void remove(Borrow borrowedList) {
        borrowRepository.delete(borrowedList);
    }
}
