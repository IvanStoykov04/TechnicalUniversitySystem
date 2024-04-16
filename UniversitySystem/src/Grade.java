public class Grade {

    private String subject;
    private int semester;
    private double rating;

    public Grade(String subject, int semester, double rating) {
        this.subject = subject;
        this.semester = semester;
        this.rating = rating;
    }


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }



}
