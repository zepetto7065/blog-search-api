package com.me.search.client;

import com.me.search.client.payload.KakaoClientResponse;
import com.me.search.dto.BlogSearchCommand;
import com.me.search.dto.KakaoBlogSearchResult;
import com.me.search.exception.KakaoSearchClientException;
import com.me.search.port.out.SearchKakaoClientAdapter;
import java.net.URI;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
class KakaoSearchClient implements SearchKakaoClientAdapter {

  private final RestTemplate restTemplate;
  private final String INVALID_RESPONSE = "Invalid Response";

  @Value("${client.kakao.uri}")
  private String rootUri;

  @Value("${client.kakao.key}")
  private String restApiKey;

  public KakaoSearchClient() {
    this.restTemplate = new RestTemplateBuilder().build();
  }

  @Override
  public List<KakaoBlogSearchResult> searchBlog(final BlogSearchCommand command) {
    final URI uri = UriComponentsBuilder.fromHttpUrl(rootUri + "/v2/search/blog")
        .queryParam("query", command.getQuery())
        .queryParam("sort", command.getSort())
        .queryParam("page", command.getPage())
        .queryParam("size", command.getSize())
        .build().toUri();

    final RequestEntity<Void> requestEntity = RequestEntity
        .get(uri)
        .header("Authorization", "KakaoAK " + restApiKey)
        .build();

    try {
      final KakaoClientResponse response = Objects.requireNonNull(
              restTemplate.exchange(requestEntity, KakaoClientResponse.class), INVALID_RESPONSE)
          .getBody();
      return KakaoSearchConverter.INSTANCE.map(response.getDocuments());
    } catch (Exception e) {
      throw new KakaoSearchClientException(e.getMessage());
    }
  }
}
