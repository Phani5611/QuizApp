package com.spring.QuizApp.Controller;


import com.spring.QuizApp.Model.Question;
import com.spring.QuizApp.Repo.QuestionDAO;
import com.spring.QuizApp.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;


    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return  questionService.getAllQuestions();
    }

    @GetMapping("category/{topic}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable("topic") String topic) {
        return questionService.getQuestionByCategory(topic);
    }

    @PostMapping("postQuestion")
    public String postQuestion(@RequestBody Question question){
        questionService.postQuestion(question);
        return "question added";
    }
    @DeleteMapping("delete/{id}")
    public String deleteQuestion(@PathVariable("id") int id){
        questionService.deleteQuestion(id);
        return "question deleted successfully";
    }
    @PutMapping("/update/{id}")
    public String updateQuestion(@PathVariable ("id") int id ,@RequestBody Question question){
        questionService.updateQuestion(id,question);
        return "question updated";
    }
}