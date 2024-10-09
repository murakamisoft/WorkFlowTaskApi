package com.murakamisoft.workflowtaskapi.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.murakamisoft.workflowtaskapi.model.Task;
import io.micrometer.common.util.StringUtils;

@Component
public class KafkaConsumer {

  @Autowired
  private RestTemplate restTemplate; // RestTemplateをインジェクション

  @KafkaListener(topics = "task-events", groupId = "task-api-group")
  public void listen(String message) {
    System.out.println("Received message: " + message);

    // StringUtilsを使ってメッセージが空またはnullかどうかをチェック
    if (StringUtils.isBlank(message)) {
      System.out.println("Received empty message, ignoring it.");
      return; // 早期リターン
    }

    callTaskApiEndpoint(message); // 受信したメッセージに基づいてエンドポイントを呼び出す
  }

  private void callTaskApiEndpoint(String message) {
    String apiUrl = "http://localhost:8080/tasks";
    // JSONをTaskオブジェクトに変換
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      Task task = objectMapper.readValue(message, Task.class);
      restTemplate.postForEntity(apiUrl, task, String.class);
    } catch (JsonProcessingException e) {
      e.printStackTrace(); // エラーログを出力
    }

  }
}
