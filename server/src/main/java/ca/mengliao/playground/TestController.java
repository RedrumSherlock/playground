package ca.mengliao.playground;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

  @Autowired
  TestChildClass childClass;

  @GetMapping(value = "/test")
  public ResponseEntity<String> test() {
    childClass.printName();
    return ResponseEntity.status(HttpStatus.OK).body("");
  }
}
