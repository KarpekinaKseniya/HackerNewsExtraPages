package com.example.hacker.news.resource;

import com.example.hacker.news.api.Comments;
import com.example.hacker.news.api.Stories;
import com.example.hacker.news.service.HackerNewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/hacker-news")
public class HackerNewsResource {

  private final HackerNewsService hackerNewsService;

  public HackerNewsResource(final HackerNewsService hackerNewsService) {
    this.hackerNewsService = hackerNewsService;
  }

  @Operation(
      summary = "Get the latest 100 news",
      description = "Endpoint for the latest 100 news",
      responses = {
        @ApiResponse(responseCode = "200", description = "Ok"),
        @ApiResponse(
            responseCode = "400",
            description = "Bad Request",
            content = @Content(schema = @Schema(implementation = String.class))),
        @ApiResponse(
            responseCode = "500",
            description = "Internal Server Error",
            content = @Content(schema = @Schema(implementation = String.class)))
      })
  @GetMapping("/new-stories")
  public Flux<Stories> getTheLatest100News() {
    return hackerNewsService.getTheLatest100News();
  }

  @Operation(
      summary = "Get story",
      description = "Endpoint for getting story by id",
      responses = {
        @ApiResponse(responseCode = "200", description = "Success"),
        @ApiResponse(
            responseCode = "400",
            description = "Bad Request",
            content = @Content(schema = @Schema(implementation = String.class))),
        @ApiResponse(
            responseCode = "404",
            description = "Not Found",
            content = @Content(schema = @Schema(implementation = String.class))),
        @ApiResponse(
            responseCode = "500",
            description = "Internal Server Error",
            content = @Content(schema = @Schema(implementation = String.class)))
      })
  @GetMapping("/stories/{id}")
  public Mono<ResponseEntity<Stories>> getStoryById(@PathVariable final Long id) {
    return hackerNewsService
        .getStoryById(id)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @Operation(
      summary = "Get comment",
      description = "Endpoint for getting comment by id",
      responses = {
        @ApiResponse(responseCode = "200", description = "Success"),
        @ApiResponse(
            responseCode = "400",
            description = "Bad Request",
            content = @Content(schema = @Schema(implementation = String.class))),
        @ApiResponse(
            responseCode = "404",
            description = "Not Found",
            content = @Content(schema = @Schema(implementation = String.class))),
        @ApiResponse(
            responseCode = "500",
            description = "Internal Server Error",
            content = @Content(schema = @Schema(implementation = String.class)))
      })
  @GetMapping("/comments/{id}")
  public Mono<ResponseEntity<Comments>> getCommentsById(@PathVariable final Long id) {
    return hackerNewsService
        .getCommentById(id)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }
}
