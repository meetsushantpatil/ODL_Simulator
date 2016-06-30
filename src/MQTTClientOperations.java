import java.io.IOException;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MQTTClientOperations {

    /**
     * @param args
     *
     */
	
	
    
    public static void main(String[] args) throws IOException {
    	String topic        = "MQTT Examples";
        String content      = "Message from MqttPublishSample";
        int qos             = 2;
        String broker       = "tcp://127.0.0.1:1883";
        String clientId     = "JavaSample";

        try {
        	
        	Start_MQTT_Server.startServer();
            MqttClient sampleClient = createClient(broker, clientId);
            
            connectClient(broker, sampleClient);
            System.out.println("Connecting to broker: "+broker);
            System.out.println("Connected");
            
            System.out.println("Publishing message: "+content);
            publishMessage(content, qos, topic, sampleClient);
            
            System.out.println("Message published");
            disconnectClient(sampleClient);
            System.out.println("Disconnected");
            System.exit(0);
        } catch(MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        }
    }
    public static void connectClient(String broker, MqttClient client) throws MqttException
    {
    	MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        client.connect(connOpts);
        
    }
    
    public static MqttClient createClient(String broker, String clientId) throws MqttException
    {
    	MemoryPersistence persistence = new MemoryPersistence();
    	MqttClient client = new MqttClient(broker, clientId, persistence);
    	return client;
    }
    
    public static void publishMessage(String content, int QoS, String topic, MqttClient client) throws MqttException
    {
    	MqttMessage message = new MqttMessage(content.getBytes());
        message.setQos(QoS);
        client.publish(topic, message);
    }
    
    public static void disconnectClient(MqttClient client) throws MqttException
    {
    	client.disconnect();
    }
}
