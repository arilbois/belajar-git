package com.pd79.librarydemosyahril.dtos;

import java.math.BigDecimal;
import java.util.Date;

public class BookDTO {
  private Long bookId;
  private String bookTitle;
  private PublisherDTO publisher;
  private AuthorDTO author;
  private Date releaseDate;
  private Integer quantityBook;
  private BigDecimal loanPriceBook;

  public BookDTO() {
  }

  public BookDTO(Long bookId, String bookTitle, PublisherDTO publisher, AuthorDTO author, Date releaseDate,
      Integer quantityBook, BigDecimal loanPriceBook) {
    this.bookId = bookId;
    this.bookTitle = bookTitle;
    this.publisher = publisher;
    this.author = author;
    this.releaseDate = releaseDate;
    this.quantityBook = quantityBook;
    this.loanPriceBook = loanPriceBook;
  }

  public Long getBookId() {
    return bookId;
  }

  public void setBookId(Long bookId) {
    this.bookId = bookId;
  }

  public String getBookTitle() {
    return bookTitle;
  }

  public void setBookTitle(String bookTitle) {
    this.bookTitle = bookTitle;
  }

  public PublisherDTO getPublisher() {
    return publisher;
  }

  public void setPublisher(PublisherDTO publisher) {
    this.publisher = publisher;
  }

  public AuthorDTO getAuthor() {
    return author;
  }

  public void setAuthor(AuthorDTO author) {
    this.author = author;
  }

  public Date getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(Date releaseDate) {
    this.releaseDate = releaseDate;
  }

  public Integer getQuantityBook() {
    return quantityBook;
  }

  public void setQuantityBook(Integer quantityBook) {
    this.quantityBook = quantityBook;
  }

  public BigDecimal getLoanPriceBook() {
    return loanPriceBook;
  }

  public void setLoanPriceBook(BigDecimal loanPriceBook) {
    this.loanPriceBook = loanPriceBook;
  }

}
