package ai.medcad.moneta.interview_task.service;

import ai.medcad.moneta.interview_task.model.TicketDTO;

public class InMemoryTicketStoreService implements TicketStoreService{

    @Override
    public void addNewTicket(TicketDTO ticket) {

    }

    @Override
    public TicketDTO getFirstTicketInQueue() {
        return null;
    }

    @Override
    public void removeFirstInQueueTicket() {

    }
}
