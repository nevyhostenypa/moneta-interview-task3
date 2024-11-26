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
public class TicketDTO implements Comparable<TicketDTO>{

    private Long sequenceNumber;
    private LocalDateTime dateTime;
    private int queueOrder;

    @Override
    public int compareTo(TicketDTO o) {
        if(this.queueOrder > o.queueOrder) {
            return 1;
        }
        else if(this.queueOrder < o.queueOrder) {
            return -1;
        }
        return 0;
    }
}
