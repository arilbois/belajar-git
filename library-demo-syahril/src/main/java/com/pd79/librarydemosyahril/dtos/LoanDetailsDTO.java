package com.pd79.librarydemosyahril.dtos;

import java.math.BigDecimal;

public class LoanDetailsDTO {
  private LoanDTO loan;
  private BookDTO book;
  private Integer quantityBook;
  private BigDecimal loanPricePerDay;

  public LoanDetailsDTO() {
  }

  public LoanDetailsDTO(LoanDTO loan, BookDTO book, Integer quantityBook, BigDecimal loanPricePerDay) {
    this.loan = loan;
    this.book = book;
    this.quantityBook = quantityBook;
    this.loanPricePerDay = loanPricePerDay;
  }

  public LoanDTO getLoan() {
    return loan;
  }

  public void setLoan(LoanDTO loan) {
    this.loan = loan;
  }

  public BookDTO getBook() {
    return book;
  }

  public void setBook(BookDTO book) {
    this.book = book;
  }

  public Integer getQuantityBook() {
    return quantityBook;
  }

  public void setQuantityBook(Integer quantityBook) {
    this.quantityBook = quantityBook;
  }

  public BigDecimal getLoanPricePerDay() {
    return loanPricePerDay;
  }

  public void setLoanPricePerDay(BigDecimal loanPricePerDay) {
    this.loanPricePerDay = loanPricePerDay;
  }

}
