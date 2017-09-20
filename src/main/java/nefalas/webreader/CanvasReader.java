package nefalas.webreader;

import com.gargoylesoftware.htmlunit.html.*;
import nefalas.kthplanner.courses.CanvasCourse;
import nefalas.kthplanner.groups.CanvasGroup;

import java.util.ArrayList;
import java.util.List;

public class CanvasReader extends WebReader {

    public CanvasReader(String username, String password) {
        super(username, password);
    }

    /*
    @Override
    public boolean login() {
        try {
            final HtmlPage loginPage = webClient.getPage(loginUrl);

            if (loginPage.asText().contains("Användarens översikt")) {
                return true;
            }

            final HtmlForm form = loginPage.getForms().get(0);

            final HtmlSubmitInput button = form.getInputByValue("Logga in");
            final HtmlTextInput usernameField = form.getInputByName("username");
            final HtmlPasswordInput passwordField = form.getInputByName("password");

            usernameField.setValueAttribute(username);
            passwordField.setValueAttribute(password);

            final HtmlPage interm = button.click();

            if (!interm.asText().contains("Continue")) {
                return interm.asText().contains("Användarens översikt");
            }

            final HtmlForm continueForm = interm.getForms().get(0);
            final HtmlSubmitInput continueButton = continueForm.getInputByValue("Continue");
            final HtmlPage result = continueButton.click();

            return result.asText().contains("Användarens översikt");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    */

    public List<CanvasCourse> getCurrentCourses() {
        HtmlPage page;
        try {
            page = webClient.getPage("https://kth.instructure.com/courses");
        } catch (Exception e) {
            return new ArrayList<>();
        }
        DomElement currentCourses = page.getElementById("my_courses_table")
                .getElementsByTagName("tbody").get(0);

        return extractCourseInfo(currentCourses);
    }

    public List<CanvasCourse> getPastCourses() {
        HtmlPage page;
        try {
            page = webClient.getPage("https://kth.instructure.com/courses");
        } catch (Exception e) {
            return new ArrayList<>();
        }
        DomElement pastCourses = page.getElementById("past_enrollments_table")
                .getElementsByTagName("tbody").get(0);

        return extractCourseInfo(pastCourses);
    }

    public List<CanvasCourse> getFutureCourses() {
        HtmlPage page;
        try {
            page = webClient.getPage("https://kth.instructure.com/courses");
        } catch (Exception e) {
            return new ArrayList<>();
        }
        DomElement futureCourses = page.getElementById("future_enrollments_table");

        return extractCourseInfo(futureCourses);
    }

    public List<CanvasGroup> getGroups() throws Exception {
        HtmlPage page = webClient.getPage("https://kth.instructure.com/courses");
        DomElement groups = page.getElementById("my_groups_table");

        ArrayList<CanvasGroup> output = new ArrayList<>();
        for (DomElement tr : groups.getElementsByTagName("tr")) {
            List<HtmlElement> elems = tr.getElementsByTagName("td");
            if (elems.size() > 0) {
                String groupName = elems.get(0).asText();
                String courseName = elems.get(1).asText();
                CanvasGroup group = new CanvasGroup(groupName, courseName);
                output.add(group);
            }
        }
        return output;
    }

    private List<CanvasCourse> extractCourseInfo(DomElement courses) {
        List<CanvasCourse> output = new ArrayList<>();
        for (DomElement tr : courses.getElementsByTagName("tr")) {
            List<HtmlElement> elems = tr.getElementsByTagName("td");
            if (elems.size() > 0) {
                String name = elems.get(1).asText();
                boolean published = elems.get(5).asText().equals("Ja");
                boolean selected = elems.get(0).asText().contains("bort");
                String link = null;
                if (elems.get(1).getElementsByTagName("a").size() > 0) {
                    link = "https://kth.instructure.com"
                            + elems.get(1).getElementsByTagName("a").get(0).getAttribute("href");
                }
                int code = (link == null)? 0 : Integer.parseInt(link.substring(link.lastIndexOf("/")+1));
                CanvasCourse course = new CanvasCourse(name, published, selected, code);
                output.add(course);
            }
        }
        return output;
    }

}

