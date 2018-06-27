package sbt2.domain.web;

public class SearchForm {
    private String input;

    public SearchForm(String input) {
        this.input = input;
    }

    public SearchForm(SearchForm other) {
        this.input = other.input;
    }

    public SearchForm() {
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}
