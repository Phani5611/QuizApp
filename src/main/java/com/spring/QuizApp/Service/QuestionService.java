package com.spring.QuizApp.Service;


import com.spring.QuizApp.Model.Question;
import com.spring.QuizApp.Repo.QuestionDAO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionDAO questionRepo;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try{
            return new ResponseEntity<>( questionRepo.findAll(), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        //If something goes wrong return array and exception in console
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }



    public ResponseEntity<List<Question>> getQuestionByCategory(String topic) {
        try {
            return new ResponseEntity<>(questionRepo.findByCategory(topic), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<String> postQuestion(Question question) {
       try{
           questionRepo.save(question);
           return new ResponseEntity<>("success",HttpStatus.CREATED);
       }
       catch(Exception e){
           e.printStackTrace();
       }
       return new ResponseEntity<>("failed",HttpStatus.BAD_REQUEST);
    }






    //Delete a question at particular ID provided
    public void deleteQuestion(int id) {
        questionRepo.deleteById(id);
    }





    // Updates the existing question based on given ID
    @Transactional
    public void updateQuestion(int id,Question question)
    {   String questionTitle = question.getQuestionTitle();
        String option1 = question.getOption1();
        String option2 = question.getOption2();
        String option3 = question.getOption3();
        String option4=question.getOption4();
        String correctAnswer=question.getCorrectAnswer();
        String difficultyLevel=question.getDifficultyLevel();
        String category=question.getCategory();

        questionRepo.updateQuestion(id,questionTitle,option1,option2,option3,option4,correctAnswer,difficultyLevel,category);
    }
}
