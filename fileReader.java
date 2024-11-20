import java.io.*;
import java.util.*;

// The FileReader class reads data from input files, parses and organizes it into smaller structures.
 
public class fileReader {

    // loads the student names from the file into the hashmap
    public static Map<String, String> loadNames(String filename) throws IOException {
        //create hashmap to store the mapping of student ID to names
        Map<String, String> names = new HashMap<>();
        
        // Try-with-resources to ensure BufferedReader is properly closed
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            // Read each line of the file
            while ((line = br.readLine()) != null) {
                // Split the line by ", " to separate student ID and name
                String[] parts = line.split(", ");
                if (parts.length == 2) { // Ensure the line has the correct format
                    names.put(parts[0], parts[1]); // Add ID and name to the map
                } else {
                    // Throw an exception if the line doesn't match the expected format
                    throw new IllegalArgumentException("Invalid name format: " + line);
                }
            }
        }
        // Return the populated map
        return names;
    }
  
    //loads the courses from the file and maps them to students using the name map.
    public static List<student> loadCourses(String filename, Map<String, String> nameMap) throws IOException {
        // store all student objects
        List<student> students = new ArrayList<>();
        // temp map to make sure each student is created only once
        Map<String, student> studentMap = new HashMap<>();

        // Try-with-resources to ensure BufferedReader is properly closed
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            // Read each line of the file
            while ((line = br.readLine()) != null) {
                // Split the line by ", " to parse course details
                String[] parts = line.split(", ");
                if (parts.length == 6) { // Ensure the line has the correct format
                    String id = parts[0]; // Extract student ID
                    String courseCode = parts[1]; // Extract course code
                    // Parse the scores into a double array
                    double[] scores = Arrays.stream(Arrays.copyOfRange(parts, 2, parts.length))
                                            .mapToDouble(Double::parseDouble).toArray();
                    // Retrieve or create the student object
                    student student = studentMap.computeIfAbsent(id, k -> 
                        new student(id, nameMap.getOrDefault(id, "Unknown")));
                    // Add the course to the student's course list
                    student.addCourseCode(new course(courseCode, scores));
                } else {
                    // Throw an exception if the line doesn't match the expected format
                    throw new IllegalArgumentException("Invalid course code format: " + line);
                }
            }
        }
        // Add all students from the map to the list
        students.addAll(studentMap.values());
        return students;
    }
}
