package sbt2.domain;

import org.elasticsearch.search.suggest.Suggest;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Document(indexName = "testdocuments", type = "testdocument", shards = 1, replicas = 0)
public class TestDoc {
    @Id
    private String Id;
    private String title;
    private String body;
    @Field(type = FieldType.Date)
    private Date date;
//    private Suggest

    public TestDoc() {
    }

    public TestDoc(String title, String body, Date date) {
        this.title = title;
        this.body = body;
        this.date = date;
    }

    public TestDoc(String id, String title, String body, Date date) {
        Id = id;
        this.title = title;
        this.body = body;
        this.date = date;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public void setDate() {
        this.date = new Date();
    }

    @Override
    public String toString() {
        return "TestDoc{" +
                "Id='" + Id + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
