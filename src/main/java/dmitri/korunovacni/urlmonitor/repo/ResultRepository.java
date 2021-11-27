package dmitri.korunovacni.urlmonitor.repo;

import dmitri.korunovacni.urlmonitor.model.MonitoredResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends JpaRepository<MonitoredResult, Long> {
}
