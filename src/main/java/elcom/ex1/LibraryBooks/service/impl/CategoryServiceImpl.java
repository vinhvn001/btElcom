package elcom.ex1.LibraryBooks.service.impl;

import elcom.ex1.LibraryBooks.entity.Category;
import elcom.ex1.LibraryBooks.repository.CategoryRepository;
import elcom.ex1.LibraryBooks.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category findById(Long ID) {
        return categoryRepository.findById(ID).orElse(null);
    }

    @Override
    public Category create(Category category) {
        if(category.getID() == null ){
            return null;
        }
        if(category.getCategoryName() == null){
            return null;
        }
        return  categoryRepository.save(category);
    }

    @Override
    public Category update(Long ID, Category category) {
        Category fromDB = categoryRepository.findById(ID).orElse(null);
        if (fromDB == null){
            return null;
        }
        else{
            fromDB.setID(category.getID());
            fromDB.setCategoryName(category.getCategoryName());
            return categoryRepository.save(fromDB);
        }
    }

    @Override
    public void delete(Long ID) {
        categoryRepository.deleteById(ID);
    }

    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }
}
