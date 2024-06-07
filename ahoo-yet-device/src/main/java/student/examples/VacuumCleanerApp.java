package student.examples;

import student.examples.comm.CommandType;
import student.examples.comm.ServerCommand;
import student.examples.config.Configuration;
import student.examples.devices.VacuumCleaner;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
       VacuumCleaner vacuumCleaner = new VacuumCleaner();
        logger.info("Started!");
        logger.info(String.format("%b", vacuumCleaner.isOn()));
        Socket socket = new Socket(Configuration.HOST, Configuration.PORT);
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ServerCommand serverCommand = (ServerCommand) ois.readObject();

        if (serverCommand.getType().equals(CommandType.TURN_ON)){
            vacuumCleaner.switchOn();
        }
        logger.info(String.format("%b", vacuumCleaner.isOn()));
        logger.info("Stoped!");
    }
}
