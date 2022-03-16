package ca.mengliao.playground;

import org.springframework.stereotype.Service;

@Service
public class TestChildClass extends TestBaseClass {
  @Override
  public void printName() {
    System.out.println(apiName);
  }
}
