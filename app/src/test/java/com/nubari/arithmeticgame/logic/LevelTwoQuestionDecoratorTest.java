package com.nubari.arithmeticgame.logic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LevelTwoQuestionDecoratorTest {
    Question question;
    Question levelTwoQuestion;

    @BeforeEach
    void setUp() {
        question = new QuestionImpl();
        levelTwoQuestion = new LevelTwoQuestionDecorator(question);
    }

    @AfterEach
    void tearDown() {
    }
    @Test
    void testThatLevelNumberShouldBeSetToTwoWhenLevelTwoQuestionInitialized() {
        assertEquals(2, levelTwoQuestion.getLevelNumber());
    }
    @Test
    void testThatLevelLimitShouldBeSetToSevenWhenLevelTwoQuestionInitialized() {
        assertEquals(7, levelTwoQuestion.getLevelLimit());
    }

    @Test
    void generateQuestion() {
        levelTwoQuestion.generateQuestion();
        assertNotNull(levelTwoQuestion.getQuestionString());
    }

    @Test
    void determineAnswer() {
        levelTwoQuestion.generateQuestion();
        //System.out.println(levelTwoQuestion.getAnswer().toString());
        assertNotNull(levelTwoQuestion.getAnswer());
    }

}