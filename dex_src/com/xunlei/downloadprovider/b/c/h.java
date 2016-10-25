package com.xunlei.downloadprovider.b.c;

import java.io.ByteArrayInputStream;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

// compiled from: BpXMLParser.java
public abstract class h extends i implements ContentHandler {
    private static final String c;
    public Object a;
    public StringBuilder b;

    public h() {
        this.b = new StringBuilder();
    }

    static {
        c = h.class.getSimpleName();
    }

    public Object parse(byte[] bArr) {
        try {
            XMLReader xMLReader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
            xMLReader.setContentHandler(this);
            xMLReader.parse(new InputSource(new ByteArrayInputStream(bArr)));
        } catch (Exception e) {
            this.mErrCode = 1000;
            this.a = null;
        }
        return this.a;
    }

    public void startDocument() throws SAXException {
    }

    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
    }

    public void characters(char[] cArr, int i, int i2) throws SAXException {
        this.b.append(cArr, i, i2);
    }

    public void endElement(String str, String str2, String str3) throws SAXException {
        this.b.setLength(0);
    }

    public void endDocument() throws SAXException {
    }

    public void startPrefixMapping(String str, String str2) throws SAXException {
    }

    public void skippedEntity(String str) throws SAXException {
    }

    public void setDocumentLocator(Locator locator) {
    }

    public void processingInstruction(String str, String str2) throws SAXException {
    }

    public void ignorableWhitespace(char[] cArr, int i, int i2) throws SAXException {
    }

    public void endPrefixMapping(String str) throws SAXException {
    }
}
