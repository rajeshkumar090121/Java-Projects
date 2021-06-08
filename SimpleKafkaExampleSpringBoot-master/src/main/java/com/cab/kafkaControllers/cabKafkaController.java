package com.cab.kafkaControllers;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cab.kafkaConsumer.CabKafkaConsumer;
import com.cab.kafkaProducer.CabKafkaProducerApplication;
import com.cab.pojo.CabDetails;
import com.google.gson.Gson;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/api/auth")
public class cabKafkaController {
	

@Value("${kafka.topic.thetechcheck}")
private String theTechCheckTopicName;

@Value("${kafka.bootstrap.servers}")
private String kafkaBootstrapServers;

@Value("${zookeeper.groupId}")
private String zookeeperGroupId;

@Value("${zookeeper.host}")
String zookeeperHost;
private static final Logger logger = Logger.getLogger(cabKafkaController.class);
	@PostMapping("/CabDetailsProduce")
    @ResponseBody
    public String sendNotification(@RequestBody CabDetails cabDetails) {
    	   Properties producerProperties = new Properties();
           producerProperties.put("bootstrap.servers", kafkaBootstrapServers);
           producerProperties.put("acks", "all");
           producerProperties.put("retries", 0);
           producerProperties.put("batch.size", 16384);
           producerProperties.put("linger.ms", 1);
           producerProperties.put("buffer.memory", 33554432);
           producerProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
           producerProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

           /*
           Creating a Kafka Producer object with the configuration above.
            */
           KafkaProducer<String, String> producer = new KafkaProducer<>(producerProperties);

           /*
           The sendTestMessagesToKafka method will generate some random test messages
           and send them to Kafka.
            */
           sendTestMessagesToKafka(producer,cabDetails);

           /*
           Now that we've produced some test messages, let's see how to consume them using a Kafka consumer object.
            */

           /*
            * Defining Kafka consumer properties.
            */
           Properties consumerProperties = new Properties();
           consumerProperties.put("bootstrap.servers", kafkaBootstrapServers);
           consumerProperties.put("group.id", zookeeperGroupId);
           consumerProperties.put("zookeeper.session.timeout.ms", "6000");
           consumerProperties.put("zookeeper.sync.time.ms","2000");
           consumerProperties.put("auto.commit.enable", "false");
           consumerProperties.put("auto.commit.interval.ms", "1000");
           consumerProperties.put("consumer.timeout.ms", "-1");
           consumerProperties.put("max.poll.records", "1");
           consumerProperties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
           consumerProperties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

           /*
            * Creating a thread to listen to the kafka topic
            */
           Thread kafkaConsumerThread = new Thread(() -> {
               logger.info("Starting Kafka consumer thread.");

               CabKafkaConsumer simpleKafkaConsumer = new CabKafkaConsumer(
                       theTechCheckTopicName,
                       consumerProperties
               );

               simpleKafkaConsumer.runSingleWorker();
           });

           /*
            * Starting the first thread.
            */
           kafkaConsumerThread.start();
           return "success";
    }

  
    /**
     * Function to send some test messages to Kafka.
     * We'll get the Kafka producer object as a parameter to this function.
     * We'll generate some test messages, both simple strings and JSON objects, in a couple of
     * loops inside the function. We'll send these test messages to the topic in Kafka.
     *
     * @param producer The Kafka producer we created in the run() method earlier.
     */
    private void sendTestMessagesToKafka(KafkaProducer<String, String> producer, CabDetails cabDetails) {
        /*
        Creating a loop which iterates 10 times, from 0 to 9, and sending a
        simple message to Kafka.
         */
       

        /*
        Creating a loop which iterates 10 times, from 0 to 9, and creates an instance of JSONObject
        in each iteration. We'll use this simple JSON object to illustrate how we can send a JSON
        object as a message in Kafka.
         */
        

            /*
            We'll create a JSON object which will have a bunch of fields, and another JSON object,
            which will be nested inside the first JSON object. This is just to demonstrate how
            complex objects could be serialized and sent to topics in Kafka.
            We'll now serialize the JSON object we created above, and send it to the same topic in Kafka,
            using the same function we used earlier.
            You can use any JSON library for this, just make sure it serializes your objects properly.
            A popular alternative to the one I've used is Gson.
             */
            sendKafkaMessage(new Gson().toJson(cabDetails), producer, theTechCheckTopicName);
        }
    

    /**
     * Function to send a message to Kafka
     * @param payload The String message that we wish to send to the Kafka topic
     * @param producer The KafkaProducer object
     * @param topic The topic to which we want to send the message
     */
    private static void sendKafkaMessage(String payload,
             KafkaProducer<String, String> producer,
             String topic)
    {
        logger.info("Sending Kafka message: " + payload);
        producer.send(new ProducerRecord<>(topic, payload));
    }
}
