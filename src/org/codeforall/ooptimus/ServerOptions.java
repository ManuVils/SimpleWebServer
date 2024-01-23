package org.codeforall.ooptimus;

import java.io.File;

public class ServerOptions {

    String clientRequest;

    // Constructor
    public ServerOptions (String clientRequest) {
        this.clientRequest = clientRequest;
    }

    // Instances of the files that will be sent to the client upon request.
    File error = new File("resources/404.html");
    File index = new File("resources/index.html");
    File picture = new File("resources/spaceship.png");


    // Headers to be sent to the client in response of the respective request.
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


    // Returns the file object corresponding to the client's request.
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

    // Returns the HTTP header corresponding to the client's request.
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
