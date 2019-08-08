package top.blazh.swagger2mock2easy;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import top.blazh.swagger2mock2easy.mock2easy.Mock2easyRequestEntity;
import top.blazh.swagger2mock2easy.mock2easy.Mock2easySender;
import top.blazh.swagger2mock2easy.swagger.*;

import java.util.List;

public class Runner {

    public static void main(String[] args) {
        // 指定 swagger 的地址
        SourceProvider<JSONObject> provider = SwaggerProvider.builder()
                .url("http://127.0.0.1:8080/v2/api-docs")
                .build();
        // 指定 mock2easy 的地址
        DocSender<Mock2easyRequestEntity> sender = Mock2easySender.builder()
                .timeout(1000 * 60)
                .urls(new String[]{"http://192.168.47.180:8001", "http://192.168.47.187:8001"})
                .build();

        // 使用默认的转换器
        DocTransformer<JSONObject, Mock2easyRequestEntity> transformer = SwaggerToMock2easyTransformer.DEFAULT;

        // 转换并发送
        sender.send(transformer.transform(provider));
    }

}
