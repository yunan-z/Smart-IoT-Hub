package zayn.iot_sim;

import java.util.List;
import java.util.TreeMap;

import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MqttCommands {
    private final TreeMap<String, PlugSim> plugs = new TreeMap<>();
    private final String topicPrefix;

    public MqttCommands(List<PlugSim> plugs, String topicPrefix) {
        for (PlugSim plug : plugs)
            this.plugs.put(plug.getName(), plug);
        this.topicPrefix = topicPrefix;
    }

    public String getTopic() {
        return topicPrefix + "action/#";
    }

    public void handleMessage(String topic, MqttMessage msg) {
        logger.info("MqttCmd {} {}", topic, msg);
        // switch on/off/toggle here
        String[] topicArray = topic.split("/");

        // code!!
        
        PlugSim plug = plugs.get(topicArray[2]);

        String topicState = topic.replace("action", "update");
        String[] topicStateArray = topicState.split("/");
        String topicPower;

        String actionString = topicArray[3];
        System.out.println("actionString is: " + actionString);
        System.out.println("-----------------");
        // String uporstate = topicArray[1];
        String powerString = " ";

        double power = 0;

        plugs.putIfAbsent(topicArray[1],new PlugSim(topicArray[1]));
        if (plug == null) {
            System.out.println(topicArray[2]);
            System.out.println("there is no plugs");
            System.out.println(topic);
        } else if ("on".equals(actionString)) {
            plug.switchOn();
            plug.measurePower();
            power = plug.getPower();
            powerString = "/" + String.valueOf(power);
            topicPower = topicStateArray[0] + "/" + topicStateArray[1] + "/" + topicStateArray[2] + powerString;

            System.out.println(topicState);
            System.out.println(topicPower);
        } else if ("toggle".equals(actionString)) {
            plug.toggle();
            plug.measurePower();
            power = plug.getPower();
            powerString = "/" + String.valueOf(power);
            topicPower = topicStateArray[0] + "/" + topicStateArray[1] + "/" + topicStateArray[2] + powerString;

            System.out.println(topicState);
            System.out.println(topicPower);
        } else if ("off".equals(actionString)) {
            plug.switchOff();
            plug.measurePower();
            power = plug.getPower();
            powerString = "/" + String.valueOf(power);
            topicPower = topicStateArray[0] + "/" + topicStateArray[1] + "/" + topicStateArray[2] + powerString;

            System.out.println(topicState);
            System.out.println(topicPower);
        } else {
            System.out.println(actionString);
            System.out.println("Wrong way");
        }
    }
    // }

    private static final Logger logger = LoggerFactory.getLogger(PlugSim.class);

}
