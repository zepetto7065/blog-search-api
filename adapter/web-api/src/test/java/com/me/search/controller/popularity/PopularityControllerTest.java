package com.me.search.controller.popularity;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.me.search.domain.Keyword;
import com.me.search.port.in.FindPopularityUsecase;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(PopularityController.class)
class PopularityControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private FindPopularityUsecase findPopularityUsecase;

  @Test
  public void listTest() throws Exception {
    //given
    given(findPopularityUsecase.findPopularityKeywords()).willReturn(new ArrayList<>(
        Arrays.asList(
            Keyword.builder().word("대박").searchCnt(20).build(),
            Keyword.builder().word("가즈아").searchCnt(12).build()
        )
    ));

    //when
    ResultActions action = mockMvc.perform(get("/popularity")
        .contentType(MediaType.APPLICATION_JSON)
    );

    //then
    action.andExpect(status().isOk())
        .andExpect(jsonPath("$.[0].word", is("대박")))
        .andExpect(jsonPath("$.[0].searchCnt", is(20)))
        .andExpect(jsonPath("$.[1].word", is("가즈아")))
        .andExpect(jsonPath("$.[1].searchCnt", is(12)))
        .andDo(print());
  }
}