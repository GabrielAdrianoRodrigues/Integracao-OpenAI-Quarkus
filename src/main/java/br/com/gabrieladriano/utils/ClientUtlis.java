package br.com.gabrieladriano.utils;

import java.lang.reflect.Field;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jboss.resteasy.reactive.multipart.FileUpload;



public abstract class ClientUtlis {
    
    public static <T> BodyPublisher createFormData(T object, String boundary) {
        List<byte[]> byteArrays = new ArrayList<>();

        Stream.of(object.getClass().getDeclaredFields()).collect(Collectors.toMap(Field::getName, x -> {
            try {
                x.setAccessible(true);
                var y = x.get(object);
                return (y != null) ? y : "null";
            } catch (Exception e) {
                return "null";
            }
        })).entrySet().stream().filter(x -> !x.getValue().equals("null")).forEach(x -> {
            try{
                if (x.getValue() instanceof FileUpload p) {
                    byteArrays.add(String.format("--%s\r\nContent-Disposition: form-data; name=\"%s\"; filename=\"%s\"\r\nContent-Type: %s\r\n\r\n", boundary, x.getKey(), p.fileName(), p.contentType()).getBytes());
                    byteArrays.add(Files.readAllBytes(p.uploadedFile()));
                    byteArrays.add("\r\n".getBytes());
                } else {
                    byteArrays.add(String.format("--%s\r\nContent-Disposition: form-data; name=\"%s\"\r\n\r\n%s\r\n", boundary, x.getKey(), String.valueOf(x.getValue())).getBytes());
                }
            } catch (Exception e) {
                throw new RuntimeException();
            }
        });
        
        byteArrays.add(String.format("--%s--\r\n", boundary).getBytes());
        return BodyPublishers.ofByteArrays(byteArrays);
    }
}
