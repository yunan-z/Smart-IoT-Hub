package zayn.iot_sim;

import static org.junit.Assert.*;
import zayn.iot_sim.PlugSim;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
import org.junit.Test;

public class HTTPCommandsTests {
    @Test
    public void test1() {

        PlugSim plug = new PlugSim("a");
        ArrayList<PlugSim> plugs = new ArrayList<>();
        plugs.add(plug);
        HTTPCommands test = new HTTPCommands(plugs);
        String path = "/";
        HashMap<String,String> params= new HashMap<>();
        params.put("action", "on");
        test.handleGet(path, params);
        test.listPlugs();
        test.report(plug);
}
@Test
    public void test2() {

        PlugSim plug = new PlugSim("a");
        ArrayList<PlugSim> plugs = new ArrayList<>();
        plugs.add(plug);
        HTTPCommands test = new HTTPCommands(plugs);
        String path = "/a";
        HashMap<String,String> params= new HashMap<>();
        params.put("action","on");
        test.handleGet(path, params);
        params.put("action","toggle");
        test.handleGet(path, params);
        params.put("action","toggle");
        test.handleGet(path, params);
        params.put("action","off");
        test.handleGet(path, params);
        test.listPlugs();
        test.report(plug);
}
@Test
    public void test3() {
        PlugSim plug = new PlugSim("a");
        ArrayList<PlugSim> plugs = new ArrayList<>();
        plugs.add(plug);
        HTTPCommands test = new HTTPCommands(plugs);
        String path = "/a";
        HashMap<String,String> params= new HashMap<>();
        params.put("actio","bad");
        test.handleGet(path, params);
        test.listPlugs();
        test.report(plug);
}
@Test
    public void test4() {
        PlugSim plug = new PlugSim("a");
        PlugSim plug1 = new PlugSim("b");
        ArrayList<PlugSim> plugs = new ArrayList<>();
        plugs.add(plug);
        plugs.add(plug1);
        HTTPCommands test = new HTTPCommands(plugs);
        String path = "/a";
        HashMap<String,String> params= new HashMap<>();
        params.put("action","on");
        test.handleGet(path, params);
        test.listPlugs();
        test.report(plug);
}
public void test5() {

    PlugSim plug = new PlugSim("a");
    ArrayList<PlugSim> plugs = new ArrayList<>();
    plugs.add(plug);
    HTTPCommands test = new HTTPCommands(plugs);
    String path = "/a";
    HashMap<String,String> params= new HashMap<>();
    params.put("action", "off");
    test.handleGet(path, params);
    test.listPlugs();
    test.report(plug);
}
public void test6() {

    PlugSim plug = new PlugSim("a");
    ArrayList<PlugSim> plugs = new ArrayList<>();
    plugs.add(plug);
    HTTPCommands test = new HTTPCommands(plugs);
    String path = "/a";
    HashMap<String,String> params= new HashMap<>();
    params.put("action", "on");
    test.handleGet(path, params);
    params.put("action","toggle");
    test.handleGet(path, params);
    test.listPlugs();
    test.report(plug);
}
public void test7() {

    PlugSim plug = new PlugSim("a");
    PlugSim plug1 = new PlugSim("b");
    ArrayList<PlugSim> plugs = new ArrayList<>();
    plugs.add(plug);
    plugs.add(plug1);
    HTTPCommands test = new HTTPCommands(plugs);
    String path = "/b";
    HashMap<String,String> params= new HashMap<>();
    params.put("action", "on");
    test.handleGet(path, params);
    path="/a";
    test.handleGet(path, params);
    test.listPlugs();
    test.report(plug);
}
public void test8() {

    PlugSim plug = new PlugSim("a");
    PlugSim plug1 = new PlugSim("b");
    ArrayList<PlugSim> plugs = new ArrayList<>();
    plugs.add(plug);
    plugs.add(plug1);
    HTTPCommands test = new HTTPCommands(plugs);
    String path = "/a";
    HashMap<String,String> params= new HashMap<>();
    params.put("action", "on");
    test.handleGet(path, params);
    path = "/b";
    test.handleGet(path, params);
    params.put("action","toggle");
    test.handleGet(path, params);
    test.listPlugs();
    test.report(plug);
}
public void test9() {

    PlugSim plug = new PlugSim("a");
    PlugSim plug1 = new PlugSim("b");
    ArrayList<PlugSim> plugs = new ArrayList<>();
    plugs.add(plug);
    plugs.add(plug1);
    HTTPCommands test = new HTTPCommands(plugs);
    String path = "/a";
    HashMap<String,String> params= new HashMap<>();
    params.put("action", "on");
    test.handleGet(path, params);
    path="/b";
    test.handleGet(path, params);
    params.put("action", "off");
    test.handleGet(path, params);
    path="/a";
    test.handleGet(path, params);
    test.listPlugs();
    test.report(plug);
}
public void test10() {

    PlugSim plug = new PlugSim("a");
    PlugSim plug1 = new PlugSim("b");
    PlugSim plug2 = new PlugSim("c");
    ArrayList<PlugSim> plugs = new ArrayList<>();
    plugs.add(plug);
    plugs.add(plug1);
    plugs.add(plug2);
    HTTPCommands test = new HTTPCommands(plugs);
    String path = "/";
    HashMap<String,String> params= new HashMap<>();
    params.put("action", "on");
    test.handleGet(path, params);
    test.listPlugs();
    test.report(plug);
}
}
