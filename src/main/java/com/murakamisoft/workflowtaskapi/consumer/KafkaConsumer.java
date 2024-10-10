package com.murakamisoft.workflowtaskapi.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.murakamisoft.workflowtaskapi.model.TaskMessage; // 新しいインポート
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import io.micrometer.common.util.StringUtils;

@Component
public class KafkaConsumer {

  @Autowired
  private RestTemplate restTemplate;

  @KafkaListener(topics = "task-events", groupId = "task-api-group")
  public void listen(String message) {
    System.out.println("Received message: " + message);

    if (StringUtils.isBlank(message)) {
      System.out.println("Received empty message, ignoring it.");
      return;
    }

    callTaskApiEndpoint(message);
  }

  private void callTaskApiEndpoint(String message) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      // メッセージをTaskMessageオブジェクトに変換
      TaskMessage taskMessage = objectMapper.readValue(message, TaskMessage.class);
      String apiUrl = "http://localhost:8080" + taskMessage.getApiEndpoint();

      switch (taskMessage.getHttpMethod()) {
        case "POST":
          restTemplate.postForEntity(apiUrl, taskMessage.getParams(), String.class);
          break;
        case "DELETE":
          restTemplate.delete(apiUrl + "/" + taskMessage.getParams().getTaskId());
          break;
        // 他のHTTPメソッドに対する処理を追加することもできます
      }
    } catch (JsonProcessingException e) {
      e.printStackTrace(); // エラーログを出力
    }
  }
}
