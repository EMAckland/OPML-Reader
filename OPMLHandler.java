package me.baconbeans.OPMLReader;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;
import java.util.List;

public class OPMLHandler extends DefaultHandler {
  List<OpmlElement> elements = new ArrayList<>();
  String elementValue = null;
  Boolean elementOn = false;
  public static OpmlElement data = null;

  public static OpmlElement getXMLData() {
    return data;
  }

  public static void setXMLData(OpmlElement data) {
    OPMLHandler.data = data;
  }

  /**
   * This will be called when the tags of the XML starts.
   **/
  @Override
  public void startElement(String uri, String localName, String qName,
                           Attributes attributes) throws SAXException {
    elementOn = true;

    if (localName.equals(OpmlSymbols.OUTLINE)){
      data = new OpmlElement();
      data.setText(attributes.getValue(OpmlSymbols.TEXT));
      data.setType(attributes.getValue(OpmlSymbols.TYPE));
      data.setXmlUrl(attributes.getValue(OpmlSymbols.XMLURL));
      data.setHtmlUrl(attributes.getValue(OpmlSymbols.HTMLURL));
    }
  }

  @Override
  public void endElement(String uri, String localName, String qName)
      throws SAXException {

    elementOn = false;

  if (localName.equalsIgnoreCase(OpmlSymbols.OUTLINE))
      elements.add(data);
  }

  /**
   * This is called to get the tags value
   **/
  @Override
  public void characters(char[] ch, int start, int length)
      throws SAXException {

    if (elementOn) {
      elementValue = new String(ch, start, length);
      elementOn = false;
    }
  }

  public List<OpmlElement> getElements() {
    return elements;
  }
}