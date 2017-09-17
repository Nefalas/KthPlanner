package nefalas.webreader;

import com.gargoylesoftware.htmlunit.html.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.List;

public class KthReader extends WebReader {

    public KthReader(String username, String password) {
        super(username, password);
        this.loginUrl = "https://login.kth.se/login";
    }

    @Override
    public boolean login() throws Exception {
        final HtmlPage loginPage = webClient.getPage(loginUrl);
        final HtmlForm form = loginPage.getForms().get(0);

        final HtmlSubmitInput button = form.getInputByValue("Logga in");
        final HtmlTextInput usernameField = form.getInputByName("username");
        final HtmlPasswordInput passwordField = form.getInputByName("password");

        usernameField.setValueAttribute(username);
        passwordField.setValueAttribute(password);

        final HtmlPage result = button.click();
        return result.asText().contains("Du är inloggad");
    }

    public String getPageContent(String url, boolean xml) throws Exception {
        HtmlPage page = webClient.getPage(url);
        return (xml)? page.asXml() : page.asText();
    }

    public JSONArray getCourses() throws Exception {
        HtmlPage page = webClient.getPage("https://www.kth.se/student/minasidor/");
        DomElement currentCourses = page.getElementById("currentCourses");

        JSONArray out = new JSONArray();
        for (DomElement tr : currentCourses.getElementsByTagName("tr")) {
            List<HtmlElement> elems = tr.getElementsByTagName("td");
            if (elems.size() > 0) {
                String code = elems.get(1).asText();
                String name = elems.get(2).asText();
                JSONObject course = new JSONObject();
                course.put("code", code);
                course.put("name", name);
                out.add(course);
            }
        }
        return out;
    }

    public JSONArray getGrades() throws Exception {
        HtmlPage page = webClient.getPage("https://www.kth.se/student/minasidor/kurser/");
        DomElement results = page.getElementById("courselistresults");

        JSONArray out = new JSONArray();
        for (DomElement tr : results.getElementsByTagName("tr")) {
            List<HtmlElement> elems = tr.getElementsByTagName("td");
            if (elems.size() > 0) {
                String code = elems.get(0).asText();
                String name = elems.get(1).asText();
                String credits = elems.get(2).asText();
                String grade = elems.get(3).asText();
                JSONObject course = new JSONObject();
                course.put("code", code);
                course.put("name", name);
                course.put("credits", credits);
                course.put("grade", grade);
                out.add(course);
            }
        }
        return out;
    }
}
