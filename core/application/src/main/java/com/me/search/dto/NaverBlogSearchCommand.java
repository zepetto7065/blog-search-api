package com.me.search.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Getter
@Value
@Builder
public class NaverBlogSearchCommand {
  String query;
  Integer display;
}
