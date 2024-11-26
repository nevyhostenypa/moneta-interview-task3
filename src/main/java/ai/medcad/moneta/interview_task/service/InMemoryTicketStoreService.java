package ai.medcad.moneta.interview_task.service;

import ai.medcad.moneta.interview_task.controller.TicketController;
import ai.medcad.moneta.interview_task.model.TicketDTO;
import lombok.Synchronized;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.TreeSet;

@Service
public class InMemoryTicketStoreService implements TicketStoreService{

    private SequenceGenerator sequenceGenerator = new SequenceGenerator(1450L);

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
    @Synchronized
    public TicketDTO createTicket() {
        int queueOrder = tickets.size();
        Long sequenceNumber = sequenceGenerator.get();

        TicketDTO newTicket = TicketDTO.builder().sequenceNumber(sequenceNumber)
                .queueOrder(queueOrder).dateTime(LocalDateTime.now()).build();

        tickets.add(newTicket);

        return newTicket;
    }

    public void emptyStore() {
        tickets.clear();
    }

    private static class SequenceGenerator {
        private Long lastValue;

        public SequenceGenerator(Long initialValue) {
            lastValue = initialValue;
        }

        private Long get() {
            lastValue++;
            return lastValue;
        }
    }


}
