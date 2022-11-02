package com.elcom.data.service;



import com.elcom.data.model.log.SchoolClass;

import java.util.List;

public interface SchoolClassService {

    SchoolClass findById(Long id);


    SchoolClass create(SchoolClass schoolClass);

    SchoolClass update(SchoolClass schoolClass);

    void delete(Long id);

    List<SchoolClass> findAll();
}
