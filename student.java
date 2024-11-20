import java.util.ArrayList;
import java.util.List;

// student who extends from person, can have multiple courses
public class student extends person {
    // list to store the courses the student is enrolled in
    private List<course> courseCode;

    // initialize student
    public student(String id, String name) {
        // call constructor of the parent class (person)
        super(id, name);  
        // initialize a course list to store course codes
        this.courseCode = new ArrayList<>();
    }
    
    // add course to the student's list of courses
    public void addCourseCode(course course) { 
        // course object that will be added
        courseCode.add(course); 
    }

    // retrieve the list of courses the student is in
    public List<course> getCourseCode() { 
        // list of course objects
        return courseCode; 
    }

    // overrides the toString method to give a formatted string of students info and the courses with final grades
    @Override
    public String toString() {
        // use StringBuilder for string concatenation
        StringBuilder sb = new StringBuilder();
        
        // loop through each course the student is in
        for (course course : courseCode) {
            // calculate the final grade for that course
            double finalGrade = course.calcFinalGrade();
            
            // add student and course info in the specified format
            sb.append(id).append(", ")        // add student ID
              .append(name).append(", ")     // add student name
              .append(course.getCourseCode()).append(", ") // add course code
              .append(String.format("%.1f", finalGrade))  // add the formatted final grade
              .append("\n"); // New line for every course
        }
        
        // return the formatted string
        return sb.toString();
    }
}
