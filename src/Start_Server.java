
import java.io.IOException;
import java.lang.ProcessBuilder;

public class Start_Server {

	public static void startServer() throws IOException {
		try {
		 ProcessBuilder pb = new ProcessBuilder("/Users/Sushant/Downloads/rabbitmq_server-3.6.2/sbin/rabbitmq-server.sh");
		 pb.start();
		 
		}
		catch(Exception me) {
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        }

	}

}

