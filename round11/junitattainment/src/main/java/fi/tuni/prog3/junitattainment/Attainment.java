package fi.tuni.prog3.junitattainment;

public class Attainment implements Comparable<Attainment> {

    private String courseCode;
    private String studentNumber;
    private int grade;

    public Attainment(String courseCode, String studentNumber, int grade) {
        if (courseCode == null || studentNumber == null || grade < 0 || grade > 5) {
            throw new IllegalArgumentException();
        }
        this.courseCode = courseCode;
        this.studentNumber = studentNumber;
        this.grade = grade;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public int getGrade() {
        return grade;
    }

    public String toString() {
        return courseCode + " " + studentNumber + " " + grade;
    }

    public int compareTo(Attainment other) {
        int result = studentNumber.compareTo(other.studentNumber);
        if (result == 0) {
            result = courseCode.compareTo(other.courseCode);
        }
        return result;
    }
}
