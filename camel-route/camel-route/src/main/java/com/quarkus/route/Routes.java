package com.quarkus.route;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

import io.vertx.core.http.HttpMethod;

@ApplicationScoped
public class Routes extends RouteBuilder {

	@Override
	public void configure() throws Exception {
 
		from("kafka:teste?brokers=localhost:9092")
		.routeId("ReciverFromKafka")
//			.log("\t\tKAFKA\n\tHeaders=> ${headers}\n")
//			.log("Body=> ${body}")
		.multicast().
		to("activemq:activeService")
		.to("direct:apiService");

		from("direct:activeService").
		routeId("SenderToActiveMQ").
		to("activemq:HandlerUsers");
		

		from("direct:apiService")
		.routeId("SenderToHttpRequest")
			.setHeader(Exchange.HTTP_METHOD, constant(HttpMethod.POST))
			.setHeader(Exchange.CONTENT_TYPE, constant("application/json")) 
			//.log(numero.toString())
			//.log("\n\n${headers}")
			//.log("${body}\n\n")
		.to("http://localhost:8082/cliente");
		
		
		
		
	}
	

}
