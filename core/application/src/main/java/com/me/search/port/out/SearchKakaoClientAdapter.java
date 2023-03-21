package com.me.search.port.out;

import com.me.search.dto.BlogSearchCommand;
import com.me.search.dto.KakaoBlogSearchResult;

import java.util.List;

public interface SearchKakaoClientAdapter {
    List<KakaoBlogSearchResult> searchBlog(BlogSearchCommand command);
}
