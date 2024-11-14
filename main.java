// MainProgram.java
import java.io.*;
import java.util.*;

public class main {

    public static void main(String[] args) {
        try {
            Map<String, String> names = fileReader.loadNames("NameFile.txt");
            List<student> students = fileReader.loadCourses("CourseFile.txt", names);

            students.sort(Comparator.comparing(student::getId));

            try (BufferedWriter bw = new BufferedWriter(new FileWriter("OutputFile.txt"))) {
                for (student student : students) {
                    bw.write(student.formatOutput());
                }
            }
            System.out.println("Output generated successfully.");
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
