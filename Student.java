import java.util.ArrayList;
import java.util.List;

public class Student extends Person {
    private List<Course> courseCode;

    public Student(String id, String name) {
        super(id, name);  
        this.courseCode = new ArrayList<>();
    }
    
    public void addCourseCode(Course course) { 
        courseCode.add(course); 
    }

    public List<Course> getCourseCode() { 
        return courseCode; 
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Course course : courseCode) {
            double finalGrade = course.calcFinalGrade();
            sb.append(id).append(", ")        
              .append(name).append(", ")     
              .append(course.getCourseCode()).append(", ") 
              .append(String.format("%.1f", finalGrade))  
              .append("\n");
        }
        return sb.toString();
    }
}
