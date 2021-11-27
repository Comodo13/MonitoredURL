package dmitri.korunovacni.urlmonitor.repo;


import dmitri.korunovacni.urlmonitor.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //Optional<User> findByEmail(String email);
}
