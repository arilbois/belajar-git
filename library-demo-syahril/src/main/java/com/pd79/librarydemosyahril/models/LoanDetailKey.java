package com.pd79.librarydemosyahril.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class LoanDetailKey implements Serializable {
  @Column(name = "loan_id")
  private Long loanId;
  @Column(name = "book_id")
  private Long bookId;

  public LoanDetailKey() {
  }

  public LoanDetailKey(Long loanId, Long bookId) {
    this.loanId = loanId;
    this.bookId = bookId;
  }

  public Long getLoanId() {
    return loanId;
  }

  public void setLoanId(Long loanId) {
    this.loanId = loanId;
  }

  public Long getBookId() {
    return bookId;
  }

  public void setBookId(Long bookId) {
    this.bookId = bookId;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((loanId == null) ? 0 : loanId.hashCode());
    result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    LoanDetailKey other = (LoanDetailKey) obj;
    if (loanId == null) {
      if (other.loanId != null)
        return false;
    } else if (!loanId.equals(other.loanId))
      return false;
    if (bookId == null) {
      if (other.bookId != null)
        return false;
    } else if (!bookId.equals(other.bookId))
      return false;
    return true;
  }

}
