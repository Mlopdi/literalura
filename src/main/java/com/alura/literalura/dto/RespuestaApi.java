package com.alura.literalura.dto;

import java.util.List;

public class RespuestaApi {
    private Integer count;
    private String next;
    private String previous;
    private List<LibroApiDto> results;
    
    public RespuestaApi(Integer count, String next, String previous, List<LibroApiDto> results) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<LibroApiDto> getResults() {
        return results;
    }

    public void setResults(List<LibroApiDto> results) {
        this.results = results;
    }

    
}
