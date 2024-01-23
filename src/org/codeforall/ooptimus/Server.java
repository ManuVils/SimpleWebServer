package org.codeforall.ooptimus;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {

        // Port where the server will be listening to incoming connections.
        int portNumber = 8085;

        {
            try {

                // Creates a server socket that listens to connections on port number 8085.
                ServerSocket serverSocket = new ServerSocket(portNumber);

                // Accepts a connection from the client by calling the accept method on the serverSocket.
                // Once there is a connection, the method will return a Socket that will establish the connection to the client socket.
                Socket clientSocket = serverSocket.accept();

                // Reads and transforms the raw data from the connection between the client and server socket from bytes to characters.
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                // Reads the data coming from the client and stores it into the requestReader variable.
                String requestReader = reader.readLine();

                // Creates a new string array using the SPACE as the delimiter. It extracts the second element in the array, which is the resource requested.
                String clientRequest = requestReader.split(" ")[1];

                // Instance of the class ServerOptions where the server will retrieve the files and respective headers to send back to the client.
                ServerOptions serverOptions = new ServerOptions(clientRequest);

                // Allows the server to read the request from the client and to send an answer.
                FileInputStream inputStream = new FileInputStream(serverOptions.clientRequestedFile());
                OutputStream outputStream = clientSocket.getOutputStream();

                // Writing of the respective header (converted to bytes) of the server's response into the outputStream.
                outputStream.write(serverOptions.fileRequestedHeader().getBytes());

                // Creation of a new array with the size of the client request file.
                byte[] buffer = new byte[(int) serverOptions.clientRequestedFile().length()];

                // Reads the data (requested file) coming from the inputStream and stores it into the buffer.
                inputStream.read(buffer);

                // Writes the content of the buffer into the outputStream, which is sent to the client.
                outputStream.write(buffer);



            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
