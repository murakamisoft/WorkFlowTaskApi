package com.murakamisoft.workflowtaskapi.controller;

import com.murakamisoft.workflowtaskapi.model.Task;
import com.murakamisoft.workflowtaskapi.service.TaskService; // TaskServiceをインポート
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {

  @Autowired
  private TaskService taskService; // TaskServiceをインジェクション

  @GetMapping("/tasks")
  public List<Task> getTasks() {
    return taskService.getAllTasks(); // TaskServiceを使用してタスクを取得
  }

  @PostMapping("/tasks")
  public Task createTask(@RequestBody Task task) {
    return taskService.createTask(task);
  }

  @DeleteMapping("/tasks/{id}")
  public void deleteTask(@PathVariable Long id) {
    taskService.deleteTask(id); // TaskServiceを使用してタスクを削除
  }
}
