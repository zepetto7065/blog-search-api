package com.me.search.service.blog;

import com.me.search.dto.BlogSearchResult;
import com.me.search.dto.KakaoBlogSearchResult;
import com.me.search.dto.NaverBlogSearchResult;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BlogConverter {

  BlogConverter INSTANCE = Mappers.getMapper(BlogConverter.class);

  List<BlogSearchResult> mapKakaoResults(List<KakaoBlogSearchResult> kakaoBlogSearchResultList);

  List<BlogSearchResult> mapNaverResults(List<NaverBlogSearchResult> naverBlogSearchResultList);

  @Mapping(target = "url", source = "link")
  @Mapping(target = "contents", source = "description")
  @Mapping(target = "blogName", source = "bloggername")
  @Mapping(target = "thumbnail", source = "bloggerlink")
  @Mapping(target = "datetime", source = "postdate")
  BlogSearchResult map(NaverBlogSearchResult naverBlogSearchResult);
}
