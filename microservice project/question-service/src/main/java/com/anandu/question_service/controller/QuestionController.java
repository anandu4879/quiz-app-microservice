package com.anandu.question_service.controller;


import com.anandu.question_service.model.QuestionWraper;
import com.anandu.question_service.model.Questions;
import com.anandu.question_service.model.Response;
import com.anandu.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("allquestion")
    public ResponseEntity<List<Questions>> getAllQuestion(){
        return questionService.getAllQuestion();
    }
    @GetMapping("category/{category}")
    public ResponseEntity<List<Questions>> getQuestionsbyCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }
    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Questions questions){
        return questionService.addQuestion(questions);
    }
    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionForQuiz
            (@RequestParam String categoryName,@RequestParam Integer numQuestion)
    {
        return questionService.getQuestionForQuiz(categoryName,numQuestion);
    }

    @PostMapping("getquestion")
    public ResponseEntity<List<QuestionWraper>> getQuestionById(@RequestBody List<Integer> questionId){
        return questionService.getQuestionFromId(questionId);
    }
    @PostMapping("getscore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return questionService.getScore(responses);
    }
}
