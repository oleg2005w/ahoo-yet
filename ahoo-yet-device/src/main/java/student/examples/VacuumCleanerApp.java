package student.examples;

import student.examples.comm.ClientCommand;
import student.examples.comm.Command;
import student.examples.comm.CommandType;
import student.examples.comm.ServerCommand;
import student.examples.config.Configuration;
import student.examples.devices.VacuumCleaner;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 * Hello world!
 *
 */
public class VacuumCleanerApp
{
    final static Logger logger = LoggerFactory.getLogger(VacuumCleanerApp.class);
    public static void main( String[] args ) throws IOException, ClassNotFoundException {
        logger.info("Starting logs!");
        Socket socket = new Socket(InetAddress.getLocalHost(), Configuration.PORT);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        VacuumCleaner vacuumCleaner = new VacuumCleaner(1, "Atom");
        ClientCommand clientCommand= new ClientCommand(CommandType.IDENTITY, vacuumCleaner);
        oos.writeObject(clientCommand);
        oos.flush();

        //from server
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Command respServer = (Command) ois.readObject();
        if (respServer.getType() == CommandType.ACKNOWLEDGE){
            System.out.println("Server responded!!!");
        }
    }
}
