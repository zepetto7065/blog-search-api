package com.me.search.controller.blog;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.me.search.dto.BlogSearchCommand;
import com.me.search.dto.BlogSearchResult;
import com.me.search.port.in.FindBlogUsecase;
import com.me.search.type.SortType;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(BlogController.class)
class BlogControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private FindBlogUsecase findBlogUsecase;

  @Test
  public void listTest() throws Exception {
    //given
    String keyword = "test";
    SortType sortType = SortType.ACCURACY;
    Integer page = 1;
    Integer size = 10;

    BlogSearchCommand command = BlogSearchCommand.builder()
        .query(keyword)
        .sort(sortType)
        .page(page)
        .size(size)
        .build();

    given(findBlogUsecase.getList(command)).willReturn(new ArrayList<>(
        Arrays.asList(
            BlogSearchResult.builder().title("testBlog").build(),
            BlogSearchResult.builder().title("testBlog2").build()
        )
    ));

    //when
    ResultActions action = mockMvc.perform(get("/blog")
            .param("keyword", keyword)
            .param("sortType", String.valueOf(sortType))
            .param("page", String.valueOf(page))
            .param("size", String.valueOf(size))
            .contentType(MediaType.APPLICATION_JSON)
        );

    //then
    action.andExpect(status().isOk())
        .andExpect(jsonPath("$.contents[0].title", is("testBlog")))
        .andExpect(jsonPath("$.contents[1].title", is("testBlog2")))
        .andDo(print());
  }
}