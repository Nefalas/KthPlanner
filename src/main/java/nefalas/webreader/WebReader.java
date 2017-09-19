package nefalas.webreader;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;

/**
 * Class WebReader
 * -
 * Extend this class to create a website reader
 */
class WebReader {

    String username, password;
    String loginUrl;
    final WebClient webClient = new WebClient(BrowserVersion.CHROME);

    /**
     * Constructor for the WebReader object
     * @param username KTH username of the user (only username, not full email)
     * @param password KTH password of the user
     */
    WebReader(String username, String password) {
        this.username = username;
        this.password = password;

        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
    }

    /**
     * Extend this class to login to a website
     * @return true if logged in
     * @throws Exception in case of unhandled exception
     */
    public boolean login() throws Exception {
        return false;
    }

}
