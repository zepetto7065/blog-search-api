package com.me.search.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
@Builder
public class NaverBlogSearchResult {

    String title;
    String link;
    String description;
    String bloggername;
    String bloggerlink;
    String postdate;
}
