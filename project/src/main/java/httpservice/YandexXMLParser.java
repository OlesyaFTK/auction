/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package httpservice;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 *
 * @author Олеся
 */
public class YandexXMLParser {
    public static String createDescriptionByDocument(Document doc){
        String description = "";
        NodeList response = doc.getChildNodes().item(0).getChildNodes().item(1).getChildNodes();
        
        for(int i=0;i<response.getLength();i++){
            if(response.item(i).getNodeName().toLowerCase().equals("results")){
                NodeList grouping = response.item(i).getChildNodes().item(0).getChildNodes();
                for(int j=0;j<grouping.getLength();j++){
                    if(grouping.item(j).getNodeName().toLowerCase().equals("group")){
                        NodeList docNode = grouping.item(j).getChildNodes().item(3).getChildNodes();
                        for(int c=0;c<docNode.getLength();c++){
                            if(docNode.item(c).getNodeName().toLowerCase().equals("passages")){
                                NodeList passage = docNode.item(c).getChildNodes().item(0).getChildNodes();
                                for(int g=0;g<passage.getLength();g++){
                                    description += passage.item(g).getTextContent();
                                }
                                return description;
                            }
                        }
                    }
                }
            }
        }
        return description;
    }
}
