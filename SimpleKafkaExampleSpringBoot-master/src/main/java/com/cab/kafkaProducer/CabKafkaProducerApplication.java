package com.cab.kafkaProducer;

import com.cab.kafkaConsumer.CabKafkaConsumer;
import com.cab.pojo.CabDetails;
import com.google.gson.Gson;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Properties;

@SpringBootApplication
@ComponentScan(basePackages="com.cab.kafkaControllers")
public class CabKafkaProducerApplication {

   

    private static final Logger logger = Logger.getLogger(CabKafkaProducerApplication.class);

    public static void main( String[] args ) {
        SpringApplication.run(CabKafkaProducerApplication.class, args);
    }
    
    
    
}
