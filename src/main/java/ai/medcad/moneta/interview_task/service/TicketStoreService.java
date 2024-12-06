package ai.medcad.moneta.interview_task.service;

import ai.medcad.moneta.interview_task.model.TicketDTO;


public interface TicketStoreService {
    public void addNewTicket(TicketDTO ticket);
    public TicketDTO getActualTicket();
    public int removeFirstTicket();
    public TicketDTO createTicket();
    public void emptyStore();
}
