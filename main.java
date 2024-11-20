import java.io.*;
import java.util.*;

// entry point for the program, starts the reading of the input files, processes the data then generates the output file.
public class main {

    public static void main(String[] args) {
        try {
            // load the student IDs to names from the txt file
            Map<String, String> names = fileReader.loadNames("NameFile.txt");

            // load the list of students and their course info from the txt file
            List<student> students = fileReader.loadCourses("CourseFile.txt", names);

            // sort the list by student IDs in ascending order
            students.sort(Comparator.comparing(student::getId));

            // write the sorted data to the output file 
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("OutputFile.txt"))) {
                for (student student : students) {
                    bw.write(student.toString()); // Use the overridden toString() method of Student
                }
            }

            // print message that the generation of the output file was successful
            System.out.println("Output generated successfully.");
        } catch (Exception e) {
            // if any exceptions occurr, catch and display that an error occurred during the process
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
