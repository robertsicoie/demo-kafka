package com.example.demokafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Date;

@SpringBootApplication
public class DemoKafkaApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(DemoKafkaApplication.class, args);
		if (args.length > 0) {
			if ("-c".equals(args[0])) {
				MessageConsumer consumer = context.getBean(MessageConsumer.class);
			} else if ("-p".equals(args[0])) {
				MessageProducer producer = context.getBean(MessageProducer.class);
				for (int i = 1; i < args.length; i++) {
					producer.send(new Message(new Date().getTime(), args[i], "demo-kafka-app"));
				}
				context.close();
			} else {

				System.out.println("\n\nTo start consumer:\n" +
						"	java -Dserver.port=8081 -jar demo-kafka-0.0.1-SNAPSHOT.jar -c\n" +
						"\n\nTo start producer:\n" +
						"	java -jar demo-kafka-0.0.1-SNAPSHOT.jar -p <MSG1> <MSG2> <MSG3> <...>\n");

				context.close();
			}

		}
	}
}
