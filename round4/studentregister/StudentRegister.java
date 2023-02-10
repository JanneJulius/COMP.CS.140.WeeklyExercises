import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class StudentRegister {
  private ArrayList<Student> students;
  private ArrayList<Course> courses;
  private ArrayList<Attainment> attainments;
  public static final String BY_NAME = "by name";
  public static final String BY_CODE = "by code";

  public StudentRegister() {
    students = new ArrayList<Student>();
    courses = new ArrayList<Course>();
    attainments = new ArrayList<Attainment>();
  }

  public ArrayList<Student> getStudents() {
    Collections.sort(students, new Comparator<Student>() {
      public int compare(Student s1, Student s2) {
        return s1.getName().compareTo(s2.getName());
      }
    });
    return students;
  }

  public ArrayList<Course> getCourses() {
    Collections.sort(courses, new Comparator<Course>() {
      public int compare(Course c1, Course c2) {
        return c1.getName().compareTo(c2.getName());
      }
    });
    return courses;
  }

  public void addStudent(Student student) {
    students.add(student);
  }

  public void addCourse(Course course) {
    courses.add(course);
  }

  public void addAttainment(Attainment att) {
    attainments.add(att);
  }

  public void printStudentAttainments(String studentNumber, String order) {
    ArrayList<Attainment> studentAttainments = new ArrayList<Attainment>();
    for (Attainment att : attainments) {
      if (att.getStudentNumber().equals(studentNumber)) {
        studentAttainments.add(att);
      }
    }
    if (studentAttainments.size() != 0){
      if(order.equals(BY_NAME)){
        studentAttainments.sort((a1, a2) -> {
          Course c1 = courses.stream()
            .filter(c -> c.getCode().equals(a1.getCourseCode()))
            .findFirst()
            .orElse(null);

          Course c2 = courses.stream()
            .filter(c -> c.getCode().equals(a2.getCourseCode()))
            .findFirst()
            .orElse(null);
          return c1.getName().compareTo(c2.getName());
        });
      }
      else if(order.equals(BY_CODE)){
        studentAttainments.sort((a1, a2) -> {
          Course c1 = courses.stream()
            .filter(c -> c.getCode().equals(a1.getCourseCode()))
            .findFirst()
            .orElse(null);

          Course c2 = courses.stream()
            .filter(c -> c.getCode().equals(a2.getCourseCode()))
            .findFirst()
            .orElse(null);
          return c1.getCode().compareTo(c2.getCode());
        });
      } 
      Student s = students.stream()
        .filter(stu -> stu.getStudentNumber().equals(studentNumber))
        .findFirst()
        .orElse(null);
      System.out.println(s.getName()+ " (" + studentNumber + "):");
      for (Attainment att : studentAttainments) {
        Course c = courses.stream()
          .filter(cu -> cu.getCode().equals(att.getCourseCode()))
          .findFirst()
          .orElse(null);
        System.out.println("  "+ att.getCourseCode()+ " "+ c.getName()+ ": "+att.getGrade());
      }
    }
    else{
      System.out.println("Unknown student number: "+ studentNumber);
      return;
    }
  }

  public void printStudentAttainments(String studentNumber) {
    ArrayList<Attainment> studentAttainments = new ArrayList<Attainment>();
    for (Attainment att : attainments) {
      if (att.getStudentNumber().equals(studentNumber)) {
        studentAttainments.add(att);
      }
    }
    if (studentAttainments.size() != 0){
      Student s = students.stream()
        .filter(stu -> stu.getStudentNumber().equals(studentNumber))
        .findFirst()
        .orElse(null);
      System.out.println(s.getName()+ " (" + studentNumber + "):");
      for (Attainment att : studentAttainments) {
        Course c = courses.stream()
          .filter(cu -> cu.getCode().equals(att.getCourseCode()))
          .findFirst()
          .orElse(null);
        System.out.println("  "+ att.getCourseCode()+ " "+ c.getName()+ ": "+att.getGrade());
      }
    }
    else{
      System.out.println("Unknown student number: "+ studentNumber);
      return;
    }

  }
}
