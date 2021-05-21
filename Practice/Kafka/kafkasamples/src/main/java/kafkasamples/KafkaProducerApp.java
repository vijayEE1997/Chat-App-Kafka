package kafkasamples;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class KafkaProducerApp {
	
	public static void main(String[] args) {
		
		Properties props = new Properties();
		props.put("bootstrap.servers","localhost:9092,localhost:9093");
		props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
		
		KafkaProducer<String,String> myProducer = new KafkaProducer<String, String>(props);
		try {
			for(int i=0;i<50;i++)
				myProducer.send(new ProducerRecord<String, String>("my-topic",Integer.toString(i),"Dhakad Message : "+Integer.toString(i)));
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally {
			myProducer.close();
		}
		
	}

}
