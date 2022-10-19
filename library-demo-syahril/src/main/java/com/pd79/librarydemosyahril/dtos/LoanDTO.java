package com.pd79.librarydemosyahril.dtos;

import java.math.BigDecimal;
import java.util.Date;

public class LoanDTO {
  private Long loanId;
  private MemberDTO member;
  private AdminDTO admin;
  private Date loanDate;
  private Date returnDate;
  private Date actualReturnDate;
  private BigDecimal lateChargerPrice;

  public LoanDTO() {
  }

  public Long getLoanId() {
    return loanId;
  }

  public LoanDTO(Long loanId, MemberDTO member, AdminDTO admin, Date loanDate, Date returnDate, Date actualReturnDate,
      BigDecimal lateChargerPrice) {
    this.loanId = loanId;
    this.member = member;
    this.admin = admin;
    this.loanDate = loanDate;
    this.returnDate = returnDate;
    this.actualReturnDate = actualReturnDate;
    this.lateChargerPrice = lateChargerPrice;
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

  public void setLoanId(Long loanId) {
    this.loanId = loanId;
  }

  public MemberDTO getMember() {
    return member;
  }

  public void setMember(MemberDTO member) {
    this.member = member;
  }

  public AdminDTO getAdmin() {
    return admin;
  }

  public void setAdmin(AdminDTO admin) {
    this.admin = admin;
  }

  public Date getLoanDate() {
    return loanDate;
  }

  public void setLoanDate(Date loanDate) {
    this.loanDate = loanDate;
  }

}
