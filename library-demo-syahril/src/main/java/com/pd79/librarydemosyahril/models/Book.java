package com.pd79.librarydemosyahril.models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "book")
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long bookId;

  @Column(name = "book_title", nullable = false)
  private String bookTitle;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "publisher_id")
  private Publisher publisher;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "author_id")
  private Author author;

  @Column(name = "release_date", nullable = false)
  @Temporal(TemporalType.DATE)
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date releaseDate;

  @Column(name = "book_quantity", nullable = false)
  private int quantityBook;

  @Column(name = "book_loan_price", nullable = false)
  private BigDecimal loanPriceBook;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "book")
  private List<LoanDetail> loanDetails;

  public Book() {
  }

  public Book(Long bookId, String bookTitle, Publisher publisher, Author author, Date releaseDate, int quantityBook,
      BigDecimal loanPriceBook, List<LoanDetail> loanDetails) {
    this.bookId = bookId;
    this.bookTitle = bookTitle;
    this.publisher = publisher;
    this.author = author;
    this.releaseDate = releaseDate;
    this.quantityBook = quantityBook;
    this.loanPriceBook = loanPriceBook;
    this.loanDetails = loanDetails;
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

  public Publisher getPublisher() {
    return publisher;
  }

  public void setPublisher(Publisher publisher) {
    this.publisher = publisher;
  }

  public Author getAuthor() {
    return author;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }

  public Date getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(Date releaseDate) {
    this.releaseDate = releaseDate;
  }

  public int getQuantityBook() {
    return quantityBook;
  }

  public void setQuantityBook(int quantityBook) {
    this.quantityBook = quantityBook;
  }

  public BigDecimal getLoanPriceBook() {
    return loanPriceBook;
  }

  public void setLoanPriceBook(BigDecimal loanPriceBook) {
    this.loanPriceBook = loanPriceBook;
  }

  public List<LoanDetail> getLoanDetails() {
    return loanDetails;
  }

  public void setLoanDetails(List<LoanDetail> loanDetails) {
    this.loanDetails = loanDetails;
  }

}
