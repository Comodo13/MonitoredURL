package dmitri.korunovacni.Applifting.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="monitor_result")
public class MonitoredResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private Long id;
    private LocalDateTime dateOfCheck;
    private int statusCode;
    private String returnedPayload;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "endpoint_id")
    @Column(name = "endpoint_id")
    private Long endpointId;
}