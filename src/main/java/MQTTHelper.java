import org.eclipse.paho.client.mqttv3.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class MQTTHelper implements MqttCallback, PropertyChangeListener {

    private MqttClient client;
    private VLCControl vlc = new VLCControl();


    private MQTTHelper() {
        vlc.getSupport().addPropertyChangeListener(this);
        if (vlc.isConnected()) {
            return;
        }
        try {
            vlc.connect();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    static void run(String topic, String clientId) {
        new MQTTHelper().connect(topic,clientId);
    }

    private void connect(String topic,String clientId) {
        try {
            client = new MqttClient(SecureDataHelper.server, clientId);
            MqttConnectOptions options = new MqttConnectOptions();
            options.setPassword(SecureDataHelper.password.toCharArray());
            options.setUserName(SecureDataHelper.username);
            client.connect(options);
            client.setCallback(this);
            client.subscribe(topic);
            //MqttMessage message = new MqttMessage();
            //message.setPayload("A single message from my computer fff".getBytes());
            //client.publish("foo", message);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connectionLost(Throwable cause) {
        // TODO Auto-generated method stub

    }

    @Override
    public void messageArrived(String topic, MqttMessage message) {
        try {
            vlc.sendCommand(message.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        // TODO Auto-generated method stub

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}