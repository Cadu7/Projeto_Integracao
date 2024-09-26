package com.quarkus.route;

import org.apache.camel.builder.RouteBuilder;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RouteKafka extends RouteBuilder {


    @Override
    public void configure() throws Exception {

        from("timer:source?period=1").
                setBody(simple("{\"nome\": \"cadu\",\"password\":\"12345\"}")).
//		log("\tMessage\n").
        to("kafka:teste?brokers=localhost:9092");
    }

}
