package db.demo.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.engine.client.ExecutionResult;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmElement;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmProcess;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;
import ch.ivyteam.ivy.scripting.objects.CompositeObject;
import ch.ivyteam.ivy.workflow.CaseState;

/**
 * This sample ProcessTest simulates users and systems working through
 * your process flow. Created data and tasks can be easily asserted.
 * 
 * <p>The test can either be run<ul>
 * <li>in the Designer IDE ( <code>right click > run as > JUnit Test </code> )</li>
 * <li>or in a Maven continuous integration build pipeline ( <code>mvn clean verify</code> )</li>
 * </ul></p>
 * 
 * <p>Detailed guidance on writing these kind of tests can be found in our
 * <a href="https://developer.axonivy.com/doc/10.0/concepts/testing/process-testing.html">Process Testing docs</a>
 * </p>
 */
@IvyProcessTest
public class SampleProcessTest{
  
  private static final BpmProcess testee = BpmProcess.path("MyProcess");
  
  @Test
  @Disabled("illustrative code: needs adaption to your environment")
  public void callProcess(BpmClient bpmClient){
    BpmElement startable = testee.elementName("start.ivp");
    ExecutionResult result = bpmClient.start().process(startable).execute();
    CompositeObject data = result.data().last();
    assertThat(data).isNotNull();
  }
  
  @Test
  @Disabled("illustrative code: needs adaption to your environment")
  public void workflow(BpmClient bpmClient)
  {
    BpmElement startable = testee.elementName("start.ivp");
    
    // start as authenticated user
    String myUser = "myUser";
    ExecutionResult result = bpmClient.start().process(startable).as().user(myUser).execute();
    assertThat(result.workflow().activeCase()).isEqualTo(CaseState.RUNNING);
    assertThat(result.workflow().executedTask().activator().name()).isEqualTo(myUser);
    
    // continue after task/switch
    bpmClient.start().anyActiveTask(result).as().role("supervisor").execute();
  }
}
