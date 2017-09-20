package nefalas.webreader;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import nefalas.webreader.sessionmanager.SessionManager;

/**
 * Class WebReader
 * -
 * Extend this class to create a website reader
 */
public class WebReader {

    private String username, password;
    private final String loginUrl = "https://kth.instructure.com/";
    WebClient webClient;

    /**
     * Constructor for the WebReader object
     * @param username KTH username of the user (only username, not full email)
     * @param password KTH password of the user
     */
    public WebReader(String username, String password) {
        this.username = username;
        this.password = password;
        this.webClient = SessionManager.getWebClientByUsername(username, password);

        setOptions();
    }

    public WebReader(String username, String password, WebClient webClient) {
        this.username = username;
        this.password = password;
        this.webClient = webClient;

        setOptions();
    }

    private void setOptions() {
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
    }

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

    /*
    public boolean login() {
        try {
            final HtmlPage loginPage = webClient.getPage(loginUrl);

            if (loginPage.asText().contains("Du är inloggad")) {
                return true;
            }

            final HtmlForm form = loginPage.getForms().get(0);

            final HtmlSubmitInput button = form.getInputByValue("Logga in");
            final HtmlTextInput usernameField = form.getInputByName("username");
            final HtmlPasswordInput passwordField = form.getInputByName("password");

            usernameField.setValueAttribute(username);
            passwordField.setValueAttribute(password);

            final HtmlPage result = button.click();

            return result.asText().contains("Du är inloggad");
        } catch (java.io.IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    */

}
