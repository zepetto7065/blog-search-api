package com.me.search.dto;

import com.me.search.type.SortType;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
@Builder
public class BlogSearchCommand {
    String query;
    SortType sort;
    Integer page;
    Integer size;
}
