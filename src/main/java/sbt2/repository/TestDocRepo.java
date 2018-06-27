package sbt2.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import sbt2.domain.TestDoc;

import java.util.List;

public interface TestDocRepo extends ElasticsearchRepository<TestDoc, String> {
    List<TestDoc> findByTitle(String stringToSearch);
}
