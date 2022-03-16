package ca.mengliao.playground;

import org.springframework.beans.factory.annotation.Value;

public abstract class TestBaseClass {
  @Value("${api.name}")
  protected String apiName;

  public abstract void printName();
}
