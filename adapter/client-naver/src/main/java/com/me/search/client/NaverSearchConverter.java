package com.me.search.client;

import com.me.search.client.payload.NaverClientResponse.Item;
import com.me.search.dto.NaverBlogSearchResult;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NaverSearchConverter {
    NaverSearchConverter INSTANCE = Mappers.getMapper(NaverSearchConverter.class);

    List<NaverBlogSearchResult> map(List<Item> responseList);
}
