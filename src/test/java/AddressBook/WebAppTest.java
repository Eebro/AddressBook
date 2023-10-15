package AddressBook;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@ContextConfiguration(classes=AddressBook.class )
@SpringBootTest
@AutoConfigureMockMvc
public class WebAppTest{
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AddressBookRepository repo;
    @Autowired
    private BuddyInfoRepository buddyRepo;

    @Test
    public void shouldReturnAddress() throws Exception {
        this.mockMvc.perform(get("/")).andExpect(content().string(containsString("hai")));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getAllAddressBooks() throws Exception {
        AddressBook a1 = new AddressBook();
        BuddyInfo b1 = new BuddyInfo("Ibro","6131234567");
        a1.addBuddy(b1);
        repo.save(a1);
        this.mockMvc.perform(get("/getAddressbook")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @Order(1)
    public void shouldGetOneAddressBook() throws Exception {
        AddressBook a1 = new AddressBook();
        BuddyInfo b1 = new BuddyInfo("Ibro","6131234567");
        a1.addBuddy(b1);
        repo.save(a1);
        this.mockMvc.perform(get("/getOneAddressbook?id=" + a1.getId())).andDo(print()).andExpect(content().string(containsString("name=&#39;Ibro&#39;, phone=&#39;6131234567&#39;")));
    }


    @Test
    public void getBuddys() throws Exception {
        AddressBook a1 = new AddressBook();
        BuddyInfo b1 = new BuddyInfo("Ibro","6131234567");
        a1.addBuddy(b1);
        repo.save(a1);
        this.mockMvc.perform(get("/getBuddies")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void addAddressBook() throws Exception {
        AddressBook a1 = new AddressBook();
        BuddyInfo b1 = new BuddyInfo("Ibro","6131234567");
        a1.addBuddy(b1);
        String body = asJsonString(a1);
        this.mockMvc.perform(post("/addAddressbook")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void addBuddy() throws Exception {
        AddressBook a1 = new AddressBook();
        BuddyInfo b1 = new BuddyInfo(1, "Ibro","6131234567");
        repo.save(a1);
        String body = asJsonString(b1);
        this.mockMvc.perform(post("/addBuddy")
                        .content(asJsonString(b1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(content().string(containsString("")));
    }

    @Test
    public void deleteBuddy() throws Exception {
        AddressBook a1 = new AddressBook();
        a1.setId(1);
        BuddyInfo b1 = new BuddyInfo(1, "Ibro","6131234567");
        repo.save(a1);
        buddyRepo.save(b1);
        String body = asJsonString(b1);
        this.mockMvc.perform(delete("/delBuddy?addressId=1&buddyId=1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());
    }
}
