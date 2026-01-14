package com.axonivy.connectivity.rest.aynch;

import javax.ws.rs.client.InvocationCallback;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.process.model.value.SignalCode;
import ch.ivyteam.ivy.request.async.IvyAsyncExecutor;

/**
 * Fires a signal to the BPM Engine when the result of an asynchronous REST
 * request is available.
 *
 * <p>
 * Async callback methods use the {@link IvyAsyncExecutor} to avoid
 * environment not available exceptions.
 * </p>
 *
 * <p>
 * See <b>Processes/rest/batchService/async.ivp</b> for a demo
 * </p>
 *
 * @since 7.3.0
 */
public class SignalInvoker implements InvocationCallback<String> {
  private final SignalCode successSignal;
  private final SignalCode errorSignal;
  private final IvyAsyncExecutor asyncExecutor;

  public SignalInvoker(String successSignal) {
    this(successSignal, "ivy:error:rest:client:async");
  }

  public SignalInvoker(String successSignal, String errorSignal) {
    this.successSignal = SignalCode.of(successSignal);
    this.errorSignal = SignalCode.of(errorSignal);
    this.asyncExecutor = IvyAsyncExecutor.create();
  }

  @Override
  public void completed(String response) {
    asyncExecutor.run(() -> Ivy.wf().signals().create().data(response).send(successSignal));
  }

  @Override
  public void failed(Throwable throwable) {
    asyncExecutor.run(() -> Ivy.wf().signals().create().data(throwable).send(errorSignal));
  }

}
