package com.spring.QuizApp.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity

@Table(name = "question")
public class Question {
    @Id
    private int id;
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String correctAnswer;
    private String difficultyLevel;
    private String category;


}
