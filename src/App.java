import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Comparator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class App {
    public static void main(String[] args) throws Exception, EmptyAutosarFileException {

        try {
            
            String path = args[0];
            if (!path.endsWith(".arxml")) {
                throw new NotVaildAutosarFileException("Not a vaild AUTOSAR file");
            }
            
            File file = new File(path);

            if(file.length() == 0) {
                throw new EmptyAutosarFileException("Empty AUTOSAR file");
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);

            NodeList nodeList = document.getElementsByTagName("CONTAINER");
            Element[] elements = new Element[nodeList.getLength()];
            for (int i = 0; i < nodeList.getLength(); i++) {
                elements[i] = (Element) nodeList.item(i);
            }

            Arrays.sort(elements, new Comparator<Element>() {
                @Override
                public int compare(Element o1, Element o2) {
                    return o1.getElementsByTagName("SHORT-NAME").item(0).getTextContent()
                            .compareTo(o2.getElementsByTagName("SHORT-NAME").item(0).getTextContent());
                }
            });
            
            for (Element element : elements) {
                document.getDocumentElement().removeChild(element);
                document.getDocumentElement().appendChild(element);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            File newFile = new File(path.replace(".arxml", "_mod.arxml"));
            newFile.createNewFile();
            StreamResult result = new StreamResult(new FileWriter(newFile));
            transformer.transform(source, result);

            System.out.println("Done");
        
        } catch (NotVaildAutosarFileException e) {
            System.out.println(e.getMessage());
        }
    }   
}

class NotVaildAutosarFileException extends Exception {
    public NotVaildAutosarFileException(String message) {
        super(message);
    }
}

class EmptyAutosarFileException extends Exception {
    public EmptyAutosarFileException(String message) {
        super(message);
    }
}

