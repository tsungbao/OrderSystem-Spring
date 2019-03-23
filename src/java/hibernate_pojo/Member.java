package hibernate_pojo;
// Generated 2019/3/21 �U�� 02:57:08 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Member generated by hbm2java
 */
@Entity
@Table(name="member"
    ,catalog="test"
    , uniqueConstraints = @UniqueConstraint(columnNames="account") 
)
public class Member  implements java.io.Serializable {


     private Integer memberId;
     private String memberName;
     private String memberPhone;
     private String account;
     private String password;
     private Integer consumptionTimes;
     private Integer totalConsumption;
     private String memberStatus;
     private Set orders = new HashSet(0);

    public Member() {
    }

	
    public Member(String memberName, String memberPhone, String account, String password) {
        this.memberName = memberName;
        this.memberPhone = memberPhone;
        this.account = account;
        this.password = password;
    }
    public Member(String memberName, String memberPhone, String account, String password, Integer consumptionTimes, Integer totalConsumption, String memberStatus, Set orders) {
       this.memberName = memberName;
       this.memberPhone = memberPhone;
       this.account = account;
       this.password = password;
       this.consumptionTimes = consumptionTimes;
       this.totalConsumption = totalConsumption;
       this.memberStatus = memberStatus;
       this.orders = orders;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="member_id", unique=true, nullable=false)
    public Integer getMemberId() {
        return this.memberId;
    }
    
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    
    @Column(name="member_name", nullable=false, length=10)
    public String getMemberName() {
        return this.memberName;
    }
    
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    
    @Column(name="member_phone", nullable=false, length=20)
    public String getMemberPhone() {
        return this.memberPhone;
    }
    
    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    
    @Column(name="account", unique=true, nullable=false, length=15)
    public String getAccount() {
        return this.account;
    }
    
    public void setAccount(String account) {
        this.account = account;
    }

    
    @Column(name="password", nullable=false, length=15)
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    
    @Column(name="consumption_times")
    public Integer getConsumptionTimes() {
        return this.consumptionTimes;
    }
    
    public void setConsumptionTimes(Integer consumptionTimes) {
        this.consumptionTimes = consumptionTimes;
    }

    
    @Column(name="total_consumption")
    public Integer getTotalConsumption() {
        return this.totalConsumption;
    }
    
    public void setTotalConsumption(Integer totalConsumption) {
        this.totalConsumption = totalConsumption;
    }

    
    @Column(name="member_status", length=10)
    public String getMemberStatus() {
        return this.memberStatus;
    }
    
    public void setMemberStatus(String memberStatus) {
        this.memberStatus = memberStatus;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="member")
    public Set getOrders() {
        return this.orders;
    }
    
    public void setOrders(Set orders) {
        this.orders = orders;
    }




}


