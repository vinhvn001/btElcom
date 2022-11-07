package com.elcom.library.repository;

import com.elcom.library.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

        Author findByAuthorName(String authorName);

}
