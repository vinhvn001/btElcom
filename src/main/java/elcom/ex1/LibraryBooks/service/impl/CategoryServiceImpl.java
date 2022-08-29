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
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category create(Category category) {
        if(category.getId() == null ){
            return null;
        }
        if(category.getCategoryName() == null){
            return null;
        }
        return  categoryRepository.save(category);
    }

    @Override
    public Category update(Long id, Category category) {
        Category fromDB = categoryRepository.findById(id).orElse(null);
        if (fromDB == null){
            return null;
        }
        else{
            fromDB.setId(category.getId());
            fromDB.setCategoryName(category.getCategoryName());
            return categoryRepository.save(fromDB);
        }
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }
}
