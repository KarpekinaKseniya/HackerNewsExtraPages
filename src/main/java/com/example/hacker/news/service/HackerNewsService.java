package com.example.hacker.news.service;

import com.example.hacker.news.api.Comments;
import com.example.hacker.news.api.Stories;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface HackerNewsService {

  Flux<Stories> getTheLatest100News();

  Mono<Stories> getStoryById(Long id);

  Mono<Comments> getCommentById(Long id);
}
