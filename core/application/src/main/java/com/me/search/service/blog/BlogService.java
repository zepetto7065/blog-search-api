package com.me.search.service.blog;

import com.me.search.domain.Keyword;
import com.me.search.dto.BlogSearchResult;
import com.me.search.dto.BlogSearchCommand;
import com.me.search.dto.NaverBlogSearchCommand;
import com.me.search.dto.NaverBlogSearchResult;
import com.me.search.exception.KakaoSearchClientException;
import com.me.search.port.in.FindBlogUsecase;
import com.me.search.port.out.FindKeywordRepositoryAdapter;
import com.me.search.port.out.RegistKeywordRepositoryAdapter;
import com.me.search.port.out.SearchKakaoClientAdapter;
import com.me.search.port.out.SearchNaverClientAdapter;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BlogService implements FindBlogUsecase {

  private final FindKeywordRepositoryAdapter findKeywordRepositoryAdapter;
  private final RegistKeywordRepositoryAdapter registKeywordRepositoryAdapter;
  private final SearchKakaoClientAdapter kakaoClient;
  private final SearchNaverClientAdapter naverClient;

  @Override
  @Transactional
  public List<BlogSearchResult> getList(BlogSearchCommand command) {
    increaseKeywordSearchCount(command);
    try {
      return BlogConverter.INSTANCE.mapKakaoResults(kakaoClient.searchBlog(command));
    } catch (KakaoSearchClientException e) {
      List<NaverBlogSearchResult> naverBlogSearchResults = naverClient.searchBlog(
          NaverBlogSearchCommand.builder()
              .query(command.getQuery())
              .display(command.getSize())
              .build());
      return BlogConverter.INSTANCE.mapNaverResults(naverBlogSearchResults);
    }
  }

  private void increaseKeywordSearchCount(BlogSearchCommand command) {
    Optional<Keyword> keyword = findKeywordRepositoryAdapter.findKeywordByWord(command.getQuery());
    keyword.ifPresentOrElse(k -> {
          k.plusSearchCnt();
          registKeywordRepositoryAdapter.save(k);
        },
        () -> registKeywordRepositoryAdapter.save(new Keyword(command.getQuery(), 1)));
  }
}
