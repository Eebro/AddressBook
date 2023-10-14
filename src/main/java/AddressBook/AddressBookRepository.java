package AddressBook;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressBookRepository extends CrudRepository<AddressBook, Integer> {

}
