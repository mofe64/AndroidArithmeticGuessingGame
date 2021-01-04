package com.nubari.arithmeticgame.logic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionImplTest {

    Question question;

    @BeforeEach
    void setUp() {
        question = new QuestionImpl();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testThatLevelNumberShouldBeSetToOneWhenQuestionImplInitialized() {
        assertEquals(1, question.getLevelNumber());
    }
    @Test
    void testThatLevelLimitShouldBeSetToFiveWhenQuestionImplInitialized() {
        assertEquals(5, question.getLevelLimit());
    }
    @Test
    void testThatUserScoreShouldBeSetToZeroWhenQuestionImplInitialized(){
        assertEquals(0, question.getScore());
    }

    @Test
    void testThatQuestionStringShouldNotBeNullOnceQuestionGenerated() {
        question.generateQuestion();
        assertNotNull(question.getQuestionString());
    }

    @Test
    void testThatQuestionAnswerShouldBeDeterminedAutomaticallyWhenQuestionGenerated() {
        question.generateQuestion();
        assertNotEquals(0, question.getAnswer());
    }

    @Test
    void testPreviousQuestionStateShouldBeClearedWhenANewQuestionGenerated() {
        String previousQuestion = question.generateQuestion();
        int previousUserAnswer = 50;
        question.setUserAnswer(previousUserAnswer);
        question.generateQuestion();
        assertNotEquals(previousQuestion, question.getQuestionString());
        assertNull(question.getUserAnswer());
    }

    @Test
    void testCheckAnswerShouldValidateUserGivenAnswer(){
        question.generateQuestion();
        Number correctAnswer = question.getAnswer();
        question.setUserAnswer(correctAnswer);
        assertTrue(question.checkAnswer());
        question.generateQuestion();
        int wrongAnswer = 100000;
        question.setUserAnswer(wrongAnswer);
        assertFalse(question.checkAnswer());
    }

    @Test
    void testUserScoreShouldBeIncreasedIfUserAnsweredCorrectly(){
        question.generateQuestion();
        Number correctAnswer = question.getAnswer();
        question.setUserAnswer(correctAnswer);
        question.updateScore();
        assertEquals(1, question.getScore());
        question.generateQuestion();
        correctAnswer = question.getAnswer();
        question.setUserAnswer(correctAnswer);
        question.updateScore();
        assertEquals(2, question.getScore());
    }

    @Test
    void testUserScoreShouldNotBeIncreasedIfUserGotAnswerWrong(){
        question.generateQuestion();
        int wrongAnswer = 1000000;
        question.setUserAnswer(wrongAnswer);
        question.updateScore();
        assertEquals(0, question.getScore());

    }
}