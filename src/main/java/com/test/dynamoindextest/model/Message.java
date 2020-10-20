package com.test.dynamoindextest.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@DynamoDBTable(tableName = "Direct-Message-Messages")
public class Message {

  @Setter(AccessLevel.NONE)
  @Getter(AccessLevel.NONE)
  @Id
  private MessagePK messagePK;

  @DynamoDBAttribute
  private String from;

  @DynamoDBIndexHashKey(globalSecondaryIndexName = "CompositionToFromIndex")
  @DynamoDBAttribute(attributeName = "CompositionToFrom")
  private String compositionToFrom;

  @DynamoDBAttribute
  private String status;

  @DynamoDBHashKey(attributeName = "To")
  public String getTo() {
    return messagePK != null ? messagePK.getTo() : null;
  }

  public void setTo(String to) {
    if (messagePK == null) {
      messagePK = new MessagePK();
    }
    messagePK.setTo(to);
  }

  @DynamoDBIndexRangeKey(globalSecondaryIndexName = "CompositionToFromIndex")
  @DynamoDBRangeKey(attributeName = "SendDate")
  public Long getSendDate() {
    return messagePK != null ? messagePK.getSendDate() : null;
  }

  public void setSendDate(Long sendDate) {
    if (messagePK == null) {
      messagePK = new MessagePK();
    }
    messagePK.setSendDate(sendDate);
  }
}
