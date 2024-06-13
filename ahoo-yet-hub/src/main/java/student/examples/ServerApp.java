package student.examples;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import student.examples.comm.Command;
import student.examples.comm.CommandType;
import student.examples.comm.ServerCommand;
import student.examples.config.Configuration;
import student.examples.devices.DeviceInterface;

import java.io.IOException;
import java.io.ObjectInputStream;
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
    private void listen() throws IOException, ClassNotFoundException {
        while (true){
            Socket clientSocket = serverSocket.accept();
            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            Command command = (Command) ois.readObject();
            if (command.getType() == CommandType.IDENTITY){
                logger.info(String.format("CLIENT: connected! -> %s", clientSocket.getInetAddress()));
                DeviceInterface deviceInterface = (DeviceInterface) command.getBody();
                Map<String, Object> client = new HashMap<>();
                client.put("socket", clientSocket);
                client.put("device", deviceInterface);
                connections.add(client);
            }
            //send to client
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
            Command command1 = new ServerCommand(CommandType.ACKNOWLEDGE, null);
            oos.writeObject(command1);
            oos.flush();

            connections.forEach(conn -> {
                System.out.println(connections);
            });
            connections.forEach(conn -> {
                System.out.println(conn);
            });

        }
    }
    public static void main( String[] args ) throws IOException, ClassNotFoundException {
        logger.info("SERVER: init!");
        ServerApp app = new ServerApp(Configuration.PORT);
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
