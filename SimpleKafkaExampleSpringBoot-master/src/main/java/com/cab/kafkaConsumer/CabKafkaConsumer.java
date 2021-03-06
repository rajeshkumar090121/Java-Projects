package com.cab.kafkaConsumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.cab.kafkaService.persistCabDetailsService;
import com.cab.pojo.CabDetails;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CabKafkaConsumer {
	@Autowired
	persistCabDetailsService persistCabdetailService;

    private static final Logger logger = Logger.getLogger(CabKafkaConsumer.class);

    private KafkaConsumer<String, String> kafkaConsumer;

    public CabKafkaConsumer(String theTechCheckTopicName, Properties consumerProperties) {

        kafkaConsumer = new KafkaConsumer<>(consumerProperties);
        kafkaConsumer.subscribe(Arrays.asList(theTechCheckTopicName));
    }

    /**
     * This function will start a single worker thread per topic.
     * After creating the consumer object, we subscribed to a list of Kafka topics, in the constructor.
     * For this example, the list consists of only one topic. But you can give it a try with multiple topics.
     */
    public void runSingleWorker() {

        /*
         * We will start an infinite while loop, inside which we'll be listening to
         * new messages in each topic that we've subscribed to.
         */
        while(true) {

            ConsumerRecords<String, String> records = kafkaConsumer.poll(100);

            for (ConsumerRecord<String, String> record : records) {

                /*
                Whenever there's a new message in the Kafka topic, we'll get the message in this loop, as
                the record object.
                 */

                /*
                Getting the message as a string from the record object.
                 */
                String message = record.value();
                try {
					JSONObject receivedJsonObject = new JSONObject(message);
                    CabDetails cabDetails = new Gson().fromJson(receivedJsonObject.toString(), CabDetails.class);
                    try {
                    persistCabdetailService.persistCabDetails(cabDetails);
                    }
                    catch(Exception e) {
                    	logger.error(e.getMessage());
                    }
                    /*
                    To make sure we successfully deserialized the message to a JSON object, we'll
                    log the index of JSON object.
                     */
                } catch (JSONException e) {
                    logger.error(e.getMessage());
                }

                /*
                Once we finish processing a Kafka message, we have to commit the offset so that
                we don't end up consuming the same message endlessly. By default, the consumer object takes
                care of this. But to demonstrate how it can be done, we have turned this default behaviour off,
                instead, we're going to manually commit the offsets.
                The code for this is below. It's pretty much self explanatory.
                 */
                {
                    Map<TopicPartition, OffsetAndMetadata> commitMessage = new HashMap<>();

                    commitMessage.put(new TopicPartition(record.topic(), record.partition()),
                            new OffsetAndMetadata(record.offset() + 1));

                    kafkaConsumer.commitSync(commitMessage);

                    logger.info("Offset committed to Kafka.");
                }
            }
        }
    }
}
