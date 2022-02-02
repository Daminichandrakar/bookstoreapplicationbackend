package com.bridgelabz.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    @NotNull

//    @Length(min = 2, max = 50, message = "Invalid Book Name")
    public String bookName;
//    @NotNull
//    @Pattern(regexp = "^[a-zA-Z]+[ ]*[a-zA-Z]*$")
    public String authorName;
//    @NotNull
    public double bookPrice;
//    @NotNull
    public double noOfCopies;

    @Column(length = 15000)
    public String bookDetail;
    public String bookImageSrc;
//    @NotNull
//    @Range(min = 1000, max = 2021, message = "Invalid Publishing Year")
    public int publishingYear;
}
