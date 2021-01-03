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
    Question question;
    String username;
    int userLevel = 1;
    boolean gameInProgress = false;
    String userCurrentAnswer;
    boolean userHasAnswered = false;
    int currentQuestionNo = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questioniare);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        question = determineLevel();
        if (question != null) {
            startGame(question);
        } else {
            gameOver();
        }

    }

    public void onClickGetAnswer(View view) {
        final EditText userAnswer = (EditText) findViewById(R.id.answer);
        userCurrentAnswer = userAnswer.getText().toString();
        userHasAnswered = true;
    }

    private void startGame(Question currentQuestion) {
        final String test = "";
        final TextView questionView = (TextView) findViewById(R.id.questionBox);
        final TextView scoreView = (TextView) findViewById(R.id.score);
        //      final EditText userAnswer = (EditText) findViewById(R.id.answer);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (gameInProgress) {
                    String questionString = currentQuestion.generateQuestion();
                    String userScore = "Score : " + currentQuestion.getScore();
                    scoreView.setText(userScore);
                    System.out.println("Level limit is " + currentQuestion.getLevelLimit());

                    questionView.setText(questionString);
                    System.out.println("Current q no is " + currentQuestionNo);
                    currentQuestionNo++;
                    int limit = currentQuestion.getLevelLimit();
                    if (currentQuestionNo > limit) {
                        gameInProgress = false;
                    }

                }
                handler.postDelayed(this, 5000);
            }
        });
    }

    private boolean check(int a, int b) {
        // System.out.println("Got here ");
        return a > b;
    }

    private Question determineLevel() {
        Question question = null;
        switch (userLevel) {
            case 1: {
                gameInProgress = true;
                question = new QuestionImpl();
                break;
            }
            case 2: {
                gameInProgress = true;
                question = new LevelTwoQuestionDecorator(new QuestionImpl());
                break;
            }
            default: {
                gameInProgress = false;
                break;
            }
        }
        return question;
    }

    private void gameOver() {
        final TextView questionView = (TextView) findViewById(R.id.questionBox);
        questionView.setText("GAME OVER");
    }
}