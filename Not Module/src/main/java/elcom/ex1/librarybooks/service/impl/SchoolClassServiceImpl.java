package elcom.ex1.librarybooks.service.impl;

import elcom.ex1.librarybooks.entity.log.SchoolClass;
import elcom.ex1.librarybooks.repository.log.SchoolClassRepository;
import elcom.ex1.librarybooks.service.SchoolClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;

@Service
public class SchoolClassServiceImpl implements SchoolClassService {

    private final SchoolClassRepository schoolClassRepository;

    @Autowired
    public SchoolClassServiceImpl(SchoolClassRepository schoolClassRepository) {
        this.schoolClassRepository = schoolClassRepository;
    }

    @Override
    public SchoolClass findById(Long id) {
        if(schoolClassRepository.findById(id) == null)
            throw new ValidationException("id không tồn tại");
        return schoolClassRepository.findById(id).orElse(null);
    }

    @Override
    public SchoolClass findByName(String name) {
        if(schoolClassRepository.findByName(name) == null)
            throw new ValidationException("Lớp không tồn tại");
        return schoolClassRepository.findByName(name);
    }

    @Override
    public SchoolClass create(SchoolClass schoolClass) {
        if(schoolClass.getName() == null || schoolClass.getStudentAmount() == null)
            throw new ValidationException("Field không được để trống");

        return schoolClassRepository.save(schoolClass);
    }

    @Override
    public SchoolClass update(SchoolClass schoolClass) {
        if(schoolClassRepository.findById(schoolClass.getId()) == null)
            throw new ValidationException("Id không tồn tại ");

        return schoolClassRepository.save(schoolClass);
    }

    @Override
    public void delete(Long id) {
        if(schoolClassRepository.findById(id) == null)
            throw new ValidationException("Id không tồn tại ");
        schoolClassRepository.deleteById(id);
    }

    @Override
    public List<SchoolClass> findAll() {
        return schoolClassRepository.findAll();
    }
}
