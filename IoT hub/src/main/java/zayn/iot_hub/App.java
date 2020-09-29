package zayn.iot_hub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;






@SpringBootApplication
public class App {

	@Bean(destroyMethod = "close")
	public MqttController mqttController(Environment env) throws Exception {
		String broker = env.getProperty("mqtt.broker");
		String clientId = env.getProperty("mqtt.clientId");
		String topicPrefix = env.getProperty("mqtt.topicPrefix");
		MqttController mqtt = new MqttController(broker, clientId, topicPrefix);
		logger.info("MqttClient {} connected: {}", clientId, broker);
		return mqtt;
	}

	private static final Logger logger = LoggerFactory.getLogger(App.class);

}

