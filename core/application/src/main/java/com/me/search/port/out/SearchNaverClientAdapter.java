package com.me.search.port.out;

import com.me.search.dto.NaverBlogSearchCommand;
import com.me.search.dto.NaverBlogSearchResult;
import java.util.List;

public interface SearchNaverClientAdapter {
    List<NaverBlogSearchResult> searchBlog(NaverBlogSearchCommand command);
}
