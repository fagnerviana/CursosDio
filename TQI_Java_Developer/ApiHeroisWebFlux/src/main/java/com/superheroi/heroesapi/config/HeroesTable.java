package com.superheroi.heroesapi.config;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.Configuration;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import static com.superheroi.heroesapi.constains.HeroesConstant.REGION_DYNAMO;
import static com.superheroi.heroesapi.constains.HeroesConstant.ENDPOINT_DYNAMO;

import java.util.Arrays;

@Configuration
@EnableDynamoDBRepositories
public class HeroesTable {
    public static void main(String [] args) throws Exception{

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(ENDPOINT_DYNAMO,REGION_DYNAMO))
                .build();

       DynamoDB dynamoDB = new DynamoDB(client);

        String tableName="Heroes_Table";

        try{
            Table table = dynamoDB.createTable(tableName,Arrays.asList(new KeySchemaElement(atributeName:"id",keyType.MASH))),
            Arrays.asList(new KeySchemaElement),
                    Arrays.asList(new KeySchemaElement("id", ScalarAttributeType.S)),
            new ProvisionedThroughput(readCapacityUnits:5L,writeCapacityUnits:5L));
            table.waitForActive();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
