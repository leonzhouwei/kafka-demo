package com.example;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class MyKafkaProducer {
	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		Producer<String, String> producer = new KafkaProducer<String, String>(props);
		final int startInclusive = 100 * 8;
		final int endExclusive = startInclusive + 100;
		for (int i = startInclusive; i < endExclusive; i++) {
			producer.send(new ProducerRecord<String, String>(Config.TOPIC, Integer.toString(i), Integer.toString(i)));
		}
		producer.close();
		System.out.println("done ^_^");
	}
}
