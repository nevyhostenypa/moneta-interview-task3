package ai.medcad.moneta.interview_task.service;

import ai.medcad.moneta.interview_task.model.TicketDTO;


public interface TicketStoreService {
    public void addNewTicket(TicketDTO ticket);
    public TicketDTO getFirstTicketInQueue();
    public int removeFirstInQueueTicket();
    public TicketDTO createTicket();
    public void emptyStore();
}
