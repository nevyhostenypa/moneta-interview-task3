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

    private TicketDTO firstTicket = TicketDTO.builder().queueOrder(0).sequenceNumber(1400L).dateTime(LocalDateTime.now()).build();
    private TicketDTO secondTicket = TicketDTO.builder().queueOrder(1).sequenceNumber(1450L).dateTime(LocalDateTime.now()).build();

    @BeforeEach
    public void setup() {
        ticketService.addNewTicket(firstTicket);
        ticketService.addNewTicket(secondTicket);

    }

    @Test
    public void testPollFirstTicket() {
        //given(beerService.getBeerById(any(UUID.class))).willReturn(validBeer);
        TicketDTO testTicket = ticketService.getFirstTicketInQueue();
        assertEquals(testTicket, firstTicket);

        TicketDTO testTicket2 = ticketService.getFirstTicketInQueue();
        assertEquals(testTicket2, firstTicket);

    }

}
