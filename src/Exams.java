import jakarta.persistence.*;

import java.util.Arrays;
import java.util.List;

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

    public String[] displayAnswerOrder(){
        String[] answers = new String[3];
        switch (this.answerOrder){
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
}
