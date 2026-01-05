package ch.ivyteam.rule.engine.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.engine.client.ExecutionResult;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmProcess;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;
import ch.ivyteam.ivy.workflow.CaseState;
import rule.engine.demo.Data;

@IvyProcessTest
class TestDiscountCalculator {
  private static final BpmProcess PROCESS = BpmProcess.name("DiscountCalculator");

  //@Test  
  void xls(BpmClient bpmClient) {
    var element = PROCESS.elementName("testXlsRule");
    ExecutionResult result = bpmClient.start().process(element).execute();
    assertThat(result.workflow().activeCase().getState()).isEqualTo(CaseState.DONE);
    Data data = result.data().last();
    assertThat(data.getMember().getDiscount()).isEqualTo(3);
  }

  @Test
  void drl(BpmClient bpmClient) {
    var element = PROCESS.elementName("testDrlRule");
    ExecutionResult result = bpmClient.start().process(element).execute();
    assertThat(result.workflow().activeCase().getState()).isEqualTo(CaseState.DONE);
    Data data = result.data().last();
    assertThat(data.getMember().getDiscount()).isEqualTo(3);
  }

  @Test
  void dmn(BpmClient bpmClient) {
    var element = PROCESS.elementName("testDmnRule");
    ExecutionResult result = bpmClient.start().process(element).execute();
    assertThat(result.workflow().activeCase().getState()).isEqualTo(CaseState.DONE);
    Data data = result.data().last();
    assertThat(data.getMember().getDiscount()).isEqualTo(new BigDecimal(3));
  }
}
