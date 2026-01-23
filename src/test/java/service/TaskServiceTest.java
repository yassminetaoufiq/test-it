package com.example.testit.service;

import com.example.testit.adapter.mail.MailService;
import com.example.testit.model.Task;
import com.example.testit.model.User;
import com.example.testit.repository.TaskRepository;
import com.example.testit.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

class TaskServiceTest {
    TaskService taskservice;
    MailService mailservice;
    TaskRepository taskRepository;
    UserRepository userRepository;

    @BeforeEach
    void setup() {
        taskRepository = Mockito.mock(TaskRepository.class);
        userRepository = Mockito.mock(UserRepository.class);
        mailservice = Mockito.mock(MailService.class);

        taskservice = new TaskService(taskRepository, userRepository, mailservice);
    }

    @Test
    public void test1() {
        Mockito.when(userRepository.findById(1L)) .thenReturn(Optional.of(new User("Requester")));
        Mockito.when(userRepository.findById(2L)) .thenReturn(Optional.of(new User("Assigned")));
        taskservice.createTask("yassmine", "abc", 1L, 2L);
        Mockito.verify(taskRepository).save(Mockito.any(Task.class));
    }
}
