package zayn.iot_hub;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.junit.Test;

import zayn.iot_sim.SimConfig;

public class test implements AutoCloseable {
    private static final String broker = "tcp://127.0.0.1";
    private static final String topicPrefix = "iot_zayn/";
    MqttController mqtt;
    private static final ObjectMapper mapper = new ObjectMapper();
    
    public test() throws Exception {
        this.mqtt = new MqttController(broker, "test/iot_hub", topicPrefix);
        this.mqtt.start();
    }

    @Override
    public void close() throws Exception {
        mqtt.close();
    }

    private static final List<String> plugNames = Arrays.asList("a", "b", "c");
    private static final List<String> groupNames = Arrays.asList("x", "y", "z");

    private String getHub(String pathParams) throws Exception {
        return Request.Get("http://127.0.0.1:8088" + pathParams).userAgent("Mozilla/5.0").connectTimeout(1000)
                .socketTimeout(1000).execute().returnContent().asString();
    }
    private void postGroup(String group, List<String> members) throws Exception {
		Request.Post("http://127.0.0.1:8088/api/groups/" + group)
			.bodyByteArray(mapper.writeValueAsBytes(members), ContentType.APPLICATION_JSON)
			.userAgent("Mozilla/5.0").connectTimeout(1000)
			.socketTimeout(1000).execute();
	}

    
	private void delGroup(String group) throws Exception {
		Request.Delete("http://127.0.0.1:8088/api/groups/" + group)
			.userAgent("Mozilla/5.0").connectTimeout(1000)
			.socketTimeout(1000).execute();
	}

    @Test
    public void test1() {

        SimConfig config = new SimConfig(8080, plugNames, broker, "testee/iot_sim", topicPrefix);
        HubConfig hubConfig = new HubConfig(8088, broker, "testee/iot_hub", topicPrefix);
        try {
            zayn.iot_sim.Main m = new zayn.iot_sim.Main(config);
            zayn.iot_hub.Main hub = new zayn.iot_hub.Main(hubConfig, new String[0]);
            //testcase01
            getHub("/api/plugs/a?action=on");
            getHub("/api/plugs/a?action=off");
            getHub("/api/plugs/a?action=toggle");
            //testcase02
            getHub("/api/plugs"); 
            //testcase03
            getHub("/api/plugs/a"); 
            //testcase04
            getHub("/api/plugs/p"); 
            //testcase05
            getHub("/api/groups/"); 
            //testcase06
            getHub("/api/groups/x"); 
            //testcase07
            getHub("/api/groups/x?action=on");
            getHub("/api/groups/x?action=off");
            getHub("/api/groups/x?action=toggle");
            //testcase08
            getHub("/api/groups/a"); 
            //testcase09
            postGroup("y", Arrays.asList("e", "f", "g"));
            postGroup("l", Arrays.asList("q", "p", "o"));
            getHub("/api/groups/y?action=on");
            getHub("/api/groups/i?action=on");
            getHub("/api/groups/"); 
            //testcase10
            delGroup("x");
            //testcase11
            getHub("/api/ppp/ppp/ppp/ppp"); 
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         
            
    
       
    }
}