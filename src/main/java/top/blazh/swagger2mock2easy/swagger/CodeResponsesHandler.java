package top.blazh.swagger2mock2easy.swagger;

import com.alibaba.fastjson.JSONObject;
import top.blazh.swagger2mock2easy.mock2easy.Mock2easyRequestEntity;

public class CodeResponsesHandler implements BodyHandler<JSONObject, Void> {

    @Override
    public Void handle(JSONObject obj, JSONObject root, Mock2easyRequestEntity entity) {
        for (String code : obj.keySet()) {
            if ("200".equals(code)){
                continue;
            }
            JSONObject body = obj.getJSONObject(code);
            // TODO
        }
        return null;
    }

}
