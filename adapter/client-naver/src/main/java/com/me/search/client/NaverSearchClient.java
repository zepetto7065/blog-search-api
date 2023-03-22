package com.me.search.client;

import com.me.search.client.payload.NaverClientResponse;
import com.me.search.dto.NaverBlogSearchCommand;
import com.me.search.dto.NaverBlogSearchResult;
import com.me.search.exception.NaverSearchClientException;
import com.me.search.port.out.SearchNaverClientAdapter;
import java.net.URI;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
class NaverSearchClient implements SearchNaverClientAdapter {

  private final RestTemplate restTemplate;

  private final String INVALID_RESPONSE = "Invalid Response";

  @Value("${client.naver.uri}")
  private String rootUri;
  @Value("${client.naver.client-id}")
  private String clientId;
  @Value("${client.naver.client-secret}")
  private String secret;

  public NaverSearchClient() {
    this.restTemplate = new RestTemplateBuilder().build();
  }

  @Override
  public List<NaverBlogSearchResult> searchBlog(final NaverBlogSearchCommand command) {
    final URI uri = UriComponentsBuilder.fromHttpUrl(rootUri + "/v1/search/blog.json")
        .queryParam("query", command.getQuery())
        .queryParam("display", command.getDisplay())
        .build().toUri();

    final RequestEntity<Void> requestEntity = RequestEntity
        .get(uri)
        .header("X-Naver-Client-Id", clientId)
        .header("X-Naver-Client-Secret", secret)
        .build();

    try {
      final NaverClientResponse response = Objects.requireNonNull(
              restTemplate.exchange(requestEntity, NaverClientResponse.class), INVALID_RESPONSE)
          .getBody();
      return NaverSearchConverter.INSTANCE.map(response.getItems());
    } catch (Exception e) {
      throw new NaverSearchClientException(e.getMessage());
    }
  }
}
