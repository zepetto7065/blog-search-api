package com.me.search.client.payload;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Getter
@Builder
@Jacksonized
public class NaverClientResponse {

  String lastBuildDate;
  int total;
  int start;
  int display;
  List<Item> items;

  @Value
  @Getter
  @Builder
  @Jacksonized
  public static class Item {
    String title;
    String link;
    String description;
    String bloggername;
    String bloggerlink;
    String postdate;
  }

}
