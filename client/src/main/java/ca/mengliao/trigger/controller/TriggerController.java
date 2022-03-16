package ca.mengliao.trigger.controller;

import ca.mengliao.trigger.model.TriggerRequest;
import ca.mengliao.trigger.model.TriggerResponse;
import ca.mengliao.trigger.service.RemoteRequestService;
import ca.mengliao.util.model.http.request.ApiCallRequest;
import ca.mengliao.util.model.http.response.ApiCallResponse;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TriggerController {

  private final RemoteRequestService remoteRequestService;

  public TriggerController(RemoteRequestService remoteRequestService) {
    this.remoteRequestService = remoteRequestService;
  }

  @PostMapping(value = {"/trigger"})
  public ResponseEntity<TriggerResponse> trigger(@Valid @RequestBody TriggerRequest triggerRequest) {
    ApiCallResponse response = remoteRequestService.callApi(new ApiCallRequest(triggerRequest.getParameter()));
    return ResponseEntity.status(HttpStatus.OK).body(new TriggerResponse(response.getMessage()));
  }
}
