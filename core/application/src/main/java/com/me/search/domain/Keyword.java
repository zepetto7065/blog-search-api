package com.me.search.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Keyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String word;

    private int searchCnt;

    public Keyword(String word, Integer searchCnt) {
        this.word = word;
        this.searchCnt = searchCnt;
    }
    public void plusSearchCnt() {
        this.searchCnt += 1;
    }
}
