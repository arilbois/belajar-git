package com.pd79.librarydemosyahril.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "member")
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "member_Id")
  private long memberId;

  @Column(name = "memberName", nullable = false)
  private String memberName;

  @Column(name = "address", nullable = false)
  private String address;

  @Column(name = "phone")
  private String phone;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
  private List<Loan> loans;

  public Member() {
  }

  public Member(String memberName, String address, String phone) {
    this.memberName = memberName;
    this.address = address;
    this.phone = phone;
  }

  public long getMemberId() {
    return memberId;
  }

  public void setMemberId(long memberId) {
    this.memberId = memberId;
  }

  public String getMemberName() {
    return memberName;
  }

  public void setMemberName(String memberName) {
    this.memberName = memberName;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public List<Loan> getLoans() {
    return loans;
  }

  public void setLoans(List<Loan> loans) {
    this.loans = loans;
  }

}
