package com.elcom.elastic.repository;


import com.elcom.elastic.model.BookEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookEsRepository extends ElasticsearchRepository<BookEs, Long> {


    List<Object[]> findByName(String name);
}
