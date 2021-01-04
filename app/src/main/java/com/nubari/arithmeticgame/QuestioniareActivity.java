package com.nubari.arithmeticgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.nubari.arithmeticgame.logic.LevelTwoQuestionDecorator;
import com.nubari.arithmeticgame.logic.Question;
import com.nubari.arithmeticgame.logic.QuestionImpl;

public class QuestioniareActivity extends AppCompatActivity {
    Question currentQuestion;
    String username;
    int userLevel = 1;
    boolean gameInProgress = false;
    int answerSubmittedByUser = -1;
    int userScore = 0;
    int currentQuestionNo;
    int numberOfLevelsInGame = 2;
    TextView questionView;
    EditText userAnswerView;
    TextView scoreView;
    TextView levelView;
    boolean gameWon = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questioniare);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        questionView = (TextView) findViewById(R.id.questionBox);
        userAnswerView = (EditText) findViewById(R.id.answer);
        scoreView = (TextView) findViewById(R.id.scoree);
        levelView = (TextView) findViewById(R.id.level);
        startGame();
    }

    public void startGame() {
        String levelText = "Level :" + userLevel;
        levelView.setText(levelText);
        Question questionObj = determineLevel(userLevel);
        currentQuestion = questionObj;
        currentQuestionNo = 1;
        if (questionObj != null) {
            generateQuestion(questionObj);
        }
        scoreView.setText(String.valueOf(userScore));

    }

    public void onClickGetAnswer(View view) {
        scoreView.setText(String.valueOf(userScore));
        String givenAnswer = userAnswerView.getText().toString();
        userAnswerView.setText("");
        if (!givenAnswer.equals("")) {
            answerSubmittedByUser = Integer.parseInt(givenAnswer);
        }
        if (isUserAnswerCorrect(answerSubmittedByUser, currentQuestion)) {
            updateScore();
            scoreView.setText(String.valueOf(userScore));
        }
        if (currentQuestionNo <= currentQuestion.getLevelLimit()) {
            generateQuestion(currentQuestion);
        } else {
            userLevel++;
            if (userCanProceedToNextLevel()) {
                startGame();
            } else {
                if (gameWon) {
                    gameOverAndUserWon();
                    System.out.println("Game won");
                } else {
                    gameOverAndUserLost();
                }
            }
        }

    }

    public void generateQuestion(Question question) {
        questionView.setText(question.generateQuestion());
        currentQuestionNo++;
    }

    public Question determineLevel(int userLevel) {
        Question question;
        switch (userLevel) {
            case 1: {
                question = new QuestionImpl();
                break;
            }
            case 2: {
                question = new LevelTwoQuestionDecorator(new QuestionImpl());
                break;
            }
            default: {
                question = null;
            }
        }
        return question;
    }

    public boolean isUserAnswerCorrect(int userAnswer, Question question) {
        Number correctAnswer = question.getAnswer();
        return correctAnswer.intValue() == userAnswer;
    }

    public void updateScore() {
        userScore++;
    }

    public boolean userCanProceedToNextLevel() {
        if (userLevel <= numberOfLevelsInGame) {
            boolean userCanProceed = userScore > (currentQuestion.getLevelLimit() / 2);
            userScore = 0;
            return userCanProceed;
        } else {
            gameWon = true;
            return false;
        }
    }

    public void gameOverAndUserWon() {
        String winningMessage = "Congrats " + username + " You won !!!!!";
        questionView.setText(winningMessage);
    }

    public void gameOverAndUserLost() {
        String losingMessage = "Sorry " + username + " You lost";
        questionView.setText(losingMessage);
    }


}