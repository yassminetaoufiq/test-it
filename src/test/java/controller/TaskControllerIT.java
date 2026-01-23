package com.example.testit.controller;

import com.example.testit.adapter.user.CurrentUserService;
import com.example.testit.service.TaskService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @MockBean
    private CurrentUserService currentUserService;

    @Test
    void itShouldCreateTask() throws Exception {

        Mockito.when(currentUserService.getCurrentUserId())
                .thenReturn(Optional.of(1L));

        mockMvc.perform(MockMvcRequestBuilders.post("/tasks").content("""
                {
                    "title":"yassmine",
                    "description":"abc",
                    "userId":2
                }
                """).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

        Mockito.verify(taskService)
                .createTask("yassmine", "abc", 1L, 2L);
    }
}
