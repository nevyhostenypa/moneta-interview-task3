package ai.medcad.moneta.interview_task.controller;

import ai.medcad.moneta.interview_task.exception.QueueEmptyException;
import ai.medcad.moneta.interview_task.model.TicketDTO;
import ai.medcad.moneta.interview_task.service.TicketStoreService;
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
        TicketDTO actualTicket = ticketStoreService.getActualTicket();
        return new ResponseEntity<>(actualTicket, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createTicket() {
        TicketDTO newTicket = ticketStoreService.createTicket();
        return new ResponseEntity(newTicket, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity removeFirstTicket() {
        ticketStoreService.removeFirstTicket();
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler
    public ResponseEntity emptyQueueHandler(QueueEmptyException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    //TODO Documentation of API via restdocs
}
