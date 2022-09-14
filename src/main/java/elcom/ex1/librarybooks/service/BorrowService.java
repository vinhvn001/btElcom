package elcom.ex1.librarybooks.service;

import elcom.ex1.librarybooks.entity.library.Borrow;

import java.util.List;

public interface BorrowService {

    List<Borrow> findAll();

    Borrow findById(Long id);

    List<Borrow>findByBorrowerId(Long borrowerId);

    void save(Borrow borrowedList);

    void remove(Borrow borrowedList);

}
