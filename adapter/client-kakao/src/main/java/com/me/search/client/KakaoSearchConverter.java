package com.me.search.client;

import com.me.search.client.payload.KakaoClientResponse.Document;
import com.me.search.dto.KakaoBlogSearchResult;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface KakaoSearchConverter {

  KakaoSearchConverter INSTANCE = Mappers.getMapper(KakaoSearchConverter.class);

  List<KakaoBlogSearchResult> map(List<Document> responseList);
}
