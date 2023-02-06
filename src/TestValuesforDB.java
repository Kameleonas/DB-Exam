import org.hibernate.Session;

import java.util.Random;

public class TestValuesforDB {
    public static void testInputsForDatabase() {
        Exams examQuestionOne = new Exams("question ONE: What is the capital of France?\n", "Paris*", "Berlin",
                "Madrid",
                randomCounter());
        Exams examQuestionTwo = new Exams("question TWO: Which country is the largest by land area?\n", "Russia*",
                "Canada",
                "China",
                randomCounter());
        Exams examQuestionThree = new Exams("question THREE: What is the largest ocean in the world?\n", "Pacific " +
                "Ocean*", "Atlantic Ocean",
                "Indian Ocean",
                randomCounter());
        Exams examQuestionFour = new Exams("question FOUR: What is the capital of Australia?\n", "Canberra*", "Sydney",
                "Melbourne",
                randomCounter());
        Exams examQuestionFive = new Exams("question FIVE: Which mountain range runs through Spain and Portugal?\n",
                "The Pyrenees*", "The Alps",
                "The Andes",
                randomCounter());
        Exams examQuestionSix = new Exams("question SIX: What is the highest mountain in the world?\n", "Mount " +
                "Everest*", "K2",
                "Lhotse",
                randomCounter());


        Student student1 = new Student("Antanas", "Antanėlis");
        Student student2 = new Student("Benas", "Benavicius");
        Student student3 = new Student("Ignas");

        StudentAnswers studentAnswer1 = new StudentAnswers("Paris*", examQuestionOne, student1);
        StudentAnswers studentAnswer2 = new StudentAnswers("Canada", examQuestionTwo, student1);
        StudentAnswers studentAnswer3 = new StudentAnswers("Atlantic Ocean", examQuestionThree, student1);
        StudentAnswers studentAnswer4 = new StudentAnswers("Canberra*", examQuestionFour, student1);
        StudentAnswers studentAnswer5 = new StudentAnswers("The Pyrenees*", examQuestionFive, student1);
        StudentAnswers studentAnswer6 = new StudentAnswers("Mount Everest*", examQuestionSix, student1);
        StudentAnswers studentAnswer11 = new StudentAnswers("Madrid", examQuestionOne, student2);
        StudentAnswers studentAnswer12 = new StudentAnswers("China", examQuestionTwo, student2);
        StudentAnswers studentAnswer13 = new StudentAnswers("Atlantic Ocean", examQuestionThree, student2);
        StudentAnswers studentAnswer14 = new StudentAnswers("Melbourne", examQuestionFour, student2);
        StudentAnswers studentAnswer15 = new StudentAnswers("The Alps", examQuestionFive, student2);
        StudentAnswers studentAnswer16 = new StudentAnswers("Mount Everest*", examQuestionSix, student2);
        StudentAnswers studentAnswer21 = new StudentAnswers("Paris*", examQuestionOne, student3);
        StudentAnswers studentAnswer22 = new StudentAnswers("Russia*", examQuestionTwo, student3);
        StudentAnswers studentAnswer23 = new StudentAnswers("Pacific Ocean*", examQuestionThree, student3);
        StudentAnswers studentAnswer24 = new StudentAnswers("Sydney", examQuestionFour, student3);
        StudentAnswers studentAnswer25 = new StudentAnswers("The Andes", examQuestionFive, student3);
        StudentAnswers studentAnswer26 = new StudentAnswers("Lhotse", examQuestionSix, student3);

        try (Session session = SessionFactoryMaker.getFactory().openSession()) {
            session.getTransaction().begin();
            session.persist(student1);
            session.persist(student2);
            session.persist(student3);
            session.persist(examQuestionOne);
            session.persist(examQuestionTwo);
            session.persist(examQuestionThree);
            session.persist(examQuestionFour);
            session.persist(examQuestionFive);
            session.persist(examQuestionSix);
            session.persist(studentAnswer1);
            session.persist(studentAnswer2);
            session.persist(studentAnswer3);
            session.persist(studentAnswer4);
            session.persist(studentAnswer5);
            session.persist(studentAnswer6);
            session.persist(studentAnswer11);
            session.persist(studentAnswer12);
            session.persist(studentAnswer13);
            session.persist(studentAnswer14);
            session.persist(studentAnswer15);
            session.persist(studentAnswer16);
            session.persist(studentAnswer21);
            session.persist(studentAnswer22);
            session.persist(studentAnswer23);
            session.persist(studentAnswer24);
            session.persist(studentAnswer25);
            session.persist(studentAnswer26);
            session.getTransaction().commit();
        }
    }

    private static int randomCounter() {
        Random rn = new Random();
        int answer = rn.nextInt(3) + 1;
        return answer;
    }
}
