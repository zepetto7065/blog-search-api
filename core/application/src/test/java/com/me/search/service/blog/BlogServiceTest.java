package com.me.search.service.blog;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.me.search.dto.BlogSearchCommand;
import com.me.search.dto.BlogSearchResult;
import com.me.search.dto.KakaoBlogSearchResult;
import com.me.search.dto.NaverBlogSearchCommand;
import com.me.search.dto.NaverBlogSearchResult;
import com.me.search.exception.KakaoSearchClientException;
import com.me.search.port.out.FindKeywordRepositoryAdapter;
import com.me.search.port.out.SearchKakaoClientAdapter;
import com.me.search.port.out.SearchNaverClientAdapter;
import com.me.search.type.SortType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class BlogServiceTest {

  @InjectMocks
  BlogService blogService;
  @Mock
  FindKeywordRepositoryAdapter findKeywordRepositoryAdapter;
  @Mock
  SearchKakaoClientAdapter searchKakaoClientAdapter;
  @Mock
  SearchNaverClientAdapter searchNaverClientAdapter;

  @BeforeEach
  void init(){
    MockitoAnnotations.openMocks(this);
  }

  @Test
  @DisplayName("카카오 Blog 정상 search")
  void successKakaoSearchList() {
    //given
    BlogSearchCommand command = BlogSearchCommand.builder()
        .query("test")
        .page(1)
        .size(10)
        .sort(SortType.ACCURACY)
        .build();
    given(findKeywordRepositoryAdapter.findKeywordByWord(command.getQuery())).willReturn(Optional.empty());
    given(searchKakaoClientAdapter.searchBlog(command)).willReturn(new ArrayList<>(
        Arrays.asList(
            KakaoBlogSearchResult.builder().title("test").contents("test입니다.").build(),
            KakaoBlogSearchResult.builder().title("test2").contents("test2입니다.").build()
        ))
    );
    //when
    List<BlogSearchResult> ret = blogService.getList(command);

    //then
    assertThat(ret.size()).isEqualTo(2);
    assertThat(ret.get(0).getTitle()).isEqualTo("test");
    assertThat(ret.get(1).getTitle()).isEqualTo("test2");
  }

  @Test
  @DisplayName("카카오 실패, 네이버 정상 search")
  void failkakaoSuccessNaverSearchList() {
    //given
    BlogSearchCommand command = BlogSearchCommand.builder()
        .query("test")
        .page(1)
        .size(10)
        .sort(SortType.ACCURACY)
        .build();

    NaverBlogSearchCommand naverCommand = NaverBlogSearchCommand.builder()
        .query(command.getQuery())
        .display(command.getSize())
        .build();

    given(findKeywordRepositoryAdapter.findKeywordByWord(command.getQuery())).willReturn(Optional.empty());
    given(searchKakaoClientAdapter.searchBlog(command)).willThrow(new KakaoSearchClientException("일시적 카카오 서버 장애"));
    given(searchNaverClientAdapter.searchBlog(naverCommand)).willReturn(new ArrayList<>(
        Arrays.asList(
            NaverBlogSearchResult.builder().title("naverTest").description("test입니다.").build(),
            NaverBlogSearchResult.builder().title("naverTest2").description("test2입니다.").build(),
            NaverBlogSearchResult.builder().title("naverTest3").description("test2입니다.").build()
        ))
    );
    //when
    List<BlogSearchResult> ret = blogService.getList(command);

    //then
    assertThat(ret.size()).isEqualTo(3);
    assertThat(ret.get(0).getTitle()).isEqualTo("naverTest");
    assertThat(ret.get(1).getTitle()).isEqualTo("naverTest2");
    assertThat(ret.get(2).getTitle()).isEqualTo("naverTest3");
  }

}