package com.test.dynamoindextest.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import lombok.Setter;

import java.io.Serializable;

@Setter
public class MessagePK implements Serializable {

  private static final long serialVersionUID = -5838201752287639091L;

  private String to;

  private Long sendDate;

  @DynamoDBHashKey
  public String getTo() {
    return to;
  }

  @DynamoDBRangeKey
  public Long getSendDate() {
    return sendDate;
  }
}
