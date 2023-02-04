import com.sun.istack.NotNull;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "exams")
public class Exams {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
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

    public Exams(long id, String question, String correctAnswer, String secondAnswer, String thirdAnswer,
                 int answerOrder, List<StudentAnswers> studentAnswers) {
        this.id = id;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.secondAnswer = secondAnswer;
        this.thirdAnswer = thirdAnswer;
        this.answerOrder = answerOrder;
        this.studentAnswers = studentAnswers;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
