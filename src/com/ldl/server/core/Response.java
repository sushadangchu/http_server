package com.ldl.server.core;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;

public class Response {
    private BufferedWriter bufferedWriter;
    private StringBuilder responseInfo;
    private StringBuilder body;
    private int bodyLength;

    public static final String BLANK = " ";
    public static final String CRLF = "\r\n";
    public static final String HTTP_VERSION = "HTTP/1.1";

    private Response() {
        responseInfo = new StringBuilder();
        body = new StringBuilder();
        bodyLength = 0;
    }

    public Response(Socket socket) {
        this();
        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            responseInfo = null;
        }
    }

    public Response(OutputStream output) {
        this();
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(output));
    }

    public void createResponseInfo(int status) {
        responseInfo.append(HTTP_VERSION).append(BLANK);
        responseInfo.append(status).append(BLANK);
        String statusInfo;
        switch (status) {
            case 200 : statusInfo = "OK"; break;
            case 404 : statusInfo = "NOT FOUND"; break;
            case 505 : statusInfo = "SERVER ERROR"; break;
            default: statusInfo = "STATUS ERROR";
        }
        responseInfo.append(statusInfo).append(CRLF);
        responseInfo.append("Date:").append(new Date()).append(CRLF);
        responseInfo.append("server:").append("ldl Server/0.1;charset=UTF-8").append(CRLF);
        responseInfo.append("Content-type:text/html").append(CRLF);
        responseInfo.append("Content-length:").append(bodyLength).append(CRLF);
        responseInfo.append(CRLF);
    }

    public Response print(String info) {
        body.append(info);
        bodyLength += info.getBytes().length;
        return this;
    }

    public Response println(String info) {
        body.append(info);
        bodyLength += (info + CRLF).getBytes().length;
        return this;
    }

    public void push(int status) throws IOException {
        if (responseInfo == null) {
            status = 505;
            body.delete(0, body.length());
            bodyLength = 0;
        }
        createResponseInfo(status);
        bufferedWriter.append(responseInfo);
        bufferedWriter.append(body);
        bufferedWriter.flush();
    }
}
