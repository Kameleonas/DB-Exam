import org.hibernate.Session;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        SessionFactoryMaker.getFactory();
        runProgram();
    }

    private static void runProgram() {
        Scanner scanner = new Scanner(System.in);
        boolean runProgram = true;
        int completedExamCounter = 0;
        int[] answersAndQuestionsCount = new int[7];
        // 0 - correct answer
        // 1 - false answer 1
        // 2 - false answer 2
        // 3 - exam question count
        // 4 - times answer a was picked
        // 5 - times answer b was picked
        // 6 - times answer c was picked
        while (runProgram) {
            try {
                printMenu();
                String input = scanner.nextLine();
                while(input == ""){
                    input = scanner.nextLine();
                }
                switch (input) {
                    case "1" -> TestValuesforDB.testInputsForDatabase();
                    case "2" -> {
                        startExam(scanner, registerStudent(scanner), answersAndQuestionsCount);
                        completedExamCounter++;
                    }
                    case "3" -> addExamQuestion(scanner);
                    case "4" -> editExamQuestion(scanner);
                    case "5" -> statistics(scanner, completedExamCounter, answersAndQuestionsCount);
                    case "0" -> runProgram = false;
                    default -> System.out.println("Incorrect input! Try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("""
                Menu
                [1] - fill database with test data;
                [2] - begin exam;
                [3] - add additional question to the exam;
                [4] - edit exam questions;
                [5] - statistics;
                [0] - quit program;""");
    }

    private static Student registerStudent(Scanner scanner) {
        System.out.println("Input your Name:");
        String name = scanner.nextLine();
        while (Objects.equals(name, "")) {
            System.out.println("Name input is required - input your Name");
            name = scanner.nextLine();
        }
        System.out.println("Eneter your surname:");
        String surname = scanner.nextLine();
        return new Student(name, surname);
    }

    private static void startExam(Scanner scanner, Student student, int[] answersAndQuestionsCount) {
        Exams examFromDB;

        System.out.println("""
                There is only one correct answer.\s
                Input only [a], [b] or [c] as an answer\s
                Begin exam\s
                """);
        try (Session session = SessionFactoryMaker.getFactory().openSession()) {
            session.getTransaction().begin();
            session.persist(student);
            int counter = 1;
            long examQuestionCount = 0;
            while (session.get(Exams.class, counter) != null) {
                examQuestionCount = session.get(Exams.class, counter++).getId();
            }
            counter = 1;
            answersAndQuestionsCount[3] = (int) examQuestionCount;
            if (session.get(Exams.class, counter) != null) {
                for (long i = 0; i < examQuestionCount; i++) {
                    examFromDB = session.get(Exams.class, counter++);
                    System.out.println(examFromDB.getQuestion());
                    String[] questionsByOrder = examFromDB.displayAnswerOrder();
                    System.out.printf("a) %s \nb) %s \nc) %s \n\n", questionsByOrder[0], questionsByOrder[1],
                            questionsByOrder[2]);
                    String answer = scanner.nextLine();
                    String fullAnswer = null;
                    switch (answer) {
                        case "a" -> {
                            if (questionsByOrder[0].equals(examFromDB.getCorrectAnswer())) {
                                answersAndQuestionsCount[0]++;
                            } else if (questionsByOrder[0].equals(examFromDB.getSecondAnswer())) {
                                answersAndQuestionsCount[1]++;
                            } else {
                                answersAndQuestionsCount[2]++;
                            }
                            fullAnswer = questionsByOrder[0];
                            answersAndQuestionsCount[4]++;
                        }
                        case "b" -> {
                            if (questionsByOrder[1].equals(examFromDB.getCorrectAnswer())) {
                                answersAndQuestionsCount[0]++;
                            } else if (questionsByOrder[1].equals(examFromDB.getSecondAnswer())) {
                                answersAndQuestionsCount[1]++;
                            } else {
                                answersAndQuestionsCount[2]++;
                            }
                            fullAnswer = questionsByOrder[1];
                            answersAndQuestionsCount[5]++;
                        }
                        case "c" -> {
                            if (questionsByOrder[2].equals(examFromDB.getCorrectAnswer())) {
                                answersAndQuestionsCount[0]++;
                            } else if (questionsByOrder[2].equals(examFromDB.getSecondAnswer())) {
                                answersAndQuestionsCount[1]++;
                            } else {
                                answersAndQuestionsCount[2]++;
                            }
                            fullAnswer = questionsByOrder[2];
                            answersAndQuestionsCount[6]++;
                        }
                        case "" -> {
                            fullAnswer = "NO INPUT = FALSE ANSWER";
                        }
                        default -> System.out.println("Incorrect input! Try again.");
                    }
                    StudentAnswers studentAnswer = new StudentAnswers(fullAnswer, examFromDB, student);
                    session.persist(studentAnswer);
                }
            }
            session.getTransaction().commit();
        }
    }

    public static void addExamQuestion(Scanner scanner) {
        System.out.println("Adding new Question to the Exam");
        Exams newExamQuestion = new Exams(scanner);
        try (Session session = SessionFactoryMaker.getFactory().openSession()) {
            session.getTransaction().begin();
            session.persist(newExamQuestion);
            session.getTransaction().commit();
        }
    }

    public static void editExamQuestion(Scanner scanner) {
        showAllQuestions();
        System.out.println("Enter Question id to update:");
        int id = scanner.nextInt();
        try (Session session = SessionFactoryMaker.getFactory().openSession()) {
            session.getTransaction().begin();
            Exams examQuestionUpdate = session.get(Exams.class, id);
            examQuestionUpdate.printFieldsAvailable();
            System.out.println("Enter field id to update:");
            examQuestionUpdate.updateFieldByFieldId(Integer.parseInt(scanner.next()), scanner);
            session.merge(examQuestionUpdate);
            session.getTransaction().commit();
        }
    }

    public static void showAllQuestions() {
        System.out.println("All Questions:");
        try (Session session = SessionFactoryMaker.getFactory().openSession()) {
            List<Exams> exam = session.createQuery("from Exams", Exams.class).list();
            for (Exams e : exam) {
                System.out.println(e);
            }
        }
    }

    public static void statistics(Scanner scanner, int completedExamCounter, int[] correctFalseAndQuestionCount) {
        System.out.println("""
                Statistics Menu
                [1] - How many times the exam was solved?
                [2] - Average of correct answers in the exam
                [3] - How many different answer options are chosen for each of the questions?""");
        int userInput = scanner.nextInt();
        switch (userInput) {
            case 1 -> examSolveCounter(completedExamCounter);
            case 2 -> averageOfCorrectAnswers(completedExamCounter, correctFalseAndQuestionCount);
            case 3 -> examAnswerOptions(correctFalseAndQuestionCount);
        }
    }

    public static void examSolveCounter(int completedExamCounter) {
        System.out.printf("Exam was completed %d times\n", completedExamCounter);
    }

    public static void averageOfCorrectAnswers(int completedExamCounter, int[] correctFalseAndQuestionCount) {
        double average = (double) correctFalseAndQuestionCount[0] / completedExamCounter;
        System.out.printf("Average Correct answers out of %d questions: %.1f\n", correctFalseAndQuestionCount[3],
                average);
    }

    public static void examAnswerOptions(int[] correctFalseAndQuestionCount) {
        System.out.printf("Answer [A] was chosen - %d times; [B] - %d times and [C] - %d times\n",
                correctFalseAndQuestionCount[4], correctFalseAndQuestionCount[5], correctFalseAndQuestionCount[6]);
    }
}