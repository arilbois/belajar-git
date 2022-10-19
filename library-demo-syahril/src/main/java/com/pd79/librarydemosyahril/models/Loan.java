package com.pd79.librarydemosyahril.models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
@Table(name = "loan")
public class Loan {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "loan_id", nullable = false)
  private Long loanId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "admin_id")
  private Admin admin;

  @Column(name = "loan_date", nullable = false)
  @Temporal(TemporalType.DATE)
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date loanDate;

  @Column(name = "return_date", nullable = false)
  @Temporal(TemporalType.DATE)
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date returnDate;

  @Column(name = "actual_return_date", nullable = true)
  @Temporal(TemporalType.DATE)
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date actualReturnDate;

  @Column(name = "late_charge_price", nullable = true)
  private BigDecimal lateChargerPrice;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "loan")
  private List<LoanDetail> loanDetails;

  public Loan() {
  }

  public Loan(Long loanId, Member member, Admin admin, Date loanDate, Date returnDate, Date actualReturnDate,
      BigDecimal lateChargerPrice, List<LoanDetail> loanDetails) {
    this.loanId = loanId;
    this.member = member;
    this.admin = admin;
    this.loanDate = loanDate;
    this.returnDate = returnDate;
    this.actualReturnDate = actualReturnDate;
    this.lateChargerPrice = lateChargerPrice;
    this.loanDetails = loanDetails;
  }

  public Long getLoanId() {
    return loanId;
  }

  public void setLoanId(Long loanId) {
    this.loanId = loanId;
  }

  public Member getMember() {
    return member;
  }

  public void setMember(Member member) {
    this.member = member;
  }

  public Admin getAdmin() {
    return admin;
  }

  public void setAdmin(Admin admin) {
    this.admin = admin;
  }

  public Date getLoanDate() {
    return loanDate;
  }

  public void setLoanDate(Date loanDate) {
    this.loanDate = loanDate;
  }

  public Date getReturnDate() {
    return returnDate;
  }

  public void setReturnDate(Date returnDate) {
    this.returnDate = returnDate;
  }

  public Date getActualReturnDate() {
    return actualReturnDate;
  }

  public void setActualReturnDate(Date actualReturnDate) {
    this.actualReturnDate = actualReturnDate;
  }

  public BigDecimal getLateChargerPrice() {
    return lateChargerPrice;
  }

  public void setLateChargerPrice(BigDecimal lateChargerPrice) {
    this.lateChargerPrice = lateChargerPrice;
  }

  public List<LoanDetail> getLoanDetails() {
    return loanDetails;
  }

  public void setLoanDetails(List<LoanDetail> loanDetails) {
    this.loanDetails = loanDetails;
  }

}
