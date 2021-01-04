package com.nubari.arithmeticgame.logic;

public class QuestionImpl extends Question {
    public QuestionImpl() {
        levelNumber = 1;
        levelLimit = 3;
    }

    @Override
    public String generateQuestion() {
        clearPreviousQuestionState();
        int operand1 = secureRandom.nextInt(10);
        int operand2 = secureRandom.nextInt(10);
        int operand3 = 1 + secureRandom.nextInt(10);
        questionString = stringBuilder.append(operand1).append("+").append(operand2).append("+").append(operand3).toString();
        determineAnswer(operand1, operand2, operand3);
        return questionString;
    }

    @Override
    public void determineAnswer(int... operands) {
        int result = 0;
        for (int operand : operands) {
            result += operand;
        }
        System.out.println("Actual answer is " + result);
        answer = result;
    }
}
