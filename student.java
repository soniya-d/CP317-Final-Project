import java.util.ArrayList;
import java.util.List;

public class student {
    private String id;
    private String name;
    private List<course> courseCode;

    public student(String id, String name) {
        this.id = id;
        this.name = name;
        this.courseCode = new ArrayList<>();
    }

    public String getId() { 
        return id; 
    }

    public String getName() { 
        return name; 
    }

    public void addCourseCode(course courses) { 
        courseCode.add(courses); 
    }
    
    public List<course> getCourseCode() { 
        return courseCode; 
    }

    public String formatOutput() {
        StringBuilder studentInfo = new StringBuilder();
        for (course course : courseCode) {
            double finalGrade = course.calcFinalGrade();
            studentInfo.append(id).append(", ").append(name).append(", ").append(course.getCourseCode())
            .append(", ").append(String.format("%.1f", finalGrade)).append("\n");
        }
        return studentInfo.toString();
    }
}
