public class Course {
    private String courseCode;
    private double[] scores;

    public Course(String courseCode, double[] scores) {
        this.courseCode = courseCode;
        this.scores = scores;

        for (double score : scores) {
            if (score < 0 || score > 100) {
                throw new IllegalArgumentException("Invalid score value: " + score);
            }
        }
    }
    public String getCourseCode() { 
        return courseCode; 
    }

    public double calcFinalGrade() {
        if (scores.length < 4) {
            throw new IllegalArgumentException("Insufficient scores provided.");
        }
        double testAvg = (scores[0] + scores[1] + scores[2]) * 0.2;
        double finalExam = scores[3] * 0.4;
        return testAvg + finalExam;
    }
}
