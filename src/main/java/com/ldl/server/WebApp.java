package com.ldl.server;

import com.ldl.server.mapper.WebContext;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class WebApp {
    private static WebContext webContext;
    static {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            WebHandler handler = new WebHandler();
            parser.parse(Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("web.xml"), handler);
            webContext = new WebContext(handler.getEntities(), handler.getMappings());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("web.xml解析失败");
        }
    }
    public static Servlet getServletFromUri(String uri) {
        String className = webContext.getClassNameByUrl("/" + uri);
        try {
            Class clazz = Class.forName(className);
            Servlet servlet = (Servlet) clazz.getConstructor().newInstance();
            return servlet;
        } catch(Exception e) {
            //e.printStackTrace();
            System.out.println("未找到资源 404");
        }
        return null;
    }
}
