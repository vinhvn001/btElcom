package com.elcom.elastic.repository;

import com.elcom.elastic.model.CategoryEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CategoryEsRepository extends ElasticsearchRepository<CategoryEs,Long> {

    CategoryEs findByname(String name);
}
