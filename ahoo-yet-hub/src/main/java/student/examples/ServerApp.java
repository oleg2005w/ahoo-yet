package student.examples;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import student.examples.comm.CommandType;
import student.examples.comm.ServerCommand;
import student.examples.config.Configuration;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//import java.util.logging.Logger;

/**
 * Hello world!
 *
 */
public class ServerApp {
    final static Logger logger = LoggerFactory.getLogger(ServerApp.class);
    private Set<Map<String, Object>> connections;
    private ServerSocket serverSocket;
    public ServerApp(int port) throws IOException {
        create(port);
    }
    private void create(int port) throws IOException {
        connections = new HashSet<>();
        serverSocket = new ServerSocket(port, 0, InetAddress.getLocalHost());
    }
    private void listen() throws IOException {
        while (true){
            Socket clientSocket = serverSocket.accept();
            logger.info(String.format("CLIENT: connected! -> %s" + clientSocket.getInetAddress()));
            Map<String, Object> client = new HashMap<>();
            client.put("socket", clientSocket);
            connections.add(client);
        }
    }
    public static void main( String[] args ) throws IOException {
        logger.info("SERVER: init!");
        ServerApp app = new ServerApp(10000);
        logger.info("Started!");
        app.listen();
        logger.info("SERVER: stop!");

//        logger.info("Started!");
//
//
//        ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
//        ServerCommand turnOnCommand = new ServerCommand(CommandType.TURN_ON);
//        oos.writeObject(turnOnCommand);
//        logger.info("Stoped!");
//        System.out.println( "Hello World!" );
    }
}
