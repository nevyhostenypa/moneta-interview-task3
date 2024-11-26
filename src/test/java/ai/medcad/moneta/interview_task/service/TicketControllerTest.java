package ai.medcad.moneta.interview_task.service;

import ai.medcad.moneta.interview_task.controller.TicketController;
import ai.medcad.moneta.interview_task.model.TicketDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;


import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TicketController.class)
@ComponentScan(value = { "ai.medcad.moneta" })
public class TicketControllerTest {

    private static final String TICKET_API_URL = "/api/v1/ticket/";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TicketStoreService ticketStoreService;

    @BeforeEach
    public void setup() {
        ticketStoreService.emptyStore();
    }

    @Test
    public void testPost() throws Exception {
        mockMvc.perform(post(TICKET_API_URL)).andExpect(status().isCreated());
    }

    @Test
    public void testGet() throws Exception{
        TicketDTO newTicket = ticketStoreService.createTicket();

        mockMvc.perform(get(TICKET_API_URL)).andExpect((status().isOk()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.sequenceNumber").isNumber())
                .andExpect(jsonPath("$.order").value("0"));
    }

    @Test
    public void testRemove() throws Exception {
        TicketDTO newTicket = ticketStoreService.createTicket();

        mockMvc.perform(delete(TICKET_API_URL)).andExpect(status().isNoContent());
        mockMvc.perform(delete(TICKET_API_URL)).andExpect(status().isNoContent());
        mockMvc.perform(delete(TICKET_API_URL)).andExpect(status().isNoContent());

    }

}
