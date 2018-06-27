package sbt2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring5.expression.Mvc;
import sbt2.domain.TestDoc;
import sbt2.domain.web.SearchForm;
import sbt2.service.TestDocService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/td/")
public class TestDocController {
    public static final Logger LOG = LoggerFactory.getLogger(TestDocController.class);

    @Autowired
    private TestDocService service;
    @Autowired
    private Mvc mvc;

    private SearchForm searchForm = new SearchForm();
    @ModelAttribute("searchForm")
    public SearchForm initializeSearchForm(){
        return new SearchForm(searchForm);
    }

    @GetMapping("s")
    public String getSearchForm(@ModelAttribute("searchForm")SearchForm searchForm, Map<String, Object> model){
        model.put("searchForm", searchForm);
        return "search";
    }
    @PostMapping("s")
    public String postSearchForm(@ModelAttribute("searchForm") SearchForm searchForm, Map<String, Object> model){
        LOG.debug("String to search: " + searchForm.getInput());
//        List<TestDoc> resultList = service.searchTemplate(searchForm.getInput());
        List<TestDoc> resultList = service.searchByTitle(searchForm.getInput());
        LOG.debug("resultList: " + resultList.toString());
        model.put("resultList", resultList);
        return "results";
    }

    @GetMapping("i")
    public String getIndexForm(Map<String, Object> model){
        model.put("newDocForm", new TestDoc());
        return "index";
    }
    @PostMapping("i")
    public String postIndexForm(@ModelAttribute("newDocForm") TestDoc doc){
        doc.setDate();
        LOG.debug("Doc to index: \n" + doc.toString());
        service.indexTestDoc(doc);
        return "redirect:" + mvc.url("TDC#getSearchForm").build();
    }
}
