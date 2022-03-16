package ca.mengliao.playground.controller;

import ca.mengliao.util.model.http.request.ApiCallRequest;
import ca.mengliao.util.model.http.response.ApiCallResponse;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class ApiServerController {

  @PostMapping(value = "/api")
  public ResponseEntity<ApiCallResponse> trigger(@Valid @RequestBody ApiCallRequest apiCallRequest) {
    Random rand = new Random();
    if (rand.nextInt(100) < 3) {
      try {
        Thread.sleep(15 * 1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    return ResponseEntity.status(HttpStatus.OK)
        .body(new ApiCallResponse(apiCallRequest.getParameter() + "-server-response"));
  }
}
