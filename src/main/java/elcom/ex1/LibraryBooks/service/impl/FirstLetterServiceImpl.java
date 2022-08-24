package elcom.ex1.LibraryBooks.service.impl;

import elcom.ex1.LibraryBooks.entity.FirstLetter;
import elcom.ex1.LibraryBooks.repository.FirstLetterRepository;
import elcom.ex1.LibraryBooks.service.FirstLetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirstLetterServiceImpl implements FirstLetterService {


    @Autowired
    FirstLetterRepository firstLetterRepository;
    @Override
    public FirstLetter findById(Long ID) {

        return firstLetterRepository.findById(ID).orElse(null);
    }

    @Override
    public FirstLetter create(FirstLetter firstLetter) {
        if(firstLetter.getID() == null){
            return null;
        }
        if(firstLetter.getFirstLetter() ==null){
            return null;
        }
        return firstLetterRepository.save(firstLetter);
    }

    @Override
    public FirstLetter update(Long ID, FirstLetter firstLetter) {
        FirstLetter fromDB = firstLetterRepository.findById(ID).orElse(null);
        if(fromDB == null){
            return null;
        }
        else{
            fromDB.setID(fromDB.getID());
            fromDB.setFirstLetter(fromDB.getFirstLetter());
            return firstLetterRepository.save(fromDB);
        }
    }

    @Override
    public void delete(Long ID) {
        firstLetterRepository.deleteById(ID);
    }

    @Override
    public Iterable<FirstLetter> findAll() {
        return firstLetterRepository.findAll();
    }
}
