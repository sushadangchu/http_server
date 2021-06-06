package com.ldl.server.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

//@Slf4j
public class ioUtil {
    public static byte[] readAllBytes(String fileName) throws IOException {
        InputStream inputStream = ioUtil.class.getResourceAsStream(fileName);
        if (inputStream == null) {
            //log.info("not found file :{}", fileName);
            throw new FileNotFoundException();
        }

        return inputStream.readAllBytes();
    }
}
