package rest.bpm;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.engine.client.ExecutionResult;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.model.value.SignalCode;
import ch.ivyteam.ivy.workflow.query.SignalEventQuery;
import ch.ivyteam.ivy.workflow.signal.ISignalEvent;

@IvyProcessTest(enableWebServer = true)
public class TestAsyncInvocation {

  @Test
  void callBatchAsync(BpmClient bpmClient) throws InterruptedException {
    ExecutionResult result = bpmClient.start().process("rest/asyncInvocation/callBatchAsync.ivp").execute();
    assertThat(result).isNotNull();

    SignalEventQuery query = Ivy.wf().signals().history().createSignalEventQuery();

    Supplier<List<String>> signals = () -> new ArrayList<>(query.executor().results())
        .stream().map(ISignalEvent::getSignalCode).map(SignalCode::toString)
        .toList();
    Awaitility.await()
        .atLeast(3, TimeUnit.SECONDS) // default delay: on process being called
        .atMost(10, TimeUnit.SECONDS)
        .until(() -> signals.get(),
            codes -> !codes.isEmpty() && Objects.equals(codes.get(0), "rest:batch:result:ok"));
  }

}
