package com.me.search.controller.popularity.payload;

import lombok.Getter;
import lombok.Value;

@Getter
@Value
public class PopularityApiResponse {

  Integer id;
  String word;
  int searchCnt;

}
