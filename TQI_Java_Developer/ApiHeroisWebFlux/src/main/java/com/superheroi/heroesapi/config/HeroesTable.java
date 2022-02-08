package com.superheroi.heroesapi.config;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.*;
import lombok.extern.slf4j.Slf4j;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.Configuration;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;

import static com.superheroi.heroesapi.constains.HeroesConstant.REGION_DYNAMO;
import static com.superheroi.heroesapi.constains.HeroesConstant.ENDPOINT_DYNAMO;

import java.util.Arrays;

public class HeroesTable{
    public static void main(String [] args) throws Exception{

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(ENDPOINT_DYNAMO,REGION_DYNAMO))
                .build();

        DynamoDB dynamoDB = new DynamoDB(client);

        String tableName="Heroes_Table_Demo";

        try{
            System.out.println("Criando table Aguarde...");
        Table table = dynamoDB.createTable(tableName,Arrays.asList(new KeySchemaElement("id", KeyType.HASH)),
        Arrays.asList(new AttributeDefinition("id",ScalarAttributeType.S)),
                new ProvisionedThroughput());
        table.waitForActive();
        System.out.println("Successo"+ table.getDescription().getTableStatus());
        }

        catch (Exception e){
            System.err.println("Não foi possivel criar a tabela");
            System.err.println(e.getMessage());
        }
    }

}
