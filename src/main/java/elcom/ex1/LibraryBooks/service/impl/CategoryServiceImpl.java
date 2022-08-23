package elcom.ex1.LibraryBooks.service.impl;

import elcom.ex1.LibraryBooks.entity.CATEGORY;
import elcom.ex1.LibraryBooks.repository.CATEGORYRepository;
import elcom.ex1.LibraryBooks.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CATEGORYRepository categoryRepository;

    @Autowired
    public

    @Override
    public CATEGORY findById(Long ID) {
        return null;
    }

    @Override
    public CATEGORY create(CATEGORY category) {
        if(category.getID() == null ){
            return null;
        }
        if(category.getCategory() == null){
            return null;
        }
        return  categoryRepository.save(category);
    }

    @Override
    public CATEGORY update(Long ID, CATEGORY category) {
        return null;
    }

    @Override
    public void delete(Long ID) {

    }

    @Override
    public Iterable<CATEGORY> findAll() {
        return null;
    }
}
