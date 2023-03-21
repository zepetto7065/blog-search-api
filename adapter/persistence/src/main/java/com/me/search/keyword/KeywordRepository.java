package com.me.search.keyword;

import com.me.search.domain.Keyword;
import com.me.search.port.out.FindKeywordRepositoryAdapter;
import com.me.search.port.out.RegistKeywordRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@CacheConfig(cacheNames = "keyword")
public class KeywordRepository implements FindKeywordRepositoryAdapter, RegistKeywordRepositoryAdapter {

    private final KeywordJpaRepository keywordJpaRepository;

    @Override
    public Optional<Keyword> findKeywordByWord(String query) {
        return keywordJpaRepository.findByWord(query);
    }

    @Override
    @Cacheable
    public List<Keyword> findPopularityKeywords() {
        return keywordJpaRepository.findTop10ByOrderBySearchCntDesc();
    }

    @Override
    @CacheEvict(allEntries = true)
    public void save(Keyword keyword) {
        keywordJpaRepository.save(keyword);
    }
}
