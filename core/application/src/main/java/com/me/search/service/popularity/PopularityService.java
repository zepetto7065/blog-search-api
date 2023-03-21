package com.me.search.service.popularity;

import com.me.search.domain.Keyword;
import com.me.search.port.in.FindPopularityUsecase;
import com.me.search.port.out.FindKeywordRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PopularityService implements FindPopularityUsecase
{
    private final FindKeywordRepositoryAdapter keywordRepositoryAdapter;
    @Override
    public List<Keyword> findPopularityKeywords() {
        return keywordRepositoryAdapter.findPopularityKeywords();
    }
}
