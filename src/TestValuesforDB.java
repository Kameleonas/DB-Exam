import org.hibernate.Session;
import java.util.Random;

public class TestValuesforDB {
    public static void testInputsForDatabase() {
        Exams examQuestionOne = new Exams("question ONE: the first question?", "TEST1_Correct", "TEST1_False1",
                "TEST1_False2",
                randomCounter());
        Exams examQuestionTwo = new Exams("question TWO: the second question?", "TEST2_Correct", "TEST2_False1",
                "TEST2_False2",
                randomCounter());
        Exams examQuestionThree = new Exams("question THREE: the third question?", "TEST3_Correct", "TEST3_False1",
                "TEST3_False2",
                randomCounter());
        Exams examQuestionFour = new Exams("question FOUR: the fourth question?", "TEST4_Correct", "TEST4_False1",
                "TEST4_False2",
                randomCounter());
        Exams examQuestionFive = new Exams("question FIVE: the fifth question?", "TEST5_Correct", "TEST5_False1",
                "TEST5_False2",
                randomCounter());
        Exams examQuestionSix = new Exams("question SIX: the sixth question?", "TEST6_Correct", "TEST6_False1",
                "TEST6_False2",
                randomCounter());

        Student student1 = new Student("Antanas", "Antanėlis");
        Student student2 = new Student("Benas", "Benavicius");
        Student student3 = new Student("Domas", "Domenas");
        Student student4 = new Student("Edgaras", "Edgaravicius");
        Student student5 = new Student("Gabrielius", "Gabrevicius");
        Student student6 = new Student("Ignas");

        StudentAnswers studentAnswer1 = new StudentAnswers("TEST1_Correct", examQuestionOne, student1);
        StudentAnswers studentAnswer2 = new StudentAnswers("TEST2_False1", examQuestionTwo, student1);
        StudentAnswers studentAnswer3 = new StudentAnswers("TEST3_False2", examQuestionThree, student1);
        StudentAnswers studentAnswer4 = new StudentAnswers("TEST4_Correct", examQuestionFour, student2);
        StudentAnswers studentAnswer5 = new StudentAnswers("TEST5_False1", examQuestionFive, student2);
        StudentAnswers studentAnswer6 = new StudentAnswers("TEST6_Correct", examQuestionSix, student2);
        StudentAnswers studentAnswer7 = new StudentAnswers("TEST1_False2", examQuestionOne, student3);
        StudentAnswers studentAnswer8 = new StudentAnswers("TEST2_False1", examQuestionTwo, student3);
        StudentAnswers studentAnswer9 = new StudentAnswers("TEST3_Correct", examQuestionThree, student3);
        StudentAnswers studentAnswer10 = new StudentAnswers("TEST4_Correct", examQuestionFour, student4);
        StudentAnswers studentAnswer11 = new StudentAnswers("TEST5_False2", examQuestionFive, student4);
        StudentAnswers studentAnswer12 = new StudentAnswers("TEST6_Correct", examQuestionSix, student4);
        StudentAnswers studentAnswer13 = new StudentAnswers("TEST1_False1", examQuestionOne, student5);
        StudentAnswers studentAnswer14 = new StudentAnswers("TEST2_False2", examQuestionTwo, student5);
        StudentAnswers studentAnswer15 = new StudentAnswers("TEST3_False1", examQuestionThree, student5);
        StudentAnswers studentAnswer16 = new StudentAnswers("TEST4_Correct", examQuestionFour, student6);
        StudentAnswers studentAnswer17 = new StudentAnswers("TEST5_False2", examQuestionFive, student6);
        StudentAnswers studentAnswer18 = new StudentAnswers("TEST6_False1", examQuestionSix, student6);

        try (Session session = SessionFactoryMaker.getFactory().openSession()) {
            session.getTransaction().begin();
            session.persist(examQuestionOne);
            session.persist(examQuestionTwo);
            session.persist(examQuestionThree);
            session.persist(examQuestionFour);
            session.persist(examQuestionFive);
            session.persist(examQuestionSix);
            session.persist(student1);
            session.persist(student2);
            session.persist(student3);
            session.persist(student4);
            session.persist(student5);
            session.persist(student6);
            session.persist(studentAnswer1);
            session.persist(studentAnswer2);
            session.persist(studentAnswer3);
            session.persist(studentAnswer4);
            session.persist(studentAnswer5);
            session.persist(studentAnswer6);
            session.persist(studentAnswer7);
            session.persist(studentAnswer8);
            session.persist(studentAnswer9);
            session.persist(studentAnswer10);
            session.persist(studentAnswer11);
            session.persist(studentAnswer12);
            session.persist(studentAnswer13);
            session.persist(studentAnswer14);
            session.persist(studentAnswer15);
            session.persist(studentAnswer16);
            session.persist(studentAnswer17);
            session.persist(studentAnswer18);
            session.getTransaction().commit();
        }
    }
    private static int randomCounter() {
        Random rn = new Random();
        int answer = rn.nextInt(3) + 1;
        return answer;
    }
}
