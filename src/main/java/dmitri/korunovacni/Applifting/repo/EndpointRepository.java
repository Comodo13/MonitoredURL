package dmitri.korunovacni.Applifting.repo;

import dmitri.korunovacni.Applifting.model.MonitoredEndpoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EndpointRepository extends JpaRepository <MonitoredEndpoint, Long> {
}