public class course {
    // private fields to store the course code and scores 
    private String courseCode;
    // array to store scores
    private double[] scores;

    //initialize the course object
    public course(String courseCode, double[] scores) {
        this.courseCode = courseCode; 
        this.scores = scores;        
    }

    // getter method to get course code
    public String getCourseCode() { 
        return courseCode; 
    }

    // method to calculate final grade with the formula given
    public double calcFinalGrade() {
        // makes sure there are at least 4 scores: 3 tests and 1 final exam
        if (scores.length < 4) {
            throw new IllegalArgumentException("Insufficient scores provided.");
        }

        // calculate the average of the three tests (20% each)
        double testAvg = (scores[0] + scores[1] + scores[2]) * 0.2;

        // calculate the mark of the final exam (40%)
        double finalExam = scores[3] * 0.4;

        // Return the total final grade
        return testAvg + finalExam;
    }
}
