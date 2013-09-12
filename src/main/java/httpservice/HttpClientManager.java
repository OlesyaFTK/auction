/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package httpservice;

import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author Олеся
 */
public class HttpClientManager {
    public static String url = "http://xmlsearch.yandex.ru/xmlsearch?user=yazikov2007&key=03.95482963:e6495af2366b2f6df0bc929427622730&query=";
    
    public Document getDocumentByName(String name) throws IOException{
        DefaultHttpClient httpClient = new DefaultHttpClient();
        httpClient.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(0, false));
        String newName = name.replaceAll(" ", "%20");
        HttpGet httpget = new HttpGet(url+newName);
        HttpContext context = new BasicHttpContext();
        HttpResponse response = httpClient.execute(httpget, context);
        HttpEntity entity = response.getEntity();
        InputStream in = null;
        if (entity != null) {      
            in = entity.getContent();
        }
        return newDocumentFromInputStream(in);
    }
    
    public Document newDocumentFromInputStream(InputStream in) {
        DocumentBuilderFactory factory = null;
        DocumentBuilder builder = null;
        Document ret = null;

        try {
         factory = DocumentBuilderFactory.newInstance();
          builder = factory.newDocumentBuilder();
          ret = builder.parse(new InputSource(in));
         } catch (ParserConfigurationException | SAXException | IOException e) {
           e.printStackTrace();
         }
        return ret;
  }
}
