package com.example.hacker.news.service;

import com.example.hacker.news.api.Comments;
import com.example.hacker.news.api.Stories;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static java.lang.String.format;

@Service
public class HackerNewsServiceImpl implements HackerNewsService {

  private static final String ITEM_PATH = "/item/%d.json";
  private static final String NEW_STORIES_PATH = "/newstories.json";
  private static final int LAST_100 = 100;

  private final WebClient hackerNewsClient;
  private final CircuitBreakerRegistry circuitBreakerRegistry;

  public HackerNewsServiceImpl(
      final WebClient hackerNewsClient, final CircuitBreakerRegistry circuitBreakerRegistry) {
    this.hackerNewsClient = hackerNewsClient;
    this.circuitBreakerRegistry = circuitBreakerRegistry;
  }

  @Override
  public Flux<Stories> getTheLatest100News() {
    final CircuitBreaker circuitBreaker =
        circuitBreakerRegistry.circuitBreaker("hackerNewsService", "hackerNewsService");
    return hackerNewsClient
        .get()
        .uri(uriBuilder -> uriBuilder.path(NEW_STORIES_PATH).build())
        .retrieve()
        .bodyToFlux(Long.class)
        .flatMap(this::getStoryById)
        .take(LAST_100)
        .transformDeferred(CircuitBreakerOperator.of(circuitBreaker));
  }

  @Override
  public Mono<Stories> getStoryById(final Long id) {
    return getItemById(id, Stories.class);
  }

  @Override
  public Mono<Comments> getCommentById(final Long id) {
    return getItemById(id, Comments.class);
  }

  private <T> Mono<T> getItemById(final Long id, final Class<T> className) {
    final CircuitBreaker circuitBreaker =
        circuitBreakerRegistry.circuitBreaker("hackerNewsService", "hackerNewsService");
    return hackerNewsClient
        .get()
        .uri(uriBuilder -> uriBuilder.path(format(ITEM_PATH, id)).build())
        .retrieve()
        .bodyToMono(className)
        .transformDeferred(CircuitBreakerOperator.of(circuitBreaker));
  }
}
