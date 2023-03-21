package com.me.search.client;

import static org.junit.jupiter.api.Assertions.*;

import com.me.search.client.payload.KakaoClientResponse;
import com.me.search.dto.BlogSearchCommand;
import com.me.search.dto.NaverBlogSearchCommand;
import com.me.search.type.SortType;
import java.net.URI;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.RequestEntity;
import org.springframework.web.util.UriComponentsBuilder;

class KakaoSearchClientTest {

  private TestRestTemplate restTemplate = new TestRestTemplate();
  private String URL = "http://dapi.kakao.com/";
  private String SECRET = "4e41e308dee76f0ccbfb117f9bf07a99";

  @Test
  void testKakaoSearchApi() {
    final BlogSearchCommand command = BlogSearchCommand.builder()
        .query("test")
        .sort(SortType.ACCURACY)
        .page(1)
        .size(10)
        .build();

    final URI uri = UriComponentsBuilder.fromHttpUrl(URL + "/v2/search/blog")
        .queryParam("query", command.getQuery())
        .queryParam("sort", command.getSort())
        .queryParam("page", command.getPage())
        .queryParam("size", command.getSize())
        .build().toUri();

    final RequestEntity<Void> requestEntity = RequestEntity
        .get(uri)
        .header("Authorization", "KakaoAK " + SECRET)
        .build();

    KakaoClientResponse response =
        restTemplate.exchange(requestEntity, KakaoClientResponse.class)
            .getBody();

    Assertions.assertThat(response).isNotNull();
  }

}