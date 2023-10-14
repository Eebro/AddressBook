package AddressBook;


import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@Entity
public class BuddyInfo {
    @Id
    @GeneratedValue
    private Integer id = null;
    private String name = null;
    private String phoneNumber = null;

    public BuddyInfo (String buddyName, String buddyPhoneNumber){
        name = buddyName;
        phoneNumber = buddyPhoneNumber;
    }
    public BuddyInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BuddyInfo{id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phoneNumber + '\'' +
                '}';
    }
    public String printBuddy() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(this.name).append(" || Phone: ").append(phoneNumber);
        return sb.toString();
    }
}
