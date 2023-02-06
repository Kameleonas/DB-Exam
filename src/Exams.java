import jakarta.persistence.*;

import java.util.List;
import java.util.Scanner;

@Entity
@Table(name = "exams")
public class Exams {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String question;

    @Column(name = "correct_answer", nullable = false)
    private String correctAnswer;

    @Column(name = "second_answer", nullable = false)
    private String secondAnswer;

    @Column(name = "third_answer", nullable = false)
    private String thirdAnswer;

    @Column(name = "answer_order", nullable = false)
    private int answerOrder;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exams", fetch = FetchType.EAGER)
    private List<StudentAnswers> studentAnswers;

    public Exams() {
    }

    public Exams(Scanner scanner) {
        System.out.println("Enter new Exam Question:");
        this.question = scanner.nextLine();
        System.out.println("Enter the correct answer for new Exam Question");
        this.correctAnswer = scanner.nextLine();
        System.out.println("Enter False answer for new Exam Question");
        this.secondAnswer = scanner.nextLine();
        System.out.println("Enter second False answer for new Exam Question");
        this.thirdAnswer = scanner.nextLine();
        System.out.println("""
                Select answer order - input [1],[2] or [3]:
                [1] - Correct answer position a);
                [2] - Correct answer position b);
                [3] - Correct answer position c);""");
        this.answerOrder = scanner.nextInt();
    }

    public Exams(String question, String correctAnswer, String secondAnswer, String thirdAnswer,
                 int answerOrder) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.secondAnswer = secondAnswer;
        this.thirdAnswer = thirdAnswer;
        this.answerOrder = answerOrder;
    }

    public long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getSecondAnswer() {
        return secondAnswer;
    }

    public void setSecondAnswer(String secondAnswer) {
        this.secondAnswer = secondAnswer;
    }

    public String getThirdAnswer() {
        return thirdAnswer;
    }

    public void setThirdAnswer(String thirdAnswer) {
        this.thirdAnswer = thirdAnswer;
    }

    public int getAnswerOrder() {
        return answerOrder;
    }

    public void setAnswerOrder(int answerOrder) {
        this.answerOrder = answerOrder;
    }

    public List<StudentAnswers> getStudentAnswers() {
        return studentAnswers;
    }

    public void setStudentAnswers(List<StudentAnswers> studentAnswers) {
        this.studentAnswers = studentAnswers;
    }

    @Override
    public String toString() {
        return String.format("|ID:%3o| %s", this.getId(), this.getQuestion());
    }

    public String[] displayAnswerOrder() {
        String[] answers = new String[3];
        switch (this.answerOrder) {
            case 1 -> {
                answers[0] = this.correctAnswer;
                answers[1] = this.secondAnswer;
                answers[2] = this.thirdAnswer;
            }
            case 2 -> {
                answers[0] = this.secondAnswer;
                answers[1] = this.correctAnswer;
                answers[2] = this.thirdAnswer;
            }
            case 3 -> {
                answers[0] = this.thirdAnswer;
                answers[1] = this.secondAnswer;
                answers[2] = this.correctAnswer;
            }
        }
        return answers;
    }

    public void printFieldsAvailable() {
        System.out.println("[0] Question: " + this.question);
        System.out.println("[1] Correct answer: " + this.correctAnswer);
        System.out.println("[2] False answer 1: " + this.secondAnswer);
        System.out.println("[3] False answer 2: " + this.thirdAnswer);
        System.out.println("[4] Answer order: " + this.answerOrder);
    }

    public void updateFieldByFieldId(long questionID, Scanner scanner) {
        switch ((int) questionID) {
            case 0 -> {
                System.out.println("Input Question to Update");
                this.setQuestion(scanner.nextLine());
            }
            case 1 -> {
                System.out.println("Input Correct answer to Update");
                this.setCorrectAnswer(scanner.nextLine());
            }
            case 2 -> {
                System.out.println("Input first False answer to Update");
                this.setSecondAnswer(scanner.nextLine());
            }
            case 3 -> {
                System.out.println("Input second False answer to Update");
                this.setThirdAnswer(scanner.nextLine());
            }
            case 4 -> {
                System.out.println("Input new answer order (possible inputs [1],[2] or [3])");
                this.setAnswerOrder(scanner.nextInt());
            }
            default -> System.out.println("Unknown field!");
        }
    }
}
