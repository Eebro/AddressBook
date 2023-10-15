package AddressBook;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest (classes = BuddyInfo.class)
public class BuddyInfoTest {

    @Autowired
    BuddyInfoRepository repo;


    @org.junit.jupiter.api.Test
    public void TestBuddy(){
        BuddyInfo b1 = new BuddyInfo("ibro", "123456789");
        System.out.println(b1);
        assertEquals("Name: ibro || Phone: 123456789", b1.printBuddy());
    }

    @org.junit.jupiter.api.Test

    public void TestGetName(){
        BuddyInfo b1 = new BuddyInfo("ibro", "123456789");
        assertEquals("ibro", b1.getName());
    }

    @org.junit.jupiter.api.Test
    public void TestGetPhone(){
        BuddyInfo b1 = new BuddyInfo("ibro", "123456789");
        assertEquals("123456789", b1.getPhoneNumber());
    }

    @org.junit.jupiter.api.Test
    public void TestSetName(){
        BuddyInfo b1 = new BuddyInfo("ibro", "123456789");
        assertEquals("ibro", b1.getName());
        b1.setName("ibrahim");
        assertEquals("ibrahim", b1.getName());
    }

    @org.junit.jupiter.api.Test
    public void TestSetPhone(){
        BuddyInfo b1 = new BuddyInfo("ibro", "123456789");
        assertEquals("123456789", b1.getPhoneNumber());
        b1.setPhoneNumber("613");
        assertEquals("613", b1.getPhoneNumber());
    }

    @org.junit.jupiter.api.Test
    public void TestBuddyInfoRepository(){
        BuddyInfo b1 = new BuddyInfo("ibro", "123456789");
        repo.save(b1);
        assertEquals(b1.getName() , repo.findById(b1.getId()).get().getName());
    }


}