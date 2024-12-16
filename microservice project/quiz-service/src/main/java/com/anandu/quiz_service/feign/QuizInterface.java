package com.anandu.quiz_service.feign;

import com.anandu.quiz_service.model.QuestionWraper;
import com.anandu.quiz_service.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
    @GetMapping("questions/generate")
    public ResponseEntity<List<Integer>> getQuestionForQuiz
            (@RequestParam String categoryName, @RequestParam Integer numQuestion);


    @PostMapping("questions/getquestion")
    public ResponseEntity<List<QuestionWraper>> getQuestionById(@RequestBody List<Integer> questionId);

    @PostMapping("questions/getscore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
}