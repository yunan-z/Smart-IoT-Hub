package zayn.iot_hub;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
@Entity
@Table(name="test")
public class PlugData {
    public PlugData(){}
   public PlugData(String plug,String state,int power){
    this.plug=plug;
    this.state=state;
    this.power=power;
}
public PlugData(int id,String plug,String state,int power){
    this.id=id;
    this.plug=plug;
    this.state=state;
    this.power=power;
}
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="plug")
    private String plug;
    @Column(name="state")
    private String state;
    @Column(name="power")
    private int power;

    public long getId(){return id;}
    public void setId(long id){this.id=id;}
    public String getPlug(){return plug;}
    public void setPlug(String plug){this.plug=plug;}
    public String getState(){return state;}
    public void setState(String state){this.state=state;}
    public int getPower(){return power;}
    public void setPower(int power){this.power=power;}
 
}