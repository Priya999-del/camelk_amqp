// camel-k: language=java configmap=amqptest
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

public class ReadWriteAMQP extends RouteBuilder {
  @Override
  public void configure() throws Exception {


      

      // Receive messages from AMQ Broker
      from("amqp:queue:shelveRestockThresholdReachedAddress")
        .routeId("Receive")
        .log("Receieve Message: ${body}")
        .setHeader(Exchange.HTTP_METHOD, constant("POST"))
        .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
        .setHeader("ACCEPT", constant("application/json"))
        .setHeader("Authorization", constant("Basic YWRtaW46cGFzc3dvcmQ="))
        .to("http://rhpam-kieserver.rhpam.svc.cluster.local:8080/services/rest/server/containers/Retail_Demo_1.0.2-SNAPSHOT/processes/Retail_Demo.shelve-restock/instances");
            
      }

}
