package zayn.iot_hub;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import zayn.iot_hub.PlugData;
import zayn.iot_hub.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GroupsResource {

	private final GroupsModel groups;
	private final MqttController mqtt;
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserRepositoryForGd userRepositoryForGd;
	public GroupsResource(GroupsModel groups, MqttController mqtt) throws Exception {
		this.groups = groups;
		this.mqtt = mqtt; 
		this.mqtt.start();
	}

	@RequestMapping("/ADD")
	public PlugData addPlug(@RequestParam String name,@RequestParam String state,@RequestParam int power ){
		PlugData pd=new PlugData(name,state,power);
		userRepository.save(pd);
		long count=userRepository.count();
		logger.info("count: {}", count);
		return pd;
	}
	@RequestMapping("/find")
	public PlugData find(@RequestParam Long id){
		PlugData user=userRepository.findOne(id);
		System.out.println(user);
		return user;
	}
	@RequestMapping("/update")
	public String update(@RequestParam Long id,@RequestParam String name){

		userRepository.updateNameById(id, name);
		return "updated";
	}
	@RequestMapping("/DelId")
	public String del(@RequestParam Long id){
		//Delete primary id=1 1l
		userRepository.delete(id);
		return"suc";
	}
	@RequestMapping("/DelOne")
	public String delOne(){
		//Delete primary id=1 1l
		PlugData user= new PlugData(3,"a","on",1);
		userRepository.delete(user);
		return"suc";
	}
	@RequestMapping("/DelAll")
	public String delAll(){
		//Delete all
		userRepository.deleteAll();
		return"succ del All";
	}
	@GetMapping("/api/plugs")
	public Collection<Object> getPlugs() throws Exception {
		ArrayList<Object> ret = new ArrayList<>();
		//plugs.getStates() is a TreeMap we have to get the key(use keySet) of this TreeMap
		//to traverse the plug
		for (String plug: mqtt.getStates().keySet()) {
			ret.add(makePlug(plug));
		}
		logger.info("Plugs: {}", ret);
		return ret;
	}

	@GetMapping("/api/plugs/{plug:.+}")
	public Object getPlug(
		@PathVariable("plug") String plug,
		@RequestParam(value = "action", required = false) String action) {
		if (action == null) {
			Object ret = makePlug(plug);
			logger.info("Plug {}: {}", plug, ret);
			return ret;
		}

		// modify code below to control plugs by publishing messages to MQTT broker
		// operation code
		try {
			mqtt.publishAction(plug, action);
			logger.info("publish sussess plug {} action{}", plug, action);
		} catch (Exception e) {
			System.out.println("fail to publish");
		}
		String state = mqtt.getState(plug);
		String power = mqtt.getPower(plug);
		logger.info("Plug {}: action {}, {}, {}", plug, action, state, power);
		return null;
	}

	@GetMapping("/api/groups")
	public Collection<Object> getGroups() throws Exception {
		ArrayList<Object> ret = new ArrayList<>();
		for (String group: groups.getGroups()) {
			ret.add(makeGroup(group));
		}
		logger.info("Groups: {}", ret);
		return ret;
	}

	@GetMapping("/api/groups/{group}")
	public Object getGroup(
		@PathVariable("group") String group,
		@RequestParam(value = "action", required = false) String action) {
		if (action == null) {
			Object ret = makeGroup(group);
			logger.info("Group {}: {}", group, ret);
			return ret;
		}

		// modify code below to control plugs by publishing messages to MQTT broker
		List<String> members = groups.getGroupMembers(group);
		logger.info("Group {}: action {}, {}", group, action, members);
		try {
			for (String member: members){
				mqtt.publishAction(member, action);
				logger.info("publish sussess member {} action{}", member, action);
			}
			
		} catch (Exception e) {
			System.out.println("fail to publish");
		}

		return null;
	}

	@PostMapping("/api/groups/{group}")
	public void createGroup(
		@PathVariable("group") String group,
		@RequestBody List<String> members) {
		groups.setGroupMembers(group, members);
		GroupData gd=new GroupData();
		gd.setGroup(group);
		String member=String.join(",", members);
		logger.info("members={}", member);
		gd.setMember(member);
		userRepositoryForGd.save(gd);
		logger.info("Group {}: created {}", group, members);
	}

	@DeleteMapping("/api/groups/{group}")
	public void removeGroup(
		@PathVariable("group") String group) {
		groups.removeGroup(group);
		logger.info("Group {}: removed", group);
	}

	protected Object makeGroup(String group) {
		// modify code below to include plug states
		HashMap<String, Object> ret = new HashMap<>();
		List<Object> members = new ArrayList<>();
		ret.put("name", group);
		for(String plugName: groups.getGroupMembers(group)){
			members.add(makePlug(plugName));
		}
		ret.put("members", members);
		return ret;
	}

	protected Object makePlug(String plugName) {
		// modify code below to include plug states
		HashMap<String, Object> ret = new HashMap<>();
		ret.put("name", plugName);
		ret.put("state", mqtt.getState(plugName));
		ret.put("power", mqtt.getPower(plugName));
		return ret;
	}
	private static final Logger logger = LoggerFactory.getLogger(GroupsResource.class);	



}
