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

import java.util.Objects;

@Getter
@Setter
@DynamoDBTable(tableName = "Direct-Message-Messages")
public class Message {

  @Setter(AccessLevel.NONE)
  @Getter(AccessLevel.NONE)
  @Id
  private MessagePK messagePK;

  @DynamoDBAttribute
  @DynamoDBIndexRangeKey(globalSecondaryIndexName = "FromGSI")
  private String from;

  @DynamoDBAttribute
  private String status;


  public MessagePK instantiateMessagePk() {
    return Objects.isNull(messagePK) ? new MessagePK() : messagePK;
  }

  @DynamoDBIndexHashKey(globalSecondaryIndexName = "FromGSI")
  @DynamoDBHashKey(attributeName = "to")
  public String getTo() {
    return messagePK != null ? messagePK.getTo() : null;
  }

  public void setTo(String to) {
    if (messagePK == null) {
      messagePK = new MessagePK();
    }
    messagePK.setTo(to);
  }

  @DynamoDBRangeKey(attributeName = "sendDate")
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
