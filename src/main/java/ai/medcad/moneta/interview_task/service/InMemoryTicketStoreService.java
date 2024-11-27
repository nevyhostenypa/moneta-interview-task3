package ai.medcad.moneta.interview_task.service;

import ai.medcad.moneta.interview_task.exception.QueueEmptyException;
import ai.medcad.moneta.interview_task.model.TicketDTO;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.TreeSet;

@Service
@Slf4j
public class InMemoryTicketStoreService implements TicketStoreService{

    private static final Long START_SEQUENCE_FROM = 1450L;

    private SequenceGenerator sequenceGenerator = new SequenceGenerator(START_SEQUENCE_FROM);

    private TreeSet<TicketDTO> tickets = new TreeSet<>();

    @Override
    public void addNewTicket(TicketDTO ticket) {
        log.info("Adding ticket object method");
        tickets.add(ticket);
    }

    @Override
    public TicketDTO getActualTicket() {
        if(tickets.size() > 0) {
            return tickets.getFirst();
        }
        else {
            log.error("Ticket queue is empty could no retrieve !!");
            throw new QueueEmptyException("Queue is empty !!!");
        }
    }


    @Override
    @Synchronized
    public int removeFirstTicket() {
        tickets.pollFirst();
        reindexQueueOrder();
        log.info("New size of queue is : {} items", tickets.size());
        return tickets.size();
    }

    private void reindexQueueOrder() {
        log.info("Reindexing queue order");
        tickets.stream().forEach(ticket-> ticket.setOrder(ticket.getOrder()-1));
    }

    //TODO Decide to call interface method addTicket or directly adding to set.
    @Override
    @Synchronized
    public TicketDTO createTicket() {
        int queueOrder = tickets.size();
        Long sequenceNumber = sequenceGenerator.get();

        TicketDTO newTicket = TicketDTO.builder().sequenceNumber(sequenceNumber)
                .order(queueOrder).creationDateTime(LocalDateTime.now()).build();

        tickets.add(newTicket);
        log.info("Ticket : {} added to queue", newTicket);

        return newTicket;
    }

    public void emptyStore() {
        log.warn("Deleting all items in queue");
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
