package zayn.iot_hub;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="groups")
public class GroupData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     private long id;
 
     @Column(name="groupName")
     private String groupName;
     @Column(name="member")
     private String memberName;
     public void setGroup(String group){this.groupName=group;}
     public void setMember(String members){this.memberName=members;}
     public GroupData(String groupName, String members){
         this.groupName=groupName;
         this.memberName=members;
     }
     public GroupData(){}
}