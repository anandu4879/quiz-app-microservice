package com.anandu.question_service.service;

import com.anandu.question_service.dao.QuestionDao;
import com.anandu.question_service.model.QuestionWraper;
import com.anandu.question_service.model.Questions;
import com.anandu.question_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;
    public ResponseEntity<List<Questions>> getAllQuestion() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<List<Questions>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findBycategory(category), HttpStatus.OK);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> addQuestion(Questions questions) {
         questionDao.save(questions);
         return new ResponseEntity<>("Success",HttpStatus.CREATED);

    }

    public ResponseEntity<List<Integer>> getQuestionForQuiz(String categoryName, Integer numQuestion) {
        List<Integer> questions = questionDao.findRandomQuestionByCategory(categoryName, numQuestion);
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWraper>> getQuestionFromId(List<Integer> questionId) {
        List<QuestionWraper> wrappers = new ArrayList<>();
        List<Questions> questions =new ArrayList<>();
        for(Integer id: questionId)
        {
            questions.add(questionDao.findById(id).get());
        }
        for(Questions qustion:questions)
        {
            QuestionWraper wrapper=new QuestionWraper();
            wrapper.setId(qustion.getId());
            wrapper.setQuestionTitle(qustion.getQuestionTitle());
            wrapper.setOption1(qustion.getOption1());
            wrapper.setOption2(qustion.getOption2());
            wrapper.setOption3(qustion.getOption3());
            wrapper.setOption4(qustion.getOption4());
            wrappers.add(wrapper);
        }
        return new ResponseEntity<>(wrappers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int right=0;
        for (Response response :responses)
        {
            Questions questions=questionDao.findById(response.getId()).get();
            if(response.getResponse().equals(questions.getRightAnswer()))
                right++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
