package com.axonivy.connectivity.soap;

import static org.assertj.core.api.Assertions.assertThat;

import javax.xml.ws.BindingProvider;

import org.junit.jupiter.api.Test;

import com.axonivy.connectivity.createorder.client.CreateOrder;
import com.axonivy.connectivity.createorder.client.CreateOrderService;
import com.axonivy.connectivity.createorder.client.Order;
import com.axonivy.connectivity.createorder.client.Task;
import com.axonivy.connectivity.createorder.client.WebServiceProcessTechnicalException;
import com.axonivy.ivy.webtest.engine.EngineUrl;

public class IntegrationTestSoapCreateOrderService {
  @Test
  public void createOrder_lowerThan10() throws WebServiceProcessTechnicalException {
    Order order = createOrder(1);
    Task task = callCreateOrderService(order);
    assertTask(task, "Team");
  }

  @Test
  public void createOrder_greaterThan10() throws WebServiceProcessTechnicalException {
    Order order = createOrder(100);
    Task task = callCreateOrderService(order);
    assertTask(task, "Boss");
  }

  private static Order createOrder(int amount) {
    Order order = new Order();
    order.setAmount(amount);
    order.setProduct("Bike");
    order.setUnitPrice(859.00);
    order.setRequester("Hans");
    return order;
  }

  private static Task callCreateOrderService(Order order) throws WebServiceProcessTechnicalException {
    CreateOrderService service = new CreateOrderService();
    CreateOrder port = service.getCreateOrderPort();
    routeToCurrentEngine(port);
    return port.call(order);
  }

  private void assertTask(Task task, String activator) {
    assertThat(task).isNotNull();
    assertThat(task.getId()).isGreaterThan(0);
    assertThat(task.getActivator()).isEqualTo(activator);
  }

  private static void routeToCurrentEngine(CreateOrder port) {
    String url = EngineUrl.createWebServiceUrl("/connectivity-demos/162492A1649E72DF");
    ((BindingProvider) port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url);
  }

}
