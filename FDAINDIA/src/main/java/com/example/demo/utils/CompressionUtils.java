package com.example.demo.utils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public class CompressionUtils {

    public static byte[] compress(byte[] data) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(data.length);
        try (DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(byteArrayOutputStream)) {
            deflaterOutputStream.write(data);
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] decompress(byte[] data) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (InflaterInputStream inflaterInputStream = new InflaterInputStream(new java.io.ByteArrayInputStream(data))) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inflaterInputStream.read(buffer)) > 0) {
                byteArrayOutputStream.write(buffer, 0, length);
            }
        }
        return byteArrayOutputStream.toByteArray();
    }
}