package com.me.search.client.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Value
@Getter
@Builder
@Jacksonized
public class KakaoClientResponse {

  Meta meta;
  List<Document> documents;

  @Value
  @Getter
  @Builder
  @Jacksonized
  @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
  public static class Meta {

    Integer totalCount;
    Integer pageableCount;
    Boolean isEnd;
  }

  @Value
  @Getter
  @Builder
  @Jacksonized
  public static class Document {

    String title;
    String contents;
    String url;
    @JsonProperty("blogname")
    String blogName;
    String thumbnail;
    String datetime;
  }
}
