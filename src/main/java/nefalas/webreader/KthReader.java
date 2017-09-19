package nefalas.webreader;

import com.gargoylesoftware.htmlunit.html.*;
import nefalas.kthplanner.courses.KthCourse;
import nefalas.kthplanner.grades.KthGrade;

import java.util.ArrayList;
import java.util.List;

/**
 * Use this class to read from the KTH website
 */
public class KthReader extends WebReader {

    /**
     * Constructor for the KthReader object
     * @param username KTH username (not full email address)
     * @param password KTH password
     */
    public KthReader(String username, String password) {
        super(username, password);
        this.loginUrl = "https://login.kth.se/login";
    }

    /**
     * Use this to login to the KTH website
     * @return true if logged in
     */
    @Override
    public boolean login() {
        try {
            final HtmlPage loginPage = webClient.getPage(loginUrl);
            final HtmlForm form = loginPage.getForms().get(0);

            final HtmlSubmitInput button = form.getInputByValue("Logga in");
            final HtmlTextInput usernameField = form.getInputByName("username");
            final HtmlPasswordInput passwordField = form.getInputByName("password");

            usernameField.setValueAttribute(username);
            passwordField.setValueAttribute(password);

            final HtmlPage result = button.click();
            return result.asText().contains("Du är inloggad");
        } catch (java.io.IOException e) {
            return false;
        }
    }

    /**
     * Get the content of the page at the given URL in text or HTML format
     * @param url the URL of the page
     * @param html set true to return as HTML
     * @return the content of the page in the selected format
     */
    public String getPageContent(String url, boolean html) {
        try {
            HtmlPage page = webClient.getPage(url);
            return (html) ? page.asXml() : page.asText();
        } catch (java.io.IOException e) {
            return "error: " + e;
        }
    }

    /**
     * Get the list of courses the user is subscribed to
     * @return a List of KthCourse objects
     */
    public List<KthCourse> getCourses() {
        try {
            HtmlPage page = webClient.getPage("https://www.kth.se/student/minasidor/");
            DomElement currentCourses = page.getElementById("currentCourses");

            ArrayList<KthCourse> output = new ArrayList<>();
            for (DomElement tr : currentCourses.getElementsByTagName("tr")) {
                List<HtmlElement> elems = tr.getElementsByTagName("td");
                if (elems.size() > 0) {
                    String code = elems.get(1).asText();
                    String name = elems.get(2).asText();
                    KthCourse course = new KthCourse(name, code);
                    output.add(course);
                }
            }
            return output;
        } catch (java.io.IOException e) {
            return new ArrayList<>();
        }
    }

    /**
     * Get the list of grades of the courses the user is subscribed to
     * @return a List of KthGrade objects
     */
    public List<KthGrade> getGrades() {
        try {
            HtmlPage page = webClient.getPage("https://www.kth.se/student/minasidor/kurser/");
            DomElement results = page.getElementById("courselistresults");

            ArrayList<KthGrade> output = new ArrayList<>();
            for (DomElement tr : results.getElementsByTagName("tr")) {
                List<HtmlElement> elems = tr.getElementsByTagName("td");
                if (elems.size() > 0) {
                    String code = elems.get(0).asText();
                    String name = elems.get(1).asText();
                    String credits = elems.get(2).asText();
                    String grade = elems.get(3).asText();
                    KthGrade kthGrade = new KthGrade(name, code, credits, grade);
                    output.add(kthGrade);
                }
            }
            return output;
        } catch (java.io.IOException e) {
            return new ArrayList<>();
        }
    }
}
