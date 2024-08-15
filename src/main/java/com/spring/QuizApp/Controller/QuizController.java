package com.spring.QuizApp.Controller;

import com.spring.QuizApp.Model.Question;
import com.spring.QuizApp.Model.QuestionWrapper;
import com.spring.QuizApp.Model.Response;
import com.spring.QuizApp.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;


    // Creates a question
    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int noQ,@RequestParam String title){
        return  quizService.createQuiz(category,noQ,title);
    }


    //Gets questions from the quiz of particular ID - Question Wrapper contains only question and options
    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable ("id") int id){
           return quizService.getQuizQuestions(id);
    }

    //Submit the result
    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable ("id") int id, @RequestBody List<Response> responses){
        return  quizService.calculateResult(id,responses);
    }

}
