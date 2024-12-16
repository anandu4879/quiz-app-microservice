package com.anandu.quiz_service.controller;


import com.anandu.quiz_service.model.QuestionWraper;
import com.anandu.quiz_service.model.Response;
import com.anandu.quiz_service.model.QuizDto;
import com.anandu.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    QuizService quizeService;
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){
        return quizeService.createQuiz(quizDto.getCategoryName(),quizDto.getNumQuestion(),quizDto.getTitle());
    }
    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWraper>> getQuizQuestion(@PathVariable Integer id){
        return quizeService.getQuizebyid(id);
    }
    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id,@RequestBody List<Response> response)
    {
        return quizeService.calculateResult(id,response);
    }
}

