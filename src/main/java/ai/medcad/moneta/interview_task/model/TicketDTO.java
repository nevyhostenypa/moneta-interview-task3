package ai.medcad.moneta.interview_task.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketDTO {

    private Long sequenceNumber;
    private LocalDateTime dateTime;
    private Integer queueOrder;
}