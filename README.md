# swagger-mock2easy
a simple importer for import swagger doc to mock2easy


## quick-start
```java
public class QuickStart {

   public static void main(String[] args) {
        SourceProvider<JSONObject> provider = SwaggerProvider.builder()
                .url("http://127.0.0.1:8393/v2/api-docs")
                .build();
        DocSender<Mock2easyRequestEntity> sender = Mock2easySender.builder()
                .timeout(1000 * 60)
                .urls(new String[]{"http://192.168.47.180:8001", "http://192.168.47.187:8001"})
                .build();
        DocTransformer<JSONObject, Mock2easyRequestEntity> transformer = SwaggerToMock2easyTransformer.DEFAULT;
        sender.send(transformer.transform(provider));
    }

}
```