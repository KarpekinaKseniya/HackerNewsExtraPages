package com.example.hacker.news.config;

import io.github.resilience4j.common.circuitbreaker.configuration.CircuitBreakerConfigCustomizer;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

import static io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType.COUNT_BASED;
import static io.netty.channel.ChannelOption.CONNECT_TIMEOUT_MILLIS;

@Configuration
public class HackerNewsConfig {

  private final String hackerNewsUri;
  private final int readTimeout;
  private final int writeTimeout;
  private final int connectTimeout;
  private final int minNumberOfCalls;

  public HackerNewsConfig(
      @Value("${hacker.news.url}") final String hackerNewsUri,
      @Value("${hacker.news.read.timeout.sec}") final int readTimeout,
      @Value("${hacker.news.write.timeout.sec}") final int writeTimeout,
      @Value("${hacker.news.connect.timeout.ms}") final int connectTimeout,
      @Value("${hacker.news.min.number.calls}") final int minNumberOfCalls) {
    this.hackerNewsUri = hackerNewsUri;
    this.readTimeout = readTimeout;
    this.writeTimeout = writeTimeout;
    this.connectTimeout = connectTimeout;
    this.minNumberOfCalls = minNumberOfCalls;
  }

  @Bean
  public WebClient hackerNewsClient() {
    final HttpClient httpClient =
        HttpClient.create()
            .option(CONNECT_TIMEOUT_MILLIS, connectTimeout)
            .doOnConnected(
                connection ->
                    connection
                        .addHandlerLast(new ReadTimeoutHandler(readTimeout))
                        .addHandlerLast(new WriteTimeoutHandler(writeTimeout)));
    final ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);
    return WebClient.builder().baseUrl(hackerNewsUri).clientConnector(connector).build();
  }

  @Bean
  public CircuitBreakerConfigCustomizer externalServiceFooCircuitBreakerConfig() {
    return CircuitBreakerConfigCustomizer.of(
        "hackerNewsService",
        builder ->
            builder
                .slidingWindowSize(10)
                .slidingWindowType(COUNT_BASED)
                .waitDurationInOpenState(Duration.ofSeconds(5))
                .minimumNumberOfCalls(minNumberOfCalls)
                .failureRateThreshold(50.0f));
  }

}
