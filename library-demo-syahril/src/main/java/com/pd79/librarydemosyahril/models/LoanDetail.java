package com.pd79.librarydemosyahril.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "loan_detail")
public class LoanDetail {
  @EmbeddedId
  private LoanDetailKey loanKey;

  @ManyToOne
  @MapsId("loan_id")
  @JoinColumn(name = "loan_id")
  private Loan loan;

  @ManyToOne
  @MapsId("book_id")
  @JoinColumn(name = "book_id")
  private Book book;

  @Column(name = "book_quantity", nullable = false)
  private int quantityBook;

  @Column(name = "loan_price_per_day", nullable = false)
  private BigDecimal loanPricePerDay;

  public LoanDetail() {
  }

  public LoanDetail(LoanDetailKey loanKey, Loan loan, Book book, int quantityBook, BigDecimal loanPricePerDay) {
    this.loanKey = loanKey;
    this.loan = loan;
    this.book = book;
    this.quantityBook = quantityBook;
    this.loanPricePerDay = loanPricePerDay;
  }

  public LoanDetailKey getLoanKey() {
    return loanKey;
  }

  public void setLoanKey(LoanDetailKey loanKey) {
    this.loanKey = loanKey;
  }

  public Loan getLoan() {
    return loan;
  }

  public void setLoan(Loan loan) {
    this.loan = loan;
  }

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }

  public int getQuantityBook() {
    return quantityBook;
  }

  public void setQuantityBook(int quantityBook) {
    this.quantityBook = quantityBook;
  }

  public BigDecimal getLoanPricePerDay() {
    return loanPricePerDay;
  }

  public void setLoanPricePerDay(BigDecimal loanPricePerDay) {
    this.loanPricePerDay = loanPricePerDay;
  }

}
