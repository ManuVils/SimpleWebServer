package org.codeforall.ooptimus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
                

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
