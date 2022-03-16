package ca.mengliao.trigger.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ca.mengliao.util.model.http.request.ApiCallRequest;
import ca.mengliao.util.model.http.response.ApiCallResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.NoHttpResponseException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class RemoteRequestService {

  private final RestTemplate restTemplate;
  private final ObjectMapper objectMapper;

  public RemoteRequestService(RestTemplate restTemplate,
                            ObjectMapper objectMapper) {
    this.restTemplate = restTemplate;
    this.objectMapper = objectMapper;
  }

  public ApiCallResponse callApi(ApiCallRequest apiCallRequest) {
    ApiCallResponse result = null;
    for (int i = 0; i < 200; i++){
      log.info("Current exectuion: {}", i);
      ResponseEntity<String> response = null;
      try {
        response = restTemplate.postForEntity("http://localhost:8081/api", apiCallRequest, String.class);
      } catch (ResourceAccessException e) {
        if (e.getCause() instanceof NoHttpResponseException) {
          e.printStackTrace();
        }
        log.error("Timedout on execution: {} with cause {} and error message {}", i, e.getCause().getMessage(), e.getMessage());
      }
      if (response != null) {
        try {
          result = objectMapper.readValue(response.getBody(), ApiCallResponse.class);
        } catch (JsonProcessingException e) {
          e.printStackTrace();
        }
      }
    }
    return result;
  }
}
