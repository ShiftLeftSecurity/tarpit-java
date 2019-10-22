package io.shiftleft.tarpit;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.logging.Logger;

class DocumentTarpit {

  private final static Logger logger = Logger.getLogger(DocumentTarpit.class.getName());

  static Document getDocument(String content) {

    DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance(); // caution!!!
    String FEATURE = null;
    try {

      // This is the PRIMARY defense. If DTDs (doctypes) are disallowed, almost all
      // XML entity attacks are prevented
      // Xerces 2 only - http://xerces.apache.org/xerces2-j/features.html#disallow-doctype-decl
      FEATURE = "http://apache.org/xml/features/disallow-doctype-decl";
      docBuilderFactory.setFeature(FEATURE, true);

      // If you can't completely disable DTDs, then at least do the following:
      // Xerces 1 - http://xerces.apache.org/xerces-j/features.html#external-general-entities
      // Xerces 2 - http://xerces.apache.org/xerces2-j/features.html#external-general-entities
      // JDK7+ - http://xml.org/sax/features/external-general-entities
      FEATURE = "http://xml.org/sax/features/external-general-entities";
      docBuilderFactory.setFeature(FEATURE, false);

      // Xerces 1 - http://xerces.apache.org/xerces-j/features.html#external-parameter-entities
      // Xerces 2 - http://xerces.apache.org/xerces2-j/features.html#external-parameter-entities
      // JDK7+ - http://xml.org/sax/features/external-parameter-entities
      FEATURE = "http://xml.org/sax/features/external-parameter-entities";
      docBuilderFactory.setFeature(FEATURE, false);

      // Disable external DTDs as well
      FEATURE = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
      docBuilderFactory.setFeature(FEATURE, false);

      // and these as well, per Timothy Morgan's 2014 paper: "XML Schema, DTD, and Entity Attacks"
      docBuilderFactory.setXIncludeAware(false);
      docBuilderFactory.setExpandEntityReferences(false);

      // And, per Timothy Morgan: "If for some reason support for inline DOCTYPEs are a requirement, then
      // ensure the entity settings are disabled (as shown above) and beware that SSRF attacks
      // (http://cwe.mitre.org/data/definitions/918.html) and denial
      // of service attacks (such as billion laughs or decompression bombs via "jar:") are a risk."

      javax.xml.parsers.DocumentBuilder builder = docBuilderFactory.newDocumentBuilder();

      return builder.parse(new ByteArrayInputStream(content.getBytes()));

    } catch (ParserConfigurationException | SAXException | IOException e) {
      throw new RuntimeException(e);
    }
  }
}