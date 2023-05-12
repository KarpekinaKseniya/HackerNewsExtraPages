package com.example.hacker.news.api;

import java.time.LocalDateTime;
import java.util.Set;

import static java.time.Instant.ofEpochMilli;
import static java.util.TimeZone.getDefault;

public class Stories {
  private Long id;
  private String author;
  private Integer descendants;
  private Set<Long> commentIds;
  private Integer score;
  private LocalDateTime time;
  private String title;
  private String url;

  public Stories() {}

  public Stories(
      final String by,
      final Integer descendants,
      final Long id,
      final Set<Long> kids,
      final Integer score,
      final Long time,
      final String title,
      final String url) {
    this.id = id;
    this.author = by;
    this.descendants = descendants;
    this.commentIds = kids;
    this.score = score;
    this.time = LocalDateTime.ofInstant(ofEpochMilli(time * 1000), getDefault().toZoneId());
    this.title = title;
    this.url = url;
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

  public Integer getDescendants() {
    return descendants;
  }

  public void setDescendants(final Integer descendants) {
    this.descendants = descendants;
  }

  public Set<Long> getCommentIds() {
    return commentIds;
  }

  public void setKids(final Set<Long> kids) {
    this.commentIds = kids;
  }

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }

  public LocalDateTime getTime() {
    return time;
  }

  public void setTime(final Long time) {
    this.time = LocalDateTime.ofInstant(ofEpochMilli(time * 1000), getDefault().toZoneId());
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(final String title) {
    this.title = title;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(final String url) {
    this.url = url;
  }

  @Override
  public String toString() {
    return "Stories{"
        + "id="
        + id
        + ", author='"
        + author
        + '\''
        + ", descendants="
        + descendants
        + ", commentIds="
        + commentIds
        + ", score="
        + score
        + ", time="
        + time
        + ", title='"
        + title
        + '\''
        + ", url='"
        + url
        + '\''
        + '}';
  }
}
