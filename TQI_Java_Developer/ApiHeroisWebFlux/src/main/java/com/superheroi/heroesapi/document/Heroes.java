package com.superheroi.heroesapi.document;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import org.springframework.data.annotation.Id;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@DynamoDBTable(tableName = "Heroes_Table")
public class Heroes {
    @Id
    @DynamoDBHashKey(attributeName = "id")
    private String id;
    @DynamoDBHashKey(attributeName = "name")
    private String name;
    @DynamoDBHashKey(attributeName = "universe")
    private String universe;
    @DynamoDBHashKey(attributeName = "films")
    private int films;

    public Heroes(String id,String name, String universe, int films){
        this.id=id;
        this.name=name;
        this.universe=universe;
        this.films=films;
    }




}
