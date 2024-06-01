import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class QuizApp {
    static ArrayList<Question> questions;
    static Timer timer;
    static boolean timesup = false;
    static Scanner fileScanner;
    static int score = 0;

    public static void main(String[] args) {
        try {
            File file = new File("questions.txt");
            fileScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Error: questions.txt file not found");
            throw new RuntimeException(e);
        }

        int numberOfQues = Integer.parseInt(fileScanner.nextLine());
        questions = new ArrayList<>();

        for (int i = 0; i < numberOfQues; i++) {
            questions.add(new Question(
                    fileScanner.nextLine(),
                    fileScanner.nextLine(),
                    fileScanner.nextLine(),
                    fileScanner.nextLine(),
                    fileScanner.nextLine(),
                    fileScanner.nextLine()
            ));
        }

        askQuestions(questions, numberOfQues);
    }

    static void askQuestions(ArrayList<Question> questions, int numberOfQues) {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < numberOfQues; i++) {
            timesup = false;
            Question currentQuestion = questions.get(i);
            currentQuestion.showQuestion();
            timer = new Timer();

            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    System.out.println("Your time is up! You didn't answer in the given time.");
                    System.out.print("Enter any key to proceed: ");
                    timesup = true;
                    synchronized (this) {
                        this.notify();
                    }
                }
            };

            synchronized (task) {
                timer.schedule(task, 10000);
                String userAnswer = sc.nextLine();
                timer.cancel();

                if (userAnswer.equalsIgnoreCase(currentQuestion.getAns()) && !timesup) {
                    score += 10;
                    System.out.println("You got it right!");
                } else if (timesup) {
                    System.out.println("Too late!");
                } else {
                    System.out.println("Wrong answer.");
                }
            }
        }
        System.out.println("FINAL SCORE: " + score);
    }
}

class Question {
    String ques;
    String opt1;
    String opt2;
    String opt3;
    String opt4;
    String correctOpt;

    Question(String ques, String opt1, String opt2, String opt3, String opt4, String correctOpt) {
        this.ques = ques;
        this.opt1 = opt1;
        this.opt2 = opt2;
        this.opt3 = opt3;
        this.opt4 = opt4;
        this.correctOpt = correctOpt;
    }

    public void showQuestion() {
        System.out.println(ques);
        System.out.println("A. " + opt1);
        System.out.println("B. " + opt2);
        System.out.println("C. " + opt3);
        System.out.println("D. " + opt4);
    
    }

    public String getAns() {
        return correctOpt;
    }
}