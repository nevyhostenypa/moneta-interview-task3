package ai.medcad.moneta.interview_task.service;

import ai.medcad.moneta.interview_task.model.TicketDTO;
import org.springframework.stereotype.Service;

public interface TicketStoreService {
    public void addNewTicket(TicketDTO ticket);
    public TicketDTO getFirstTicketInQueue();
    public void removeFirstInQueueTicket();
    public TicketDTO createTicket();

}
