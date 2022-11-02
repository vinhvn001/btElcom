package com.elcom.data.repository.log;


import com.elcom.data.model.log.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolClassRepository extends JpaRepository<SchoolClass,Long> {
    SchoolClass findByName(String name);
}
