package com.quiz;
import java.util.*;

public class quizapplication {
    private static final int Timelimit = 40; 
    private static int score = 0;
    private static boolean isTimeUp = false;

    static class Question {
        String question;
        String[] options;
        int correctAnswer;

        Question(String question, String[] options, int correctAnswer) {
            this.question = question;
            this.options = options;
            this.correctAnswer = correctAnswer;
        }
    }

    static class AnswerDetails {
        Question question;
        int userAnswer;

        AnswerDetails(Question question, int userAnswer) {
            this.question = question;
            this.userAnswer = userAnswer;
        }

        boolean isCorrect() {
            return userAnswer == question.correctAnswer;
        }
    }

    public static void main(String[] args) {
        List<Question> questions = new ArrayList<>();
        List<AnswerDetails> userAnswers = new ArrayList<>();  
        System.out.println("Welcome To QUIZ....");
        System.out.println("  ");
        questions.add(new Question("Who invented Java?", new String[]{"Thomas", "Monswen", "James Gosling", "Raha"}, 2));
        questions.add(new Question("In which year Java was invented?", new String[]{"1998", "1994", "1995", "1996"}, 2));
        questions.add(new Question("How many primitive data types are there in java?", new String[]{"5", "8", "9", "2"}, 1));
        questions.add(new Question("Class name and Constructor name both are same?", new String[]{"Yes", "No", "True", "Both 1 and 3"}, 3));
        questions.add(new Question("What is the use of packages?", new String[]{"To combine the files", "To segregate the java files in a java project", "Both 1 and 2", "None of the above"}, 1));

        startTimer();

        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < questions.size(); i++) {
            if (isTimeUp) {
                System.out.println("Time's up! Your score: " + score);
                displaySummary(userAnswers);  // Display summary if time is up
                return;
            }
            Question q = questions.get(i);
            System.out.println((i + 1) + ". " + q.question);
            for (int j = 0; j < q.options.length; j++) {
            
                System.out.println("   "+(j + 1) + ". " + q.options[j]);
            }

            System.out.print("Your answer : ");
            int userAnswer = scanner.nextInt() - 1;

            // Add user's answer details for the summary
            userAnswers.add(new AnswerDetails(q, userAnswer));
            if (userAnswer == q.correctAnswer) {
                score++;
                System.out.println("Correct ✅");
            } else {
                System.out.println("Wrong! The correct answer was: " + q.options[q.correctAnswer]);
            }
            System.out.println(); // Blank line for spacing
        }

        System.out.println("Quiz completed - Your score : " + score);
        displaySummary(userAnswers);  // Display summary at the end
        scanner.close();
    }

    // Method to display the summary of the quiz, including user answers
    private static void displaySummary(List<AnswerDetails> userAnswers) {
        System.out.println("\n***** Quiz Summary *****");
        for (int i = 0; i < userAnswers.size(); i++) {
            AnswerDetails answerDetails = userAnswers.get(i);
            Question q = answerDetails.question;

            System.out.println((i + 1) + ". " + q.question);
            System.out.println("   "+"Your answer: " + q.options[answerDetails.userAnswer]);
            System.out.println("   "+"Correct answer: " + q.options[q.correctAnswer]);

            if (answerDetails.isCorrect()) {
                System.out.println("   "+"Result: Correct ✅");
            } else {
                System.out.println("   "+"Result: Incorrect ❎");
            }
            System.out.println("  ");
        }
    }

    private static void startTimer() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                isTimeUp = true;  
            }
        }, Timelimit * 1000);
    }
}