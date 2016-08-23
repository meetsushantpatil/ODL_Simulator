
public class Simulator_CLI {

	public static void main(String[] args) throws Exception {
		
		//System.out.println(args[0]);
		// TODO Auto-generated method stub
		
		AMQPSend AMQPSender = new AMQPSend("QueueName", "localhost");
		AMQPSender.sendMessage();
		
		AMQPRecv AMQPRecv = new AMQPRecv("QueueName", "localhost");
		AMQPRecv.recvMessage();
		
//		MQTTSend MQTTSender = new MQTTSend("localhost", "Topic", "Hello !" );
//		MQTTSender.sendMessage();
		
		MQTTRecv MQTTRecver = new MQTTRecv("localhost", "Topic");
		MQTTRecver.recvMessage();
		
		MQTTSend MQTTSender = new MQTTSend("localhost", "Topic", "Hello !" );
		MQTTSender.sendMessage();
		
		MQTTSender.sendMessage();
		
		//hi
		
		
//		Thread.sleep(1000);
//		System.exit(0);
		
		
	}

}
