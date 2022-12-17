package exercise1;

import javax.swing.*;
import java.util.Random;
import java.util.Stack;

public class Test {

    private final String[] questions = {
            "What does an instance variable describe?",
            "Which one of the following terms is correct for Java class that creates object of another class, then calls the object's methods?",
            "How many significant digits does a floating-point number have?",
            "There are variables for which each object of a class does not need its own separate copy. They are called ________.",
            "Converting an argument's value, if possible, to the type that the method expects to receive in its corresponding parameter is called ________."
    };

    private final String[] options = {
            "a. Behaviour of an object\nb. Properties of an object\nc. Height of an object\nd. Measurement of an object",
            "a. Subscriber class\nb. Concrete class\nc. Abstract class\nd. Driver class",
            "a. 15\nb. 10\nc. 7\nd. 5",
            "a. class variables\nb. constants\nc. local variables\nd. instance variables",
            "a. type casting\nb. argument truncation\nc. argument promotion\nd. argument conversion"
    };

    private final String[] correctAnswers = {
            "b. Properties of an object",
            "d. Driver class",
            "c. 7",
            "a. class variables",
            "c. argument promotion"
    };

    private final String[] congratulatoryMessage = {
            "Excellent!",
            "Good!",
            "Keep up the good work!",
            "Nice work!"
    };

    private final String[] wrongAnswerMessage = {
            "No. Please try again",
            "Wrong. Try once more",
            "Don't give up!",
            "That's not correct. Keep trying.."
    };

    // stores the questions that have already been simulated
    private static Stack<Integer> viewedQuestionsIndex;

    // stores the answers to the questions
    private static Stack<String> answersToQuestions;

    // stores the total correct answers
    private static int totalCorrectAnswers;

    // stores the total incorrect answers
    private static int totalIncorrectAnswers;

    public Test() {
        viewedQuestionsIndex =  new Stack<>();
        answersToQuestions = new Stack<>();
        totalCorrectAnswers = 0;
        totalIncorrectAnswers = 0;
    }

    // method to simulate questions
    private String simulateQuestion(){

        // generate random number to get random question in the array
        Random rnd = new Random();
        int randomNumber = rnd.nextInt(5);
        while (viewedQuestionsIndex.search(randomNumber) >= 0){     // check if the index position exists in the stack
            randomNumber = rnd.nextInt(5);
        }
        viewedQuestionsIndex.push(randomNumber);

        answersToQuestions.push(correctAnswers[randomNumber]);  // save the correct answer
        return questions[randomNumber] + "\n" + options[randomNumber];  // return the question
    }

    // method to interact with the user
    public void inputAnswer(){
        Object[] options = {"a", "b", "c", "d"};
        int userSelectedOption;

        for (int i = 0; i < 5; i++) {
            userSelectedOption = JOptionPane.showOptionDialog(
                    null,
                    simulateQuestion(),
                    "Question " + (i + 1),
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    null
            );

            JOptionPane.showMessageDialog(null, checkAnswer(options[userSelectedOption]));

            if (viewedQuestionsIndex.size() == 5) {
                double percentTotalOfCorrectAnswers = (totalCorrectAnswers / 5d) * 100;
                JOptionPane.showMessageDialog(
                        null,
                        "Total number of correct answers:     " + totalCorrectAnswers + "\nTotal number of incorrect answers:  " + totalIncorrectAnswers + "\nPercent total of correct answers:      " + percentTotalOfCorrectAnswers + "%",
                        "Grade",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        }
    }

    // method to check the user's answer
    private String checkAnswer(Object userAnswer){
        boolean isCorrect;

        if (userAnswer != null && answersToQuestions.peek().substring(0, 1).equals(userAnswer)){
            totalCorrectAnswers++;
            isCorrect = true;
        }
        else{
            totalIncorrectAnswers++;
            isCorrect = false;
        }
        return generateMessage(isCorrect);
    }

    // method to display random message based on the user answer
    private String generateMessage(boolean isCorrect){
        Random rnd = new Random();
        int randomNumber = rnd.nextInt(4);

        if (isCorrect){
            switch (randomNumber)
            {
                case 0:
                    return congratulatoryMessage[0];
                case 1:
                    return congratulatoryMessage[1];
                case 2:
                    return congratulatoryMessage[2];
                case 3:
                    return congratulatoryMessage[3];
            }
        }
        else {
            switch (randomNumber) {
                case 0:
                    return wrongAnswerMessage[0];
                case 1:
                    return wrongAnswerMessage[1];
                case 2:
                    return wrongAnswerMessage[2];
                case 3:
                    return wrongAnswerMessage[3];
            }
        }

        return null;
    }
}
