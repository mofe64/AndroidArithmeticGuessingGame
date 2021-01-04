package com.nubari.arithmeticgame.logic;

public class LevelTwoQuestionDecorator extends QuestionDecorator {
    Question question;

    public LevelTwoQuestionDecorator(Question question) {
        this.question = question;
        this.levelLimit = 7;
        this.levelNumber = 2;
    }

    @Override
    public String generateQuestion() {
        clearPreviousQuestionState();
        int operand1 = secureRandom.nextInt(10);
        int operand2 = secureRandom.nextInt(10);
        int operand3 = secureRandom.nextInt(10);
        int operand4 = secureRandom.nextInt(15);
        int operand5 = secureRandom.nextInt(15);
        questionString = stringBuilder.append(operand1).append("+").append(operand2).append("+").append(operand3).append("+")
                .append(operand4).append("+").append(operand5).toString();
        determineAnswer(operand1, operand2, operand3, operand4, operand5);
        return questionString;
    }

    @Override
    public void determineAnswer(int... operands) {
        int result = 0;
        for (int operand : operands) {
            result += operand;
        }
        System.out.println("Level Two : Actual answer is  " + result);
        answer = result;
    }
}
