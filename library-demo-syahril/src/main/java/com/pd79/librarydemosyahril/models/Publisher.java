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
@Table(name = "publisher")
public class Publisher {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "publisher_id", nullable = false)
  private Long publisherId;

  @Column(name = "publisher_name", nullable = false)
  private String publisherName;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "publisher")
  private List<Book> books;

  public Publisher() {
  }

  public Publisher(Long publisherId, String publisherName, List<Book> books) {
    this.publisherId = publisherId;
    this.publisherName = publisherName;
    this.books = books;
  }

  public Long getPublisherId() {
    return publisherId;
  }

  public void setPublisherId(Long publisherId) {
    this.publisherId = publisherId;
  }

  public String getPublisherName() {
    return publisherName;
  }

  public void setPublisherName(String publisherName) {
    this.publisherName = publisherName;
  }

  public List<Book> getBooks() {
    return books;
  }

  public void setBooks(List<Book> books) {
    this.books = books;
  }

}
