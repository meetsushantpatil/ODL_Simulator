	 import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
     import org.eclipse.paho.client.mqttv3.MqttCallback;
     import org.eclipse.paho.client.mqttv3.MqttException;
     import org.eclipse.paho.client.mqttv3.MqttMessage;
     import org.eclipse.paho.client.mqttv3.MqttClient;

     public class Test_A_2 implements MqttCallback {

     MqttClient clientR;
     MqttClient clientS;

     public Test_A_2() {
     }

     public static void main(String[] args) throws InterruptedException {
         new Test_A_2().doDemo();
     }

    public void doDemo() throws InterruptedException {
    try {   
    clientS = new MqttClient("tcp://localhost:1883", "Sender");
    clientR = new MqttClient("tcp://localhost:1883", "Receiver");
    clientR.connect();
    clientS.connect();
    MqttMessage message = new MqttMessage();

    String messagePayload = "hi";

    clientR.subscribe("BenchMQTT");   
    clientR.setCallback(this);
    
    for(int i=0;i<10;i++)
    {
    message.setPayload((messagePayload)
            .getBytes());
    clientS.publish("BenchMQTT", message);
    Thread.sleep(1);
    }
    clientR.disconnect();   
    clientS.disconnect();
    clientR.close();   
    clientS.close();

   } catch (MqttException e)
    {
     System.out.println("ERROR");
    }
 }

     @Override
     public void connectionLost(Throwable cause) {
         // TODO Auto-generated method stub

     }

     @Override
     public void messageArrived(String topic, MqttMessage message)
     {
         System.out.println("Received: " + message.toString());
     }

     @Override
     public void deliveryComplete(IMqttDeliveryToken token) {

     }

     }