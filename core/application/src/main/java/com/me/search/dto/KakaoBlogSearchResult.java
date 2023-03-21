package com.me.search.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
@Builder
public class KakaoBlogSearchResult {

    String title;
    String contents;
    String url;
    String blogName;
    String thumbnail;
    String datetime;
}
