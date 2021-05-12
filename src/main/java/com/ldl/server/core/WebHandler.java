package com.ldl.server.core;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class WebHandler extends DefaultHandler {
    private List<Entity> entities;
    private List<Mapping> mappings;
    private Entity entity;
    private Mapping mapping;
    private String tag;
    private Boolean isMapping;

    @Override
    public void startDocument() throws SAXException {
        entities = new ArrayList<>();
        mappings = new ArrayList<>();
        isMapping = false;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName != null) {
            tag = qName;
            if (tag.equals("servlet")) {
                entity = new Entity();
                isMapping = false;
            } else if (tag.equals("servlet-mapping")) {
                mapping = new Mapping();
                isMapping = true;
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String contexts = new String(ch, start, length).trim();

        if (contexts != null && tag != null && contexts.length() > 0) {
            if (!isMapping) {
                if (tag.equals("servlet-name")) {
                    entity.setName(contexts);
                } else if (tag.equals("servlet-class")) {
                    entity.setClazz(contexts);
                }
            } else {
                if (tag.equals("servlet-name")) {
                    mapping.setName(contexts);
                } else if (tag.equals("url-pattern")) {
                    mapping.addPattern(contexts);
                }
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName != null) {
            if (qName.equals("servlet")) {
                entities.add(entity);
            } else if (qName.equals("servlet-mapping")) {
                mappings.add(mapping);
            }
        }

        tag = null;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public List<Mapping> getMappings() {
        return mappings;
    }
}
