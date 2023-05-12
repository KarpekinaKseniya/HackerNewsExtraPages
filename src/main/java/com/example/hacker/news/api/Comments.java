package com.example.hacker.news.api;

import java.time.LocalDateTime;
import java.util.Set;

import static java.time.Instant.ofEpochMilli;
import static java.util.TimeZone.getDefault;

public class Comments {

  private Long id;
  private String author;
  private Long parent;
  private Set<Long> kids;
  private String text;
  private LocalDateTime time;

  public Comments() {}

  public Comments(
      final Long id,
      final String by,
      final Long parent,
      final Set<Long> kids,
      final String text,
      final Long time) {
    this.id = id;
    this.author = by;
    this.parent = parent;
    this.kids = kids;
    this.text = text;
    this.time = LocalDateTime.ofInstant(ofEpochMilli(time * 1000), getDefault().toZoneId());
  }

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public String getAuthor() {
    return author;
  }

  public void setBy(final String by) {
    this.author = by;
  }

  public Long getParent() {
    return parent;
  }

  public void setParent(final Long parent) {
    this.parent = parent;
  }

  public Set<Long> getKids() {
    return kids;
  }

  public void setKids(final Set<Long> kids) {
    this.kids = kids;
  }

  public String getText() {
    return text;
  }

  public void setText(final String text) {
    this.text = text;
  }

  public LocalDateTime getTime() {
    return time;
  }

  public void setTime(final Long time) {
    this.time = LocalDateTime.ofInstant(ofEpochMilli(time * 1000), getDefault().toZoneId());
  }

  @Override
  public String toString() {
    return "Comments{" +
            "id=" + id +
            ", author='" + author + '\'' +
            ", parent=" + parent +
            ", kids=" + kids +
            ", text='" + text + '\'' +
            ", time=" + time +
            '}';
  }

}
