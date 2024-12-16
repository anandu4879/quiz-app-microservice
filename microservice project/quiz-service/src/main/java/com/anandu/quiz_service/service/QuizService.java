package com.anandu.quiz_service.service;

import com.anandu.quiz_service.dao.QuizDao;
import com.anandu.quiz_service.feign.QuizInterface;
import com.anandu.quiz_service.model.QuestionWraper;
import com.anandu.quiz_service.model.Quiz;
import com.anandu.quiz_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;


    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Integer> questions = quizInterface.getQuestionForQuiz(category ,numQ).getBody();
        Quiz quiz =new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionId(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("SUCCESS", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWraper>> getQuizebyid(Integer id) {
      Quiz quiz= quizDao.findById(id).get();
      List<Integer> questionId = quiz.getQuestionId();
      ResponseEntity<List<QuestionWraper>> questions = quizInterface.getQuestionById(questionId);
      return questions;
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        ResponseEntity<Integer> score = quizInterface.getScore(responses);
        return score;
    }

}
