package com.me.search.controller.popularity.converter;

import com.me.search.controller.popularity.payload.PopularityApiResponse;
import com.me.search.domain.Keyword;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PopularityConverter {
    PopularityConverter INSTANCE = Mappers.getMapper(PopularityConverter.class);
    List<PopularityApiResponse> mapToApiResponse(List<Keyword> keywordList);
}
