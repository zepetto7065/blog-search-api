package com.me.search.controller.http;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PageResult<T> {
  private final long page;
  private final long total;
  private final List<T> contents;
}
