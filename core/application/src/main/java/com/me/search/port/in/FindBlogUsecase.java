package com.me.search.port.in;

import com.me.search.dto.BlogSearchResult;
import com.me.search.dto.BlogSearchCommand;
import java.util.List;

public interface FindBlogUsecase {
    List<BlogSearchResult> getList(BlogSearchCommand build);
}
