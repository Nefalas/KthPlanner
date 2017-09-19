package nefalas.kthplanner.groups;

public class CanvasGroup {

    private String groupName;
    private String courseName;

    public CanvasGroup(String groupName, String courseName) {
        this.groupName = groupName;
        this.courseName = courseName;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getCourseName() {
        return courseName;
    }

}
