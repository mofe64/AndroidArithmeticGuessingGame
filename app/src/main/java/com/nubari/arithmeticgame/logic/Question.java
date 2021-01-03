package com.nubari.arithmeticgame.logic;

import java.security.SecureRandom;

public abstract class Question {
    StringBuilder stringBuilder = new StringBuilder();
    String questionString;
    Number answer;
    SecureRandom secureRandom = new SecureRandom();
    Number previousUserAnswer;
    int score = 0;
    int levelLimit = 0;
    int levelNumber = 0;

    public abstract String generateQuestion();

    public abstract void determineAnswer(int... operands);

    public void clearPreviousQuestionState() {
        stringBuilder = new StringBuilder();
        questionString = "";
        previousUserAnswer = null;
    }

    public Number getAnswer() {
        return answer;
    }

    public Number getUserAnswer() {
        return previousUserAnswer;
    }

    public void setUserAnswer(Number answer) {
        previousUserAnswer = answer;
    }

    public int getLevelLimit() {
        return levelLimit;
    }

    public boolean checkAnswer() {
        return getAnswer().equals(getUserAnswer());
    }

    public void updateScore() {
        if (checkAnswer()) {
            score++;
        }
    }

    public int getScore() {
        return score;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public String getQuestionString() {
        return questionString;
    }
}
