package top.blazh.swagger2mock2easy.mock2easy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Builder;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import top.blazh.swagger2mock2easy.DocSender;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

/**
 * 发送给 mock2easy
 * @author blazh
 */
@Slf4j
@Builder
public class Mock2easySender implements DocSender<Mock2easyRequestEntity> {

    @NonNull
    private String[] urls;

    @NonNull
    private int timeout = 60 * 1000;

    @Override
    @SneakyThrows
    public void send(List<Mock2easyRequestEntity> docEntity) {
        doSend(docEntity);
    }

    @SneakyThrows
    private void doSend(List<Mock2easyRequestEntity> docEntity){
        int config = SerializerFeature.config(JSON.DEFAULT_GENERATE_FEATURE, SerializerFeature.WriteEnumUsingToString, true);
        for (String domain : urls) {
            for (Mock2easyRequestEntity mock2easyRequestEntity : docEntity) {
                    request(config, domain, mock2easyRequestEntity);
            }
        }
    }

    @SneakyThrows
    private void request(int config, String domain, Mock2easyRequestEntity mock2easyRequestEntity){
        String r = Request.Post(domain + "/modify")
                .connectTimeout(timeout)
                .socketTimeout(timeout)
                .bodyString(JSON.toJSONString(mock2easyRequestEntity , config), ContentType.APPLICATION_JSON)
                .execute().returnContent().asString();
        log.info(r);
    }


}
