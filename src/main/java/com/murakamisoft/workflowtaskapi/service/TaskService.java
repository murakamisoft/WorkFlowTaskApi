package com.murakamisoft.workflowtaskapi.service;

import com.murakamisoft.workflowtaskapi.mapper.TaskMapper;
import com.murakamisoft.workflowtaskapi.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // サービスクラスであることを示す
public class TaskService {

  @Autowired
  private TaskMapper taskMapper; // タスクマッパーのインジェクション

  // すべてのタスクを取得するメソッド
  public List<Task> getAllTasks() {
    return taskMapper.findAllTasks();
  }

  public Task createTask(Task task) {
    taskMapper.insertTask(task); // 新たにタスクをDBに追加するメソッドを呼び出す
    return task; // 追加されたタスクを返す
  }

  // タスクを削除するメソッド
  public void deleteTask(Long id) {
    taskMapper.deleteTask(id); // マッパーを使用してDBからタスクを削除
  }

}
