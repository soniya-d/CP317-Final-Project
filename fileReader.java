import java.io.*;
import java.util.*;

public class fileReader {

    public static Map<String, String> loadNames(String filename) throws IOException {
        Map<String, String> names = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 2) {
                    names.put(parts[0], parts[1]);
                } else {
                    throw new IllegalArgumentException("Invalid name format: " + line);
                }
            }
        }
        return names;
    }

    public static List<student> loadCourses(String filename, Map<String, String> nameMap) throws IOException {
        List<student> students = new ArrayList<>();
        Map<String, student> studentMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 6) {
                    String id = parts[0];
                    String courseCode = parts[1];
                    double[] scores = Arrays.stream(Arrays.copyOfRange(parts, 2, parts.length))
                                            .mapToDouble(Double::parseDouble).toArray();
                    student student = studentMap.computeIfAbsent(id, k -> 
                        new student(id, nameMap.getOrDefault(id, "Unknown")));
                    student.addCourseCode(new course(courseCode, scores));
                } else {
                    throw new IllegalArgumentException("Invalid course code format: " + line);
                }
            }
        }
        students.addAll(studentMap.values());
        return students;
    }
}
