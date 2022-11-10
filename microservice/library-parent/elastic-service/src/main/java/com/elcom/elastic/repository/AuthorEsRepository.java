package com.elcom.elastic.repository;

import com.elcom.elastic.model.AuthorEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface AuthorEsRepository extends ElasticsearchRepository<AuthorEs,Long> {

    AuthorEs findByName(String name);
}
