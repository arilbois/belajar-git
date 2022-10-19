package com.pd79.librarydemosyahril.dtos;

public class AuthorDTO {
  private Long authorId;
  private String authorName;
  private String gender;
  private String age;
  private String country;
  private String rating;

  public AuthorDTO() {
  }

  public AuthorDTO(Long authorId, String authorName, String gender, String age, String country, String rating) {
    this.authorId = authorId;
    this.authorName = authorName;
    this.gender = gender;
    this.age = age;
    this.country = country;
    this.rating = rating;
  }

  public Long getAuthorId() {
    return authorId;
  }

  public void setAuthorId(Long authorId) {
    this.authorId = authorId;
  }

  public String getAuthorName() {
    return authorName;
  }

  public void setAuthorName(String authorName) {
    this.authorName = authorName;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getRating() {
    return rating;
  }

  public void setRating(String rating) {
    this.rating = rating;
  }

}
