package com.me.search.controller.blog;

import com.me.search.controller.http.PageResult;
import com.me.search.dto.BlogSearchCommand;
import com.me.search.dto.BlogSearchResult;
import com.me.search.port.in.FindBlogUsecase;
import com.me.search.type.SortType;
import java.util.List;
import javax.validation.constraints.Max;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/blog")
@RequiredArgsConstructor
public class BlogController {

    private final FindBlogUsecase findBlogUsecase;

    @GetMapping
    public ResponseEntity<PageResult<BlogSearchResult>> list (@RequestParam(value = "keyword") String query,
                                                            @RequestParam(value = "sortType", required = false , defaultValue = "ACCURACY") SortType sortType,
                                                            @RequestParam(required = false, defaultValue = "1") @Max(50) Integer page,
                                                            @RequestParam(required = false, defaultValue = "1") @Max(100) Integer size) {
        List<BlogSearchResult> resultList = findBlogUsecase.getList(BlogSearchCommand.builder()
                .query(query)
                .sort(sortType)
                .page(page)
                .size(size)
                .build());
        return ResponseEntity.ok(new PageResult<>(page, size, resultList));
    }
}
