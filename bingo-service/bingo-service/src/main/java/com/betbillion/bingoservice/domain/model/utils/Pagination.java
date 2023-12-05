package com.betbillion.bingoservice.domain.model.utils;

import lombok.Data;

@Data
public class Pagination {
    private String search;
    private Long offset;
    private Long limit;
    private Long totalPages;
    private Long totalElements;
    private Long numberPage;

    public Pagination(String search, Long offset, Long limit, Long totalElements, Long numberPage) {
        this.search = search;
        this.offset = offset;
        this.limit = limit;
        this.totalElements = totalElements;
        this.totalPages = (long) Math.ceil((double) totalElements / limit);
        this.numberPage = numberPage;
    }

    public Pagination(String search, Long offset, Long limit, Long numberPage) {
        this.search = search;
        this.offset = offset;
        this.limit = limit;
        this.totalPages = (long) Math.ceil((double) offset / limit);
        this.numberPage = numberPage;
    }

    public Pagination pagination(Long totalPages) {
        return new Pagination(this.getSearch(), this.getOffset(), this.getLimit(), totalPages, this.numberPage);
    }

    public Pagination nextPage() {
        if (numberPage < totalPages) {
            return new Pagination(this.getSearch(), this.getOffset() + this.getLimit(), this.getLimit(), this.totalElements, this.numberPage + 1);
        }
        return this;
    }

    public Pagination previousPage() {
        if (numberPage > 1) {
            return new Pagination(this.getSearch(), this.getOffset() - this.getLimit(), this.getLimit(), this.totalElements, this.numberPage - 1);
        }
        return this;
    }

    public Pagination goToPage(Long pageNumber) {
        if (pageNumber >= 1 && pageNumber <= totalPages) {
            return new Pagination(this.getSearch(), (pageNumber - 1) * this.getLimit(), this.getLimit(), this.totalElements, pageNumber);
        }
        return this;
    }
}
