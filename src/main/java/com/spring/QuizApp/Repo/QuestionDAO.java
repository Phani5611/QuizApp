package com.spring.QuizApp.Repo;

import com.spring.QuizApp.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDAO extends JpaRepository<Question,Integer> {

    List<Question> findByCategory(String topic);


    //For updating an existing question provided with ID in the URL
    @Modifying
    //@Query("update question q set q.question_title=: questionTitle,q.option1= :option1,q.option2= :option2,q.option3 =:option3,q.option4=:option4,q.correctAnswer=:correct_answer,q.difficulty_level=:difficulty_level,q.category=:category where q.id= :id" )
    @Query("UPDATE Question q SET q.questionTitle = :questionTitle, q.option1 = :option1, q.option2 = :option2, q.option3 = :option3, q.option4 = :option4, q.correctAnswer = :correctAnswer, q.difficultyLevel = :difficultyLevel, q.category = :category WHERE q.id = :id")
    void updateQuestion(
            @Param("id") int id,
            @Param("questionTitle") String questionTitle,
            @Param("option1") String option1,
            @Param("option2") String option2,
            @Param("option3") String option3,
            @Param("option4") String option4,
            @Param("correctAnswer") String correctAnswer,
            @Param("difficultyLevel") String difficultyLevel,
            @Param("category") String category
    );


    @Query(value = "SELECT * FROM Question q WHERE q.category=:category ORDER BY RANDOM() LIMIT :noQ",nativeQuery = true)
    List<Question> findRandomQuestionByCategory(
            @Param("category") String category,
            @Param("noQ") int noQ);
}
