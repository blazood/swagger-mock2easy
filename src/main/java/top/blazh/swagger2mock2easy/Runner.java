package top.blazh.swagger2mock2easy;


import com.alibaba.fastjson.JSONObject;
import top.blazh.swagger2mock2easy.mock2easy.Mock2easyRequestEntity;
import top.blazh.swagger2mock2easy.mock2easy.Mock2easySender;
import top.blazh.swagger2mock2easy.swagger.*;

public class Runner {

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
