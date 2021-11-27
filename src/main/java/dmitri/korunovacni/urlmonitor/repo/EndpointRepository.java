package dmitri.korunovacni.urlmonitor.repo;

import dmitri.korunovacni.urlmonitor.model.MonitoredEndpoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EndpointRepository extends JpaRepository<MonitoredEndpoint, Long> {
}
