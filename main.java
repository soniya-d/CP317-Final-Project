import java.io.*;
import java.util.*;

public class main {

    public static void main(String[] args) {
        try {
            Map<String, String> names = fileReader.loadNames("NameFile.txt");
            
            List<Student> students = fileReader.loadCourses("CourseFile.txt", names);

            students.sort(Comparator.comparing(Student::getId));

            try (BufferedWriter bw = new BufferedWriter(new FileWriter("OutputFile.txt"))) {
                for (Student student : students) {
                    bw.write(student.toString());
                }
            }
            System.out.println("Output generated successfully.");
        } catch (IllegalStateException e) {
            System.err.println("Program terminated due to incomplete data: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
