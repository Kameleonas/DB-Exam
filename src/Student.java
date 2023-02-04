import com.sun.istack.NotNull;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String surname;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "student", fetch = FetchType.EAGER)
    private List<StudentAnswers> studentAnswers;

    public Student() {
    }

    public Student(long id, String name, String surname, List<StudentAnswers> studentAnswers) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.studentAnswers = studentAnswers;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<StudentAnswers> getStudentAnswers() {
        return studentAnswers;
    }

    public void setStudentAnswers(List<StudentAnswers> studentAnswers) {
        this.studentAnswers = studentAnswers;
    }
}
