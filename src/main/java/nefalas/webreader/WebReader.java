package nefalas.webreader;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;

public class WebReader {

    protected String username, password;
    protected String loginUrl;
    protected final WebClient webClient = new WebClient(BrowserVersion.CHROME);


    WebReader(String username, String password) {
        this.username = username;
        this.password = password;

        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
    }

    public boolean login() throws Exception {
        return false;
    }

}
