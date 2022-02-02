package com.bridgelabz.bookstore.dto;

import com.bridgelabz.bookstore.model.BookDetails;

public class ResponseDTO {

    public int orderBookDetail;
    public String message;
    public BookDetails bookDetails;

//    private String message;
    private Object data;

    public ResponseDTO(String message, Object data) {
        super();
        this.message = message;
        this.data = data;
    }

    public ResponseDTO(String message, BookDetails bookDetails) {
        this.message = message;
        this.bookDetails = bookDetails;
    }

    public ResponseDTO(String message, int orderBookDetail) {
        this.message=message;
        this.orderBookDetail=orderBookDetail;
    }
}
