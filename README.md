# Dynamo Index Test
Projeto dedicado a testes com índices secundários globais. 

## Stack
- Spring Boot 2.3.4
- Spring Data Lovelace-SR16
- Spring Data DynamoDB 5.1.0
- AWS Java SDK DynamoDB 1.11.881

## Como testar? 

### Adicionar carga 

```cURL
  curl --location --request POST 'http://localhost:9020/dynamo-service/messages/20' 
```

### Consultar por índice de forma paginada

```cURL
curl --location --request GET 'http://localhost:9020/dynamo-service/messages?to=999&from=998&sendDate=1603214173425&page=0&size=10'
```

## Inclusão de tabela via AWS CLI
```javascript
  aws dynamodb create-table \
  --table-name Direct-Message-Messages \
  --attribute-definitions \
      AttributeName=To,AttributeType=S \
      AttributeName=SendDate,AttributeType=N \
  --key-schema \
      AttributeName=To,KeyType=HASH \
      AttributeName=SendDate,KeyType=RANGE \
--provisioned-throughput \
      ReadCapacityUnits=10,WriteCapacityUnits=5 \
--endpoint-url http://localhost:8042 // caso esteja utilizando local especificar o endpoint-url
```
## Inclusão de índice global na tabela 
```javascript
aws dynamodb update-table \
    --table-name Direct-Message-Messages \
    --attribute-definitions AttributeName=CompositionToFrom,AttributeType=S \
    --global-secondary-index-updates '[
      { 
        "Create":{
          "IndexName": "CompositionToFromIndex",
          "KeySchema":[
            {
              "AttributeName": "CompositionToFrom",
              "KeyType":"HASH"
            },
            {
              "AttributeName":"SendDate",
              "KeyType": "RANGE"
            } 
          ], 
          "ProvisionedThroughput": {
            "ReadCapacityUnits": 10, 
            "WriteCapacityUnits": 5 
          },
          "Projection":{
            "ProjectionType":"ALL"
          }
        }
      }
    ]' --endpoint-url  http://localhost:8042 // caso esteja utilizando local especificar o endpoint-url
```