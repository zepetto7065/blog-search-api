package com.me.search.controller.popularity;

import com.me.search.controller.popularity.converter.PopularityConverter;
import com.me.search.controller.popularity.payload.PopularityApiResponse;
import com.me.search.port.in.FindPopularityUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/popularity")
@RequiredArgsConstructor
public class PopularityController {

    private final FindPopularityUsecase findPopularityUsecase;

    @GetMapping
    public ResponseEntity<List<PopularityApiResponse>> list() {
        return ResponseEntity.ok(PopularityConverter.INSTANCE.mapToApiResponse(findPopularityUsecase.findPopularityKeywords()));
    }
}
