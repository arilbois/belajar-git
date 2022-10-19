package com.pd79.librarydemosyahril.dtos;

public class MemberDTO {
  private Long memberId;
  private String memberName;
  private String address;
  private String phone;

  public MemberDTO() {
  }

  public Long getMemberId() {
    return memberId;
  }

  public MemberDTO(Long memberId, String memberName, String address, String phone) {
    this.memberId = memberId;
    this.memberName = memberName;
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

  public void setMemberId(Long memberId) {
    this.memberId = memberId;
  }

  public String getMemberName() {
    return memberName;
  }

  public void setMemberName(String memberName) {
    this.memberName = memberName;
  }

}
