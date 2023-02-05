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
        while (runProgram) {
            try {
                printMenu();
                String input = scanner.nextLine();
                switch (input) {
                    case "1" -> TestValuesforDB.testInputsForDatabase();
                    case "2" -> startExam(scanner, registerStudent(scanner));
//                    case "3" -> addExamQuestion(scanner);
//                    case "4" -> editExamQuestion(scanner);
//                    case "5" -> statistics(scanner);
                    case "0" -> runProgram = false;
                    default -> System.out.println("Incorrect input! Try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
        scanner.close();
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





    private static void startExam(Scanner scanner, Student student) {
        Exams examFromDB;
        boolean finalAnswer;////////////////////////////////////////////////////////// EDIT!!!
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
                                finalAnswer = true;
                            }
                            finalAnswer = false;
                            fullAnswer = questionsByOrder[0];
                        }
                        case "b" -> {
                            if (questionsByOrder[1].equals(examFromDB.getCorrectAnswer())) {
                                finalAnswer = true;
                            }
                            finalAnswer = false;
                            fullAnswer = questionsByOrder[1];
                        }
                        case "c" -> {
                            if (questionsByOrder[2].equals(examFromDB.getCorrectAnswer())) {
                                finalAnswer = true;
                            }
                            finalAnswer = false;
                            fullAnswer = questionsByOrder[2];
                        }
                        case "" -> {
                            finalAnswer = false;
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
}