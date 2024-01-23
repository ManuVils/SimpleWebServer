package org.codeforall.ooptimus;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        int portNumber = 8085;

        {
            try {
                ServerSocket serverSocket = new ServerSocket(portNumber);
                Socket clientSocket = serverSocket.accept();

                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String requestReader = reader.readLine();
                String clientRequest = requestReader.split(" ")[1];

                ServerOptions serverOptions = new ServerOptions(clientRequest);

                FileInputStream inputStream = new FileInputStream(serverOptions.clientRequestedFile());
                OutputStream outputStream = clientSocket.getOutputStream();

                outputStream.write(serverOptions.fileRequestedHeader().getBytes());

                byte[] buffer = new byte[(int) serverOptions.clientRequestedFile().length()];
                inputStream.read(buffer);
                outputStream.write(buffer);



            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
