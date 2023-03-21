package com.me.search.client;

import com.me.search.client.payload.NaverClientResponse;
import com.me.search.dto.NaverBlogSearchCommand;
import java.net.URI;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.RequestEntity;
import org.springframework.web.util.UriComponentsBuilder;

class NaverSearchClientTest {
  private TestRestTemplate restTemplate = new TestRestTemplate();
  private String URL = "https://openapi.naver.com";
  private String CLINET_ID = "_1fOWlYqQXvTzXodB0jM";
  private String SECRET = "NO1fWhFTmg";


  @Test
  void testNaverSearchApi(){
    final NaverBlogSearchCommand command = NaverBlogSearchCommand.builder()
        .query("test")
        .display(10)
        .build();

    final URI uri = UriComponentsBuilder.fromHttpUrl(URL + "/v1/search/blog.json")
        .queryParam("query", command.getQuery())
        .queryParam("display", command.getDisplay())
        .build().toUri();

    final RequestEntity<Void> requestEntity = RequestEntity
        .get(uri)
        .header("X-Naver-Client-Id", CLINET_ID)
        .header("X-Naver-Client-Secret", SECRET)
        .build();

    NaverClientResponse resp = restTemplate.exchange(requestEntity, NaverClientResponse.class)
        .getBody();

    Assertions.assertThat(resp).isNotNull();
  }
}