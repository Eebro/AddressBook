package AddressBook;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Entity
public class AddressBook {

    @Id
    @GeneratedValue
    private Integer id = null;
    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private List<BuddyInfo> buddyList;

    public AddressBook(){
        buddyList = new ArrayList<BuddyInfo>();
    }

    public List<BuddyInfo> getBuddyList() {
        return buddyList;
    }
    public String printBuddyList() {
        StringBuilder result = new StringBuilder();
        for (BuddyInfo bud : buddyList) {
            result.append("Name: ").append(bud.getName()).append(" || Phone: ").append(bud.getPhoneNumber()).append("\n");
        }
        return result.toString();
    }

    public void addBuddy(BuddyInfo newBuddy) {
        this.buddyList.add(newBuddy);
    }
    public void removeInfo(BuddyInfo buddy){
        buddyList.remove(buddy);
    }

    public Integer getId(){
        return id;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AddressBook {").append("\n");
        sb.append("  id=").append(id).append("\n");

        sb.append("  BuddyList [").append("\n");
        for (BuddyInfo buddy : buddyList) {
            sb.append("    ").append(buddy.toString()).append("\n");
        }
        sb.append("  ]").append("\n");

        sb.append("}").append("\n");
        return sb.toString();
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public static void main(String[] args) {
        AddressBook myBook = new AddressBook();

        BuddyInfo bud1 = new BuddyInfo("Ibrahim", "1234567");
        BuddyInfo bud2 = new BuddyInfo("Quagmire", "1234567");

        myBook.addBuddy(bud1);
        myBook.addBuddy(bud2);

        System.out.println(myBook.printBuddyList());


    }
}
