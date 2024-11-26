package ai.medcad.moneta.interview_task.service;

import ai.medcad.moneta.interview_task.model.TicketDTO;
import lombok.Synchronized;
import org.springframework.stereotype.Component;
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
    @Synchronized
    public int removeFirstInQueueTicket() {
        tickets.pollFirst();
        reindexQueueOrder();
        return tickets.size();
    }

    private void reindexQueueOrder() {
        tickets.stream().forEach(ticket-> ticket.setOrder(ticket.getOrder()-1));
    }

    //TODO
    @Override
    @Synchronized
    public TicketDTO createTicket() {
        int queueOrder = tickets.size();
        Long sequenceNumber = sequenceGenerator.get();

        TicketDTO newTicket = TicketDTO.builder().sequenceNumber(sequenceNumber)
                .order(queueOrder).creationDateTime(LocalDateTime.now()).build();

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
