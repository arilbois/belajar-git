package com.pd79.librarydemosyahril.dtos;

public class PublisherDTO {
  private Long publisherId;
  private String publisherName;

  public PublisherDTO() {
  }

  public PublisherDTO(Long publisherId, String publisherName) {
    this.publisherId = publisherId;
    this.publisherName = publisherName;
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

}
