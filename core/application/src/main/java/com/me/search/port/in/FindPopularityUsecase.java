package com.me.search.port.in;

import com.me.search.domain.Keyword;

import java.util.List;

public interface FindPopularityUsecase {
    List<Keyword> findPopularityKeywords();
}
