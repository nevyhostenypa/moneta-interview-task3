package ai.medcad.moneta.interview_task.service;

import ai.medcad.moneta.interview_task.model.TicketDTO;
import org.springframework.stereotype.Service;

import java.util.TreeSet;

@Service
public class InMemoryTicketStoreService implements TicketStoreService{

    private TreeSet<TicketDTO> tickets = new TreeSet<>();

    @Override
    public void addNewTicket(TicketDTO ticket) {
        tickets.add(ticket);
    }

    @Override
    public TicketDTO getFirstTicketInQueue() {
        return tickets.getFirst();
    }

    @Override
    public void removeFirstInQueueTicket() {

    }

    //TODO
    @Override
    public TicketDTO createTicket() {
        return new TicketDTO();
    }

    public void test() {

    }
}
