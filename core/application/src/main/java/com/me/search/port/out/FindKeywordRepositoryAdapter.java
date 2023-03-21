package com.me.search.port.out;

import com.me.search.domain.Keyword;

import java.util.List;
import java.util.Optional;

public interface FindKeywordRepositoryAdapter {
    Optional<Keyword> findKeywordByWord(String query);
    List<Keyword> findPopularityKeywords();
}
