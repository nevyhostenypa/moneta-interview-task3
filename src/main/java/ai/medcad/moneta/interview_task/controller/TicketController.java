package ai.medcad.moneta.interview_task.controller;

import ai.medcad.moneta.interview_task.model.TicketDTO;
import ai.medcad.moneta.interview_task.service.TicketStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ticket/")
public class TicketController {

    private final TicketStoreService ticketStoreService;

    public TicketController(TicketStoreService ticketStoreService) {
        this.ticketStoreService = ticketStoreService;
    }

    @GetMapping
    public ResponseEntity<TicketDTO> getFirstTicket() {
        TicketDTO actualTicket = ticketStoreService.getFirstTicketInQueue();
        return new ResponseEntity<>(actualTicket, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createTicket() {
        TicketDTO newTicket = ticketStoreService.createTicket();
        return new ResponseEntity(newTicket, HttpStatus.CREATED);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeFirstTicket() {
        ticketStoreService.removeFirstInQueueTicket();
    }
}
