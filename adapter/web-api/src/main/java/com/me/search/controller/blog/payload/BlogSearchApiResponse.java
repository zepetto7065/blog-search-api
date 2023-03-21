package com.me.search.controller.blog.payload;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
@Builder
public class BlogSearchApiResponse {

  String title;
  String contents;
  String url;
  String blogName;
  String thumbnail;
  String datetime;
}
