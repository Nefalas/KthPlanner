package nefalas.kthplanner.grades;

public class KthGrade {

    private String name;
    private String code;
    private String credits;
    private String grade;

    public KthGrade(String name, String code, String credits, String grade) {
        this.name = name;
        this.code = code;
        this.credits = credits;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getCredits() {
        return credits;
    }

    public String getGrade() {
        return grade;
    }

}
