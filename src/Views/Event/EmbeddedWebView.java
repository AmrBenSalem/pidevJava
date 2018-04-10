/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Event;

import javafx.scene.layout.StackPane;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

/**
 * A WebView which has getters and setters for content or a document url.
 *
 * Usage in FXML element is: 
 * 
 *   EITHER by specifying a url to a html document:
 * 
 *      <EmbeddedWebView fx:id="embeddedWebView" url="/embeddedwebview/embedded.html">
 *          
 *   OR by specifying CDATA escaped html content:
 *   
 *     <EmbeddedWebView fx:id="embeddedWebView">
 *         <content>
 *             <![CDATA[
 *                 <h3>Embedded WebView</h3>
 *                 <p>HTML content inline in FXML</p>
 *             ]]>
 *         </content>
 *     </EmbeddedWebView>
 *
 */
public class EmbeddedWebView extends StackPane {

    final private WebView webView;
    final private WebEngine webEngine;
    private final Bridge bridge = new Bridge();

    // For space efficiency, an alternate implementation could just 
    // rely on the content in the WebView itself rather than 
    // duplicating the content here, but it was simple to implement with a duplicate. 
    private String content;
    
    private String url;

    public EmbeddedWebView() {
        webView = new WebView();
        getChildren().add(webView);
        webEngine = webView.getEngine();
        
        ////////////////////////////////
        
     // Enable Javascript.
       webEngine.setJavaScriptEnabled(true);
 
        // A Worker load the page
        Worker<Void> worker = webEngine.getLoadWorker();
 
        // Listening to the status of worker
        worker.stateProperty().addListener(new ChangeListener<State>() {
 
            @Override
            public void changed(ObservableValue<? extends State> observable, //
                    State oldValue, State newValue) {
 
                // When load successed.
                if (newValue == Worker.State.SUCCEEDED) {
                    // Get window object of page.
                    JSObject jsobj = (JSObject) webEngine.executeScript("window");
 
                    // Set member for 'window' object.
                    // In Javascript access: window.myJavaMember....
                    jsobj.setMember("myJavaMember", bridge);
                }
            }
        });
        /////////////////////////////////
    }

    public String getContent() {
        return content;
    }
    
    public void execstring(String s){
        webView.getEngine().executeScript(s);
    }
    
    public void exectest(String s){
        webEngine.getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
                if (newState == Worker.State.SUCCEEDED) {
                    webEngine.executeScript(s);
                }
                });
                }

    /**
     * Loads html content directly into the webview.
     * @param content a html content string to load into the webview.
     */
    public void setContent(String content) {
        this.content = content;
        webView.getEngine().loadContent(content);
    }

    public String getUrl() {
        return url;
    }

    /**
     * Loads content into the WebView from a given url.
     * The allowed url types are http, https and file.
     *
     * Additionally, content can be loaded from a classpath resource.
     * To be loaded from the classpath, the url must start with a / character
     * and specify the full resource path to the html
     * (i.e., relative resource path specifiers are not allowed).
     *
     * @param url the location of the html document to be loaded.
     */
    public void setUrl(String url) {
        if ( url == null || ! (url.startsWith("/") || url.startsWith("http:") || url.startsWith("https:") || url.startsWith("file:"))) {
            throw new IllegalArgumentException("url must start with one of http: file: https: or /");
        }

        this.url = url;

        if (url.startsWith("/")) {
            webView.getEngine().load(EmbeddedWebView.class.getResource(url).toExternalForm()
            );
        } else {
            webView.getEngine().load(
                    url
            );
        }
    }
    
    
    
    
    
    
    
     public class Bridge {
 
        public void showTime(String l1,String l2) {
            FXMLController.latetude=l1;
            FXMLController.longitude=l2;
            //ModifPropController.latetude=l1;
            //ModifPropController.longitude=l2;
        }
    }
     
    
}
