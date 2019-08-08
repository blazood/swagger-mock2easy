package top.blazh.swagger2mock2easy.swagger;

import com.alibaba.fastjson.JSONObject;

import top.blazh.swagger2mock2easy.mock2easy.Mock2easyRequestEntity;
import top.blazh.swagger2mock2easy.mock2easy.Mock2easyResponseParameter;
import top.blazh.swagger2mock2easy.swagger.formator.Formators;

public class ResponsesHandler implements BodyHandler<JSONObject, Void> {

    @Override
    public Void handle(JSONObject obj, JSONObject root, Mock2easyRequestEntity entity) {
        Mock2easyResponseParameter responseRoot = Mock2easyResponseParameter.root();
        JSONObject properties = obj.getJSONObject("properties");
        for (String name : properties.keySet()) {
            JSONObject jsonObject = properties.getJSONObject(name);
            Formators.format(name, jsonObject, responseRoot, entity);
        }
        entity.setResponseParameters(responseRoot.flat());
        return null;
    }

}
