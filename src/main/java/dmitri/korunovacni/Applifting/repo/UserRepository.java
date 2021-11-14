package dmitri.korunovacni.Applifting.repo;


import dmitri.korunovacni.Applifting.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //Optional<User> findByEmail(String email);
}
