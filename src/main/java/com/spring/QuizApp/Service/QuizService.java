package com.spring.QuizApp.Service;


import com.spring.QuizApp.Model.Question;
import com.spring.QuizApp.Model.QuestionWrapper;
import com.spring.QuizApp.Model.Quiz;
import com.spring.QuizApp.Model.Response;
import com.spring.QuizApp.Repo.QuizRepo;
import com.spring.QuizApp.Repo.QuestionDAO;
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
    private QuizRepo quizRepo;

    @Autowired
    private QuestionDAO questionDAO;

    public ResponseEntity<String> createQuiz(String category, int noQ, String title) {

        // Create the questions for quiz randomly
        List<Question> questions = questionDAO.findRandomQuestionByCategory(category, noQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepo.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    // Gets the question from DB to user
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
        //Why optional ? - if the data doesn't exist in DB, so we use optional - returns null
        //Get object of quiz from DB
        Optional<Quiz> quiz = quizRepo.findById(id);
        //We get the quiz question from quiz object
        List<Question> questionsFromDB = quiz.get().getQuestions();
        //We convert question ->  question wrapper
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for (Question q : questionsFromDB) {
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionsForUser.add(qw);
        }
        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }


    //Result - calculating the quiz response
    public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {
        Optional<Quiz> quiz = quizRepo.findById(id);
        List<Question> questions = quiz.get().getQuestions();
        //Score of the quiz
        int result = 0;
        int i = 0;

        // iterates through each response of the client and compares it with question
        for (Response response : responses) {
            if (response.getResponse().equals(questions.get(i).getCorrectAnswer())) {
                result++;
            }
            i++;
        }
        return  new ResponseEntity<>(result,HttpStatus.OK);
    }
}