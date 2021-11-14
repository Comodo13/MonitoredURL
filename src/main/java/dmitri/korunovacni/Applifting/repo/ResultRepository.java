package dmitri.korunovacni.Applifting.repo;

import dmitri.korunovacni.Applifting.model.MonitoredResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends JpaRepository<MonitoredResult, Long> {
}
