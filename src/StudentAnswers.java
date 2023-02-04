import jakarta.persistence.*;

@Entity
@Table(name = "student_answers")
public class StudentAnswers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String answer;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "exams_id")
    private Exams exams;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private Student student;

    public StudentAnswers() {
    }

    public StudentAnswers(String answer, Exams exams, Student student) {
        this.answer = answer;
        this.exams = exams;
        this.student = student;
    }

    public long getId() {
        return id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
