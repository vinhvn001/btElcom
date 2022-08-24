package elcom.ex1.LibraryBooks.service;

import elcom.ex1.LibraryBooks.entity.FirstLetter;

public interface FirstLetterService {

    FirstLetter findById(Long ID);

    FirstLetter create(FirstLetter firstLetter);

    FirstLetter update(Long ID, FirstLetter firstLetter);

    void delete(Long ID);

    Iterable<FirstLetter> findAll();
}
