package sbt2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;
import sbt2.domain.TestDoc;
import sbt2.repository.TestDocRepo;

import java.util.List;

@Service
public class TestDocService {
    @Autowired
    private ElasticsearchOperations elasticsearchTemplate;
    @Autowired
    private TestDocQueryBuilder testDocQueryBuilder;
    @Autowired
    private TestDocRepo repo;

    public List<TestDoc> searchTemplate(String stringToSearch){
        return elasticsearchTemplate.queryForList(testDocQueryBuilder.buildTestDocMultiFieldQuery(stringToSearch), TestDoc.class);
    }
    public List<TestDoc> searchByTitle(String stringToSearch){
        return repo.findByTitle(stringToSearch);
    }

    public void indexTestDoc(TestDoc doc){
        repo.index(doc);
    }
}
