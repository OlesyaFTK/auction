/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package httpservice;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;

/**
 *
 * @author Олеся
 */
public class DescriptionCatalogue {
    public static List<Description> getDescriptionList(String text){
        try {
            HttpClientManager httpClient = new HttpClientManager();
            Document doc = httpClient.getDocumentByName(text);
            List<Description> descriptions = YandexXMLParser.createDescriptionListByDocument(doc);
            return descriptions;
        } catch (IOException ex) {
            Logger.getLogger(DescriptionCatalogue.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
    }
}
