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
@Table(name = "admin")
public class Admin {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "admin_Id")
  private long adminId;

  @Column(name = "adminName", nullable = false)
  private String adminName;

  @Column(name = "address")
  private String address;

  @Column(name = "phone", nullable = false)
  private String phone;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "admin")
  private List<Loan> loans;

  public Admin() {
  }

  public Admin(String adminName, String address, String phone) {
    this.adminName = adminName;
    this.address = address;
    this.phone = phone;
  }

  public long getAdminId() {
    return adminId;
  }

  public void setAdminId(long adminId) {
    this.adminId = adminId;
  }

  public String getAdminName() {
    return adminName;
  }

  public void setAdminName(String adminName) {
    this.adminName = adminName;
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
