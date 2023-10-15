package AddressBook;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest (classes = AddressBook.class)
public class AddressBookTest {

    @Autowired
    AddressBookRepository repo;

    @org.junit.jupiter.api.Test
    public void TestBook() {
        AddressBook a1 = new AddressBook();
        a1.addBuddy(new BuddyInfo("ibro", "613"));
        a1.addBuddy(new BuddyInfo("bobby", "123"));
        assertEquals("Name: ibro || Phone: 613\n" +
                "Name: bobby || Phone: 123\n", a1.printBuddyList());
    }


    @org.junit.jupiter.api.Test
    public void TestBookAlternate() {
        AddressBook a1 = new AddressBook();
        a1.addBuddy(new BuddyInfo("bob", "6131112222"));
        a1.addBuddy(new BuddyInfo("ibro", "6131234567"));
        System.out.println(a1);
        assertEquals("Name: bob || Phone: 6131112222\n" +
                "Name: ibro || Phone: 6131234567\n", a1.printBuddyList());
    }

    @org.junit.jupiter.api.Test
    public void testGetter() {
        AddressBook a1 = new AddressBook();
        ArrayList<BuddyInfo> b1 = new ArrayList<BuddyInfo>();
        BuddyInfo b2 = new BuddyInfo("bob", "6131112222");
        BuddyInfo b3 = new BuddyInfo("ibro", "6131234567");
        b1.add(b2);
        b1.add(b3);
        a1.addBuddy(b2);
        a1.addBuddy(b3);
        assertEquals(b1, a1.getBuddyList());
    }

    @org.junit.jupiter.api.Test
    public void testRemove() {
        AddressBook a1 = new AddressBook();
        ArrayList<BuddyInfo> b1 = new ArrayList<BuddyInfo>();
        BuddyInfo b2 = new BuddyInfo("bob", "6131112222");
        BuddyInfo b3 = new BuddyInfo("ibro", "6131234567");
        b1.add(b2);
        b1.add(b3);
        a1.addBuddy(b2);
        a1.addBuddy(b3);
        assertEquals(b1, a1.getBuddyList());
        a1.removeInfo(b2);
        assertEquals("Name: ibro || Phone: 6131234567\n", a1.printBuddyList());
    }

    @org.junit.jupiter.api.Test
    public void testAddressBookJPA() {
        AddressBook a1 = new AddressBook();
        BuddyInfo b2 = new BuddyInfo("bob", "6131112222");
        BuddyInfo b3 = new BuddyInfo("ibro", "6131234567");
        a1.addBuddy(b2);
        a1.addBuddy(b3);
        repo.save(a1);
        a1.printBuddyList();
        System.out.println(repo.findById(a1.getId()).get());
        assertEquals(a1.getBuddyList().size(), repo.findById(a1.getId()).get().getBuddyList().size());
    }
}