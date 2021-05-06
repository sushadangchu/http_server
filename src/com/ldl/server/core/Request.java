package com.ldl.server.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.*;

public class Request {
    private String requestInfo;
    private String method;
    private String uri;
    private String parameters;
    private Map<String, List<String>> parametersMap;

    private static final String CRLF = "\r\n";
    private static final String ENCODE = "utf-8";
    public Request() {
        requestInfo = "";
        method = "";
        uri = "";
        parameters = "";
        parametersMap = new HashMap<>();
    }

    public Request(InputStream inputStream) {
        this();
        byte[] data = new byte[1024 * 1024]; //1m
        int len = 0;
        try {
            len = inputStream.read(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        requestInfo = new String(data, 0, len);
        parseRequestInfo();
    }

    public Request(Socket socket) throws IOException {
        this(socket.getInputStream());
    }

    private void parseRequestInfo() {
        int start = requestInfo.indexOf("/");
        int end = requestInfo.indexOf("HTTP/");
        method = requestInfo.substring(0, start).trim().toUpperCase(Locale.ROOT);
        uri = requestInfo.substring(start + 1, end).trim();
        int paramStart = uri.indexOf("?");
        if (paramStart >= 0) {
            parameters = uri.substring(paramStart + 1).trim();
            uri = uri.substring(0, paramStart);
        }
        if ("POST".equals(method)) {
            int endIndex = requestInfo.lastIndexOf(CRLF);
            if ("".equals(parameters)) {
                parameters = requestInfo.substring(endIndex).trim();
            } else {
                parameters += "&" + requestInfo.substring(endIndex).trim();
            }
        }
        parseParameters();
    }

    private void parseParameters() {
        String[] parameter = parameters.split("&");
        for (String par : parameter) {
            String[] keyValue = par.split("=");
            keyValue = Arrays.copyOf(keyValue, 2);
            String key = keyValue[0] == null ? null : decode(keyValue[0], ENCODE);
            String value = keyValue[1] == null ? null : decode(keyValue[1], ENCODE);;
            if (key == null) {
                continue;
            }
            if (!parametersMap.containsKey(key)) {
                parametersMap.put(key, new ArrayList<>());
            }
            parametersMap.get(key).add(value);
        }
    }

    private String decode(String str, String encode) {
        try {
            return java.net.URLDecoder.decode(str, encode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String[] getParameterValues(String key) {
        List<String> values = parametersMap.get(key);
        if (values == null) {
            return null;
        }
        return values.toArray(new String[0]);
    }

    public String getParameterValue(String key) {
        String[] values = getParameterValues(key);
        return values == null ? null : values[0];
    }

    public String getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }
}
