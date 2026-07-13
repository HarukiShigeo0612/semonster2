package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {

  @Test
  public void testGreeting() {
    App classUnderTest = new App();

    assertEquals(
        "こんにちは　SEMonster",
        classUnderTest.getGreeting());
  }
}
