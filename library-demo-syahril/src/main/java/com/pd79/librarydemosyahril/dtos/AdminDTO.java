package com.pd79.librarydemosyahril.dtos;

public class AdminDTO {
  private Long adminId;
  private String adminName;
  private String address;
  private String phone;

  public AdminDTO() {
  }

  public AdminDTO(Long adminId, String adminName, String address, String phone) {
    this.adminId = adminId;
    this.adminName = adminName;
    this.address = address;
    this.phone = phone;
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

  public Long getAdminId() {
    return adminId;
  }

  public void setAdminId(Long adminId) {
    this.adminId = adminId;
  }

  public String getAdminName() {
    return adminName;
  }

  public void setAdminName(String adminName) {
    this.adminName = adminName;
  }

}
