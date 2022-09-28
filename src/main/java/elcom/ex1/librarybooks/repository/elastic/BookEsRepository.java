package elcom.ex1.librarybooks.repository.elastic;

import elcom.ex1.librarybooks.entity.elastic.BookEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface BookEsRepository extends ElasticsearchRepository<BookEs, Long> {
    List<BookEs> findByName(String name);
}
