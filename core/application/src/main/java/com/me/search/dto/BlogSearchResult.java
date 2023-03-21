package com.me.search.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
@Builder
public class BlogSearchResult {

  String title;
  String url;
  String contents;
  String blogName;
  String thumbnail;
  String datetime;
}
