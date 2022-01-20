package com.app.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Class to configure aws servies
 */
@Configuration
public class DynamoDBConfiguration {

    @Value("${aws.accessKey}")
    private String accessKey;

    @Value("${aws.secretKey}")
    private String secretKey;

    Logger logger = LoggerFactory.getLogger(DynamoDBConfiguration.class);

    /**
     *
     * @return Dynamo Db object with required connection
     */
    @Bean
    public DynamoDBMapper dynamoDBMapper() {
        logger.info("DynamoDBConfiguration.dynamoDBMapper()");

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .withRegion(Regions.US_EAST_1)
                .build();

        logger.info("DynamoDBConfiguration:{} ",client);

        return new DynamoDBMapper(client, DynamoDBMapperConfig.DEFAULT);
    }

}