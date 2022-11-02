package elcom.ex1.librarybooks.service;

import elcom.ex1.librarybooks.entity.log.SchoolClass;

import java.util.List;

public interface SchoolClassService {

    SchoolClass findById(Long id);

    SchoolClass findByName(String name);

    SchoolClass create(SchoolClass schoolClass);

    SchoolClass update(SchoolClass schoolClass);

    void delete(Long id);

    List<SchoolClass> findAll();
}
