package ai.medcad.moneta.interview_task.service;

import ai.medcad.moneta.interview_task.model.TicketDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@SpringBootTest
public class InMemoryTicketStoreServiceTest {

    @Autowired
    private TicketStoreService ticketService;

    private TicketDTO firstTicket = TicketDTO.builder().queueOrder(0).sequenceNumber(140L).dateTime(LocalDateTime.now()).build();
    private TicketDTO secondTicket = TicketDTO.builder().queueOrder(1).sequenceNumber(143L).dateTime(LocalDateTime.now()).build();

    @BeforeEach
    public void setup() {
        ticketService.emptyStore();
    }

    @Test
    public void testPollFirstTicket() {
        ticketService.addNewTicket(firstTicket);
        ticketService.addNewTicket(secondTicket);

        //given(beerService.getBeerById(any(UUID.class))).willReturn(validBeer);
        TicketDTO testTicket = ticketService.getFirstTicketInQueue();
        assertEquals(firstTicket, testTicket);

        TicketDTO testTicket2 = ticketService.getFirstTicketInQueue();
        assertEquals(firstTicket, testTicket2);

    }

    @Test
    public void testCreateNewTicket() {
        TicketDTO new1 = ticketService.createTicket();
        TicketDTO new2 = ticketService.createTicket();
        TicketDTO new3 = ticketService.createTicket();

        assertEquals(0, new1.getQueueOrder());
        assertEquals(1, new2.getQueueOrder());
        assertEquals(2, new3.getQueueOrder());

    }

}
