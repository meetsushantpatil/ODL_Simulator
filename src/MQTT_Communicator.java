import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttClient;

public class MQTT_Communicator implements MqttCallback {

	MqttClient clientR;
	MqttClient clientS;
	String receivedMessage;
	static String brokerIP;
	static int connections;

	public MQTT_Communicator() {
	}

	public static void main(String[] args) throws InterruptedException {
	brokerIP = args[0];
    new MQTT_Communicator().receive_MQTT_message(brokerIP);
	}

	public void receive_MQTT_message(String brokerIP) throws InterruptedException {
		try {   
			clientS = new MqttClient("tcp://"+brokerIP+":1883", "Sender");
			clientR = new MqttClient("tcp://"+brokerIP+":1883", "Receiver");
			clientR.connect();
			clientS.connect();
			MqttMessage message = new MqttMessage();

			String messagePayload = "Message 0";

			clientR.subscribe("MQTT Examples");   
			clientR.setCallback(this);

				for(int i=0;i<10;i++)
				{
					messagePayload = "Message " + i;
					message.setPayload((messagePayload).getBytes());
					clientS.publish("MQTT Examples", message);
					System.out.println("Message Sent :" + messagePayload);
					Thread.sleep(10);
					
					System.out.println("Message Received :" + getMessage());
				}
				
				
				clientR.disconnect();   
				clientS.disconnect();
				clientR.close();   
				clientS.close();

		} 
		catch(MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        }
}

	@Override
	public void connectionLost(Throwable cause) {
    // TODO Auto-generated method stub

	}

	@Override
	public void messageArrived(String topic, MqttMessage message)
	{
		//System.out.println("Received: " + message.toString());
		receivedMessage = message.toString();
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {

	}
	
	public String getMessage()
	{
		return receivedMessage;
	}

}