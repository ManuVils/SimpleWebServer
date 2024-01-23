package org.codeforall.ooptimus;

import java.io.File;

public class ServerOptions {

    String clientRequest;

    public ServerOptions (String clientRequest) {
        this.clientRequest = clientRequest;
    }

    File error = new File("resources/404.html");
    File index = new File("resources/index.html");
    File picture = new File("resources/spaceship.png");


    String errorHeader = "HTTP / 1.0 404 Not Found" +
            "Content - Type:text / html;" +
            "charset = UTF - 8\r\n" +
            "Content - Length: " + error.length() + " \r\n" +
            "\r\n";

    String indexHeader = "HTTP/1.0 200 Document Follows\r\n" +
            "Content-Type: text/html; charset=UTF-8\r\n" +
            "Content-Length: " + index.length() + " \r\n" +
            "\r\n";

    String pictureHeader = "HTTP / 1.0 200 Document Follows\r\n" +
            "Content - Type:image /JPG \r\n" +
            "Content - Length: " + picture.length() + " \r\n" +
            "\r\n";


    public File clientRequestedFile() {
        switch(this.clientRequest){
            case "/spaceship.png":
                return picture;

            case "/index.html":
                return index;

            default:
                return error;
        }
    }

    public String fileRequestedHeader() {
        switch(this.clientRequest){
            case "spaceship.png":
                return pictureHeader;

            case "index.html":
                return indexHeader;

            default:
                return errorHeader;
        }
    }


}
