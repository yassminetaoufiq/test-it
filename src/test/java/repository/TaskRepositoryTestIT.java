package com.example.testit.repository;

import com.example.testit.model.Task;
import com.example.testit.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TaskRepositoryTestIT {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    void testSaveAndFindTask() {
        User user = new User();
        user.setUsername("Chakib");
        user = userRepository.save(user);

        Task task = new Task();
        task.setTitle("Ma tâche");
        task.setDescription("Test intégration");
        task.setAssignedUser(user);

        task = taskRepository.save(task);

        Task savedTask =
                taskRepository.findById(task.getId()).orElseThrow();

        Assertions.assertThat(savedTask.getTitle()).isEqualTo("Ma tâche");
        Assertions.assertThat(savedTask.getDescription()).isEqualTo("Test intégration");
        Assertions.assertThat(savedTask.getAssignedUser().getUsername()).isEqualTo("Chakib");
    }

}