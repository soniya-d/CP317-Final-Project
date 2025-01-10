import java.io.*;
import java.util.*;
import java.util.regex.*;

public class fileReader {

    private static boolean isValidStudentId(String id) {
        return id.matches("\\d+");  
    }

    private static boolean isValidCourseCode(String courseCode) {
        String regex = "^[A-Za-z]{2,3}\\d{3}$";
        return courseCode.matches(regex);
    }

    public static Map<String, String> loadNames(String filename) throws IOException {
        Map<String, String> names = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 2) { 
                    String id = parts[0];
                    String name = parts[1];
                    if (!isValidStudentId(id)) {
                        throw new IllegalArgumentException("Invalid student ID format: " + id);
                    }
                    if (names.containsKey(id)) {
                        throw new IllegalArgumentException("Duplicate student ID found: " + id);
                    }
                    names.put(id, name); 
                } else {
                    throw new IllegalArgumentException("Invalid name format: " + line);
                }
            }
        } catch (IOException e) {
            throw new IOException("Error reading file " + filename + ": " + e.getMessage(), e);
        }
        return names;
    }

    public static List<Student> loadCourses(String filename, Map<String, String> nameMap) throws IOException {
        List<Student> students = new ArrayList<>();
        Map<String, Student> studentMap = new HashMap<>();
        Set<String> processedIds = new HashSet<>(); 

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 6) { 
                    String id = parts[0]; 
                    String courseCode = parts[1]; 
                    if (!nameMap.containsKey(id)) {
                        throw new IllegalArgumentException("Student ID " + id + " not found in name file.");
                    }
                    if (!isValidCourseCode(courseCode)) {
                        throw new IllegalArgumentException("Invalid course code format: " + courseCode);
                    }

                    double[] scores = new double[4];
                    try {
                        for (int i = 2; i < 6; i++) { 
                            double score = Double.parseDouble(parts[i]);
                            if (score < 0 || score > 100) {
                                throw new IllegalArgumentException("Score out of range (0-100): " + score);
                            }
                            scores[i - 2] = score;
                        }
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Invalid score value, must be a number: " + line, e);
                    }

                    Student student = studentMap.computeIfAbsent(id, k -> 
                        new Student(id, nameMap.getOrDefault(id, "Unknown")));

                    for (Course existingCourse : student.getCourseCode()) {
                        if (existingCourse.getCourseCode().equals(courseCode)) {
                            throw new IllegalArgumentException("Duplicate course entry for student ID " + id + ": " + courseCode);
                        }
                    }

                    processedIds.add(id); 
                    student.addCourseCode(new Course(courseCode, scores));
                } else {
                    throw new IllegalArgumentException("Invalid course format: " + line);
                }
            }
        } catch (IOException e) {
            throw new IOException("Error reading file " + filename + ": " + e.getMessage(), e);
        }

        List<String> studentsWithoutMarks = new ArrayList<>();
        for (String id : nameMap.keySet()) {
            if (!processedIds.contains(id)) {
                studentsWithoutMarks.add(id + ", " + nameMap.get(id));
            }
        }

        if (!studentsWithoutMarks.isEmpty()) {
            System.err.println("Error: The following students have no marks in the CourseFile.txt:");
            for (String student : studentsWithoutMarks) {
                System.err.println(student);
            }
            throw new IllegalStateException("Incomplete data: Students without marks found.");
        }

        students.addAll(studentMap.values());
        return students;
    }
}
