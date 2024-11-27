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
    private LocalDateTime creationDateTime;
    //TODO Think about using boxed Integer
    private int order;

    @Override
    public int compareTo(TicketDTO o) {
        if(this.order > o.order) {
            return 1;
        }
        else if(this.order < o.order) {
            return -1;
        }
        return 0;
    }
}
