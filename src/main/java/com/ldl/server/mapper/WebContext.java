package com.ldl.server.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebContext {
    private List<Entity> entities;
    private List<Mapping> mappings;
    private Map<String, String> entityMap;
    private Map<String, String> mappingMap;

    public WebContext(){
    }

    public WebContext(List<Entity> entities, List<Mapping> mappings) {
        this.entities = entities;
        this.mappings = mappings;
        this.entityMap = new HashMap<>();
        this.mappingMap = new HashMap<>();

        for (Entity entity : this.entities) {
            entityMap.put(entity.getName(), entity.getClazz());
        }

        for (Mapping mapping : mappings) {
            for (String s : mapping.getPattern()) {
                mappingMap.put(s, mapping.getName());
            }
        }
    }

    public String getClassNameByUrl(String url) {
        if (url == null) {
            return null;
        }
        String name = mappingMap.get(url);
        return entityMap.get(name);
    }
}
