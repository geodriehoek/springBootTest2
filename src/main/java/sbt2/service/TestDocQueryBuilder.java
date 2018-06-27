package sbt2.service;

import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

@Service
public class TestDocQueryBuilder {
    public static final Logger LOG = LoggerFactory.getLogger(TestDocQueryBuilder.class);

    public SearchQuery buildTestDocMultiFieldQuery(String stringToSearch){
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(
                        QueryBuilders
                        .boolQuery()
                        .should(
                                QueryBuilders
                                .matchQuery(
                                         "title", stringToSearch
                                )
                        )
                        .should(
                                QueryBuilders
                                .matchQuery(
                                        "body", stringToSearch
                                )
                        )
                )
                .build();

        LOG.debug("Processing Query:\n" + searchQuery.getQuery().toString());
        return searchQuery;
    }
}
