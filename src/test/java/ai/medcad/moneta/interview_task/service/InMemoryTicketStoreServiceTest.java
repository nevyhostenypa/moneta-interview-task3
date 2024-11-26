package ai.medcad.moneta.interview_task.service;

import ai.medcad.moneta.interview_task.model.TicketDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

        TicketDTO testTicket = ticketService.getFirstTicketInQueue();
        assertEquals(firstTicket, testTicket);

        //Some methods calls
        TicketDTO newTicket = ticketService.createTicket();
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

        System.out.println(new1);
        System.out.println(new2);
        System.out.println(new3);

    }

    @Test
    public void testRemoveTicket() {
        TicketDTO new1 = ticketService.createTicket();
        TicketDTO new2 = ticketService.createTicket();
        TicketDTO new3 = ticketService.createTicket();
        TicketDTO new4 = ticketService.createTicket();

        int newSize = ticketService.removeFirstInQueueTicket();

        assertEquals(3, newSize);
        assertEquals(0, new2.getQueueOrder());
        assertEquals(1, new3.getQueueOrder());
        assertEquals(2, new4.getQueueOrder());


        TicketDTO first = ticketService.getFirstTicketInQueue();
        assertEquals(new2, first);
    }

    public void testRemoveTicketToZero() {
        TicketDTO new1 = ticketService.createTicket();
        TicketDTO new2 = ticketService.createTicket();
        TicketDTO new3 = ticketService.createTicket();
        TicketDTO new4 = ticketService.createTicket();

        int newSize1 = ticketService.removeFirstInQueueTicket();
        TicketDTO actualTicket = ticketService.getFirstTicketInQueue();
        assertEquals(new2, actualTicket);
        assertEquals(3 ,newSize1);

        int newSize2 = ticketService.removeFirstInQueueTicket();
        int newSize3 = ticketService.removeFirstInQueueTicket();
        int newSize4 = ticketService.removeFirstInQueueTicket();


        assertEquals(2 ,newSize2);
        assertEquals(1 ,newSize3);
        assertEquals(0 ,newSize4);



    }


}
