package com.pd79.librarydemosyahril.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "author")
public class Author {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "author_id", nullable = false)
  private Long authorId;

  @Column(name = "author_name", nullable = false)
  private String authorName;

  @Column(name = "gender", nullable = true)
  private String gender;

  @Column(name = "age", nullable = true)
  private int age;

  @Column(name = "country", nullable = false)
  private String country;

  @Column(name = "rating", nullable = false)
  private String rating;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "author")
  private List<Book> books;

  public Author() {
  }

  public Author(Long authorId, String authorName, String gender, int age, String country, String rating,
      List<Book> books) {
    this.authorId = authorId;
    this.authorName = authorName;
    this.gender = gender;
    this.age = age;
    this.country = country;
    this.rating = rating;
    this.books = books;
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

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
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

  public List<Book> getBooks() {
    return books;
  }

  public void setBooks(List<Book> books) {
    this.books = books;
  }

}
