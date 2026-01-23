package com.example.testit.service;

import com.example.testit.model.Status;
import com.example.testit.model.Task;
import com.example.testit.model.User;
import com.example.testit.repository.TaskRepository;
import com.example.testit.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TaskServiceTestIT {
    @Autowired
    TaskService taskService;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void test1() {

        User requester = new User();
        requester.setUsername("requester");

        User assigned = new User();
        assigned.setUsername("assigned");

        userRepository.save(requester);
        userRepository.save(assigned);

        Task task = new Task("Titre", "Description", assigned);
        task.setRequester(requester);
        task.setStatus(Status.OUVERT);
        taskRepository.save(task);

        Task result = taskService.startTask(task.getId(), assigned.getId());


        Optional<Task> taskBase = taskRepository.findById(task.getId());

        Assertions.assertThat(taskBase).isPresent();
        Assertions.assertThat(taskBase.get().getStatus()).isEqualTo(Status.EN_COURS);
    }
}