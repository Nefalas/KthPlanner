package nefalas.kthplanner.courses;

public class CanvasCourse {

    private String name;
    private boolean published;
    private boolean selected;
    private int code;

    public CanvasCourse(String name, boolean published, boolean selected, int code) {
        this.name = name;
        this.published = published;
        this.selected = selected;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public boolean isPublished() {
        return published;
    }

    public boolean isSelected() {
        return selected;
    }

    public int getCode() {
        return code;
    }
}
