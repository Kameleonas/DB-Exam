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

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public long getId() {
        return id;
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
