package com.me.search.advisor;

import com.me.search.exception.KakaoSearchClientException;
import com.me.search.exception.NaverSearchClientException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import org.zalando.problem.spring.web.advice.ProblemHandling;

@Slf4j
@ControllerAdvice
public class ControllerAdvisor implements ProblemHandling {
    @ExceptionHandler
    public ResponseEntity<Problem> kakaoSearchClientExceptionHandler(final KakaoSearchClientException exception,
        final NativeWebRequest request){
        return create(Status.INTERNAL_SERVER_ERROR, exception, request);
    }

    @ExceptionHandler
    public ResponseEntity<Problem> naverSearchClientExceptionHandler(final NaverSearchClientException exception,
        final NativeWebRequest request){
        return create(Status.INTERNAL_SERVER_ERROR, exception, request);
    }

    @Override
    public void log(Throwable throwable, Problem problem, NativeWebRequest request, HttpStatus status) {
        if (status.is5xxServerError()) {
            log.error("{}, {}", status, request, throwable);
        }
    }
}
