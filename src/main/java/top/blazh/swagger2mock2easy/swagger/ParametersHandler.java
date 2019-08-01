package top.blazh.swagger2mock2easy.swagger;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import top.blazh.swagger2mock2easy.mock2easy.Mock2easyRequestEntity;
import top.blazh.swagger2mock2easy.mock2easy.Mock2easyRequestParameter;

import java.util.ArrayList;


public class ParametersHandler implements BodyHandler<JSONArray, Void> {

    @Override
    public Void handle(JSONArray parameters, JSONObject root, Mock2easyRequestEntity entity){
        ArrayList<Mock2easyRequestParameter> mock2EasyRequestParameters = new ArrayList<>();
        if (parameters != null){
            for (int i = 0; i < parameters.size(); i++) {
                mock2EasyRequestParameters.add(each(parameters.getJSONObject(i)));
            }
        }
        entity.setRequiredParameters(mock2EasyRequestParameters);
        return null;
    }

    private Mock2easyRequestParameter each(JSONObject parameter){
        // 入参处理 入参只有一级, 所以自己弄
        Mock2easyRequestParameter mock2EasyRequestParameter = new Mock2easyRequestParameter();
        mock2EasyRequestParameter.setName(parameter.getString("name"));
        // 默认值拼接进去
        mock2EasyRequestParameter.setRemark(parameter.getString("description") + "(default:" + parameter.getString("default") + ")");
        mock2EasyRequestParameter.setRequired(parameter.getBoolean("required"));
        return mock2EasyRequestParameter;
    }

}
