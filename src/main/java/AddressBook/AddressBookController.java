package AddressBook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class AddressBookController {
    @Autowired
    AddressBookRepository addressRepo;
    @Autowired
    BuddyInfoRepository buddyRepo;

    public AddressBookController(AddressBookRepository addressRepo, BuddyInfoRepository buddyRepo){
        this.addressRepo = addressRepo;
        this.buddyRepo = buddyRepo;
    }


    @GetMapping("/getAddressbook")
    public String getAllAddressBooks(Model model){
        List<AddressBook> addressBooks = new ArrayList<>();
        addressRepo.findAll().forEach(addressBooks::add);
        model.addAttribute("Addresses", addressBooks);
        return "addresses";

    }


    @GetMapping("/getOneAddressbook")
    public String getOneAddressbook(@RequestParam Integer id, Model model){
        Optional<AddressBook> a1 = addressRepo.findById(id);
        System.out.println("printbuddylist: " + a1.get().printBuddyList());
        System.out.println("tosting: " + a1.get().getBuddyList().toString());
        System.out.println("get: " + a1.get());


        if (a1.isEmpty())
            return null;
        model.addAttribute("BuddyList", a1.get().getBuddyList().toString());
        return "addresses";
    }

    @GetMapping("/getBuddies")
    public String getBuddies(Model model){
        model.addAttribute("Addresses", buddyRepo.findAll());
        return "addresses";
    }

    @PostMapping("/addAddressbook")
    public String addAddressBook(Model model){
        AddressBook a = new AddressBook();
        addressRepo.save(a);
        model.addAttribute("AddressId", a.getId());
        return "addresses";
    }

    @PostMapping("/addBuddy")
    public String addBuddy(@RequestParam Integer id, @RequestParam String name, @RequestParam String phone, Model model){
        Optional<AddressBook> a = addressRepo.findById(id);
        if (a.isEmpty())
            return null;
        AddressBook a1 = a.get();
        a1.addBuddy(new BuddyInfo(name, phone));
        addressRepo.save(a1);
        return "addresses";
    }

    @DeleteMapping("/delBuddy")
    public String removeBuddy(@RequestParam Integer addressId, @RequestParam Integer buddyId){
        Optional<AddressBook> a1 = addressRepo.findById(addressId);
        if (a1.isEmpty())
            return null;
        AddressBook a = a1.get();
        Optional<BuddyInfo> b = buddyRepo.findById(buddyId);
        if (b.isEmpty())
            return null;
        a.removeInfo(b.get());
        addressRepo.save(a);
        return "addresses";
    }

    @DeleteMapping("/delAddressbook")
    public String removeAddress(@RequestParam Integer id){
        Optional<AddressBook> a1 = addressRepo.findById(id);
        if (a1.isEmpty())
            return null;
        addressRepo.deleteById(id);
        return "addresses";
    }




}
