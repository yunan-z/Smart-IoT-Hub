package zayn.iot_sim;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlugSimTests {

	@Test
	public void testInit() {
		PlugSim plug = new PlugSim("a");

		assertFalse(plug.isOn());
	}

	@Test
	public void testSwitchOn() {
		PlugSim plug = new PlugSim("a");

		plug.switchOn();

		assertTrue(plug.isOn());
	}
	@Test
	public void testToggle1() {
		PlugSim plug = new PlugSim("a");

		plug.toggle();

		assertTrue(plug.isOn());
	}
	@Test
	public void testToggle2() {
		PlugSim plug = new PlugSim("a");
		plug.switchOn();
		plug.toggle();

		assertTrue(!plug.isOn());
	}
	@Test
	public void testSwitchOff() {
		PlugSim plug = new PlugSim("a");

		plug.switchOn();
		plug.switchOff();
		assertTrue(!plug.isOn());
	}
	@Test
	public void test() {
		PlugSim plug = new PlugSim("a");

		plug.getName();
		plug.measurePower();
		plug.updatePower(250);
		plug.measurePower();
		plug.getPower();
	}
	@Test
	public void measurePower(){
		PlugSim plug= new PlugSim("3.3");
		plug.switchOn();
		plug.measurePower();
	}
	@Test
	public void measurePower1(){
		PlugSim plug= new PlugSim("a");
		plug.switchOn();
		plug.updatePower(50);
		plug.measurePower();
	}
	@Test
	public void measurePower2(){
		PlugSim plug= new PlugSim("a");
		plug.switchOn();
		plug.updatePower(400);
		plug.measurePower();
	}
	@Test
	public void measurePower3(){
		PlugSim plug= new PlugSim("a");
		plug.switchOn();
		plug.updatePower(270);
		plug.measurePower();
	}
}
