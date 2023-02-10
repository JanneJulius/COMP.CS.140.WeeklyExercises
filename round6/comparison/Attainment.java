import java.util.Comparator;

public class Attainment implements Comparable<Attainment>{
  private String courseCode;
  private String studentNumber;
  private int grade;

  public Attainment(String courseCode, String studentNumber, int grade) {
    this.courseCode = courseCode;
    this.studentNumber = studentNumber;
    this.grade = grade;
  }

  @Override
  public int compareTo(Attainment other) {
    int cmp = studentNumber.compareTo(other.studentNumber);  
    if(cmp == 0) {
      cmp = courseCode.compareTo(other.courseCode);
    }
    return cmp;
  }

  @Override
  public String toString() {
    return courseCode + " " + studentNumber + " " + grade;
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

  public static final Comparator<Attainment> CODE_STUDENT_CMP = new Comparator<Attainment>() {
    @Override
    public int compare(Attainment a1, Attainment a2) {
      int codeCmp = a1.courseCode.compareTo(a2.courseCode);
      if (codeCmp != 0) {
        return codeCmp;
      } else {
        return a1.studentNumber.compareTo(a2.studentNumber);
      }
    }
  };
  
  public static final Comparator<Attainment> CODE_GRADE_CMP = new Comparator<Attainment>() {
    @Override
    public int compare(Attainment a1, Attainment a2) {
      int codeCmp = a1.courseCode.compareTo(a2.courseCode);
      if (codeCmp != 0) {
        return codeCmp;
      } else {
        return Integer.compare(a2.grade, a1.grade);
      }
    }
  };
}