package com.quarkus.route;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

import io.vertx.core.http.HttpMethod;

@Path("/setup")
public class Controller {
	
	CamelContext context = new DefaultCamelContext();

	@PUT
	@Consumes(MediaType.TEXT_PLAIN)
	public void changeData(String str) throws Exception {
		context.removeRoute("Schedule");
		String[] numeros = str.split(":");
		System.out.println(numeros[0] + "   " + numeros[1]);
		Numero.HH = Integer.parseInt(numeros[0]);
		Numero.MM = Integer.parseInt(numeros[1]);
		context.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {
				from("cron:tab?schedule=0+"+Numero.MM+"+"+Numero.HH+"+*+*+?").
				routeId("Schedule").
				setHeader(Exchange.HTTP_METHOD, constant(HttpMethod.POST)).
				setHeader(Exchange.CONTENT_TYPE, constant("application/json")). 
				setBody(simple("{\"nome\": \"carlinhos\",\"password\":\"12345\"}")).
				log("${body}").
				to("http://localhost:8082/cliente");	
			}
			
		
		});
		context.start();
	}

	
	@GET
	public void imprima() {
		System.out.println(Numero.HH+":"+Numero.MM);
	}

}
