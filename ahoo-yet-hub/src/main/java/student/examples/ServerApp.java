package student.examples;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import student.examples.comm.CommandType;
import student.examples.comm.ServerCommand;
import student.examples.config.Configuration;

import java.io.IOException;
import java.io.ObjectOutputStream;
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

    public static void main( String[] args ) {
        Set<Map<String, Object>> connections;
        connections = new HashSet<>();
        Map<String, Object> client1 = new HashMap<>();
        client1.put("ip", "192.168.0.1");
        client1.put("id", 1);//autoboxing

        Map<String, Object> client2 = new HashMap<>();
        client2.put("ip", "192.168.0.8");
        client2.put("id", 11);//autoboxing

        connections.add(client1);
        connections.add(client2);


        connections.forEach(System.out::println);
        connections.forEach(connection -> {
            System.out.println(connection);
        });
        connections.forEach(connection -> {
            System.out.println(connections);
        });
    }
}
