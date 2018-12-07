package com.itechart.ekar.controller;

import com.itechart.ekar.dto.ClientConfiguration;
import com.itechart.ekar.service.countmanager.ChangeResult;
import com.itechart.ekar.service.countmanager.ChangeResultInterpreter;
import com.itechart.ekar.service.countmanager.CounterManager;
import com.itechart.ekar.service.logging.RequestLoggingService;
import com.itechart.ekar.service.worker.WorkerPool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AdjustController.class,
    secure = false)
public class AdjustControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RequestLoggingService requestLoggingService;

    @MockBean
    private CounterManager counterManager;

    @MockBean
    private ChangeResultInterpreter resultInterpreter;

    @MockBean
    private WorkerPool workerPool;

    @Test
    public void postAdjustClient() throws Exception {
        Integer producer = 23;
        Integer consumer = 45;
        mvc.perform(post("/adjust/client")
            .content(String.format("{\"producer\" : \"%s\", \"consumer\" : \"%s\"}", producer, consumer))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());
        verify(requestLoggingService, times(1)).logConfigurationEvent(new ClientConfiguration(producer, consumer));
    }

    @Test
    public void postAdjustNewCounter() throws Exception {
        Integer newCounter = 6;
        ChangeResult result = mock(ChangeResult.class);
        when(counterManager.setNewValue(newCounter)).thenReturn(result);

        mvc.perform(post("/adjust/counter/{newCounter}", newCounter)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

        verify(requestLoggingService, times(1)).logCounter(newCounter);
        verify(resultInterpreter, times(1)).interpret(any(String.class), eq(result));
    }


    @SpringBootConfiguration()
    public static class config {

        @Bean
        public AdjustController controller() {
            return new AdjustController();
        }


    }
}