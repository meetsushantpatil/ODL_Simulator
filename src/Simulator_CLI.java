import AMQP_Client.AMQPRecv;
import AMQP_Client.AMQPSend;
import MQTTClient.*;
public class Simulator_CLI {

	public static void main(String[] args) throws Exception 
	{
		// TODO Auto-generated method stub
		
		AMQPSend AMQPsClient  = new AMQPSend("localhost", "hello", "Hi, how's going ?");
		AMQPsClient.sendMessage();
		
		AMQPRecv AMQPrClient = new AMQPRecv("localhost", "hello");
		AMQPrClient.recvMessage();
		
		/*MQTTSend MQTTsClient = new MQTTSend("MQTT Examples", "Message from MqttPublishSample", 2, "tcp://127.0.0.1:1883", "JavaSample");
		MQTTsClient.sendMessage();
		
		MQTTRecv MQTTrClient = new MQTTRecv("tcp://127.0.0.1:1883","MQTT Examples" );
		MQTTrClient.recvMessage();*/
		
	}

}
