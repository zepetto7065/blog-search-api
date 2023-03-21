package com.me.search.keyword;

import com.me.search.domain.Keyword;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface KeywordJpaRepository extends CrudRepository<Keyword, Long> {
    Optional<Keyword> findByWord(@Param("word") String query);
    List<Keyword> findTop10ByOrderBySearchCntDesc();
}
