import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Map;

public class XmlWriter {
    public static void write(Map<Integer,MusicBand> data, File file) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        Element root = doc.createElement("MusicBands");
        doc.appendChild(root);

        for(Map.Entry<Integer,MusicBand> band : data.entrySet()){
            root.appendChild(createUser(doc,band.getValue()));
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transf = transformerFactory.newTransformer();

        transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transf.setOutputProperty(OutputKeys.INDENT, "yes");
        transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        DOMSource source = new DOMSource(doc);

        StreamResult my_file = new StreamResult(file);
        // сохраняем в файл
       transf.transform(source, my_file);
    }

    private static Node createUser(Document doc,MusicBand band) {
        Element user = doc.createElement("MusicBand");
        Element cords = doc.createElement("Coordinates");
        Element album = doc.createElement("Album");

        user.appendChild(createUserElement(doc, "id", String.valueOf(band.getId())));
        user.appendChild(createUserElement(doc, "name",band.getName()));
        user.appendChild(createUserElement(doc,"numberOfParticipants",band.getNumberOfParticipants().toString()));
        user.appendChild(createUserElement(doc,"singles",band.getSinglesCount().toString()));
        user.appendChild(createUserElement(doc,"genre",band.getGenre().toString()));
        cords.appendChild(createUserElement(doc,"x",String.valueOf(band.getCoordinates().getX())));
        cords.appendChild(createUserElement(doc,"y",String.valueOf(band.getCoordinates().getY())));
        album.appendChild(createUserElement(doc,"name",band.getBestAlbum().getName()));
        album.appendChild(createUserElement(doc,"tracks",band.getBestAlbum().getTracks().toString()));
        album.appendChild(createUserElement(doc,"length",band.getBestAlbum().getLength().toString()));
        user.appendChild(cords);
        user.appendChild(album);
        return user;
    }

    private static Node createUserElement(Document doc, String name,
                                          String value) {

        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));

        return node;
    }
}
