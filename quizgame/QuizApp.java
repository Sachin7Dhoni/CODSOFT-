import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizApp {
    static Scanner scanner = new Scanner(System.in);
    static int score = 0;

    static int questionIndex = 0;
    static boolean answered = false;
    static String[] questions = {
        "What is the capital of France?",
        "What is 2 + 2?",
        "What is the capital of Japan?"
    };
    static String[][] options = {
        {"1. Berlin", "2. Madrid", "3. Paris", "4. Rome"},

        {"1. 3", "2. 4", "3. 5", "4. 6"},
        {"1. Beijing", "2. Tokyo", "3. Seoul", "4. Bangkok"}
    };
    static int[] answers = {3, 2, 2};  // Correct options (1-based index)
    static int[] userAnswers = new int[questions.length];

    public static void main(String[] args) {
        System.out.println("Welcome to the Quiz!");

        for (questionIndex = 0; questionIndex < questions.length; questionIndex++) {
            answered = false;
            System.out.println("\nQuestion " + (questionIndex + 1) + ": " + questions[questionIndex]);
            for (String option : options[questionIndex]) {
                System.out.println(option);
            }

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (!answered) {
                        System.out.println("\nTime's up!");
                        userAnswers[questionIndex] = 0;  
                        timer.cancel();
                        nextQuestion();
                    }
                }
            }, 10000);  

            askQuestion();
            timer.cancel();  
        }

        showResults();
    }

    public static void askQuestion() {
        while (!answered) {
            System.out.print("Your answer (1-4): ");
            int answer = scanner.nextInt();
            if (answer >= 1 && answer <= 4) {
                userAnswers[questionIndex] = answer;
                answered = true;
                if (answer == answers[questionIndex]) {
                    score++;
                }
            } else {
                System.out.println("Invalid option. Please choose a number between 1 and 4.");
            }
        }
    }

    public static void nextQuestion() {
        if (questionIndex < questions.length - 1) {
            questionIndex++;
            System.out.println("\nNext Question:");
            System.out.println("\nQuestion " + (questionIndex + 1) + ": " + questions[questionIndex]);
            for (String option : options[questionIndex]) {
                System.out.println(option);
            }
            answered = false;
            askQuestion();
        } else {
            showResults();
        }
    }

    public static void showResults() {
        System.out.println("\nQuiz Over!");
        System.out.println("Your score: " + score + "/" + questions.length);
        for (int i = 0; i < questions.length; i++) {
            System.out.println("\nQuestion " + (i + 1) + ": " + questions[i]);
            System.out.println("Your answer: " + (userAnswers[i] == 0 ? "No answer" : options[i][userAnswers[i] - 1]));
            System.out.println("Correct answer: " + options[i][answers[i] - 1]);
        }
        scanner.close();
    }
}
