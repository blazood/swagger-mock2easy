package top.blazh.swagger2mock2easy.swagger;

import com.alibaba.fastjson.JSONObject;
import top.blazh.swagger2mock2easy.mock2easy.Mock2easyRequestEntity;
import top.blazh.swagger2mock2easy.mock2easy.Mock2easyResponseParameter;

public class ResponsesHandler implements BodyHandler<JSONObject, Void> {

    @Override
    public Void handle(JSONObject obj, JSONObject root, Mock2easyRequestEntity entity) {
        Mock2easyResponseParameter responseRoot = Mock2easyResponseParameter.root();
        convert(obj, responseRoot);
        entity.setResponseParameters(responseRoot.flat());
        return null;
    }

    /**
     * JSON 转 Mock2easyResponseParameter
     * @param obj
     * @param r
     */
    private void convert(JSONObject obj, Mock2easyResponseParameter r) {
        if (obj.containsKey("properties")) {
            // object处理
            JSONObject properties = obj.getJSONObject("properties");
            for (String name : properties.keySet()) {
                JSONObject p = properties.getJSONObject(name);
                Mock2easyResponseParameter.Kind kind = null;
                boolean isNext = true;
                switch (p.getString("type")) {
                    case "object":
                        kind = Mock2easyResponseParameter.Kind.object;
                        break;
                    case "array":
                        kind = Mock2easyResponseParameter.Kind.array;
                        break;
                    default:
                        kind = Mock2easyResponseParameter.Kind.string;
                        isNext = false;
                }

                Mock2easyResponseParameter description = null;
                description = r.append(kind, name, p.getString("description"), p.getString("example"));

                if (isNext) {
                    convert(p, description);
                }
            }
        } else if (obj.containsKey("items")) {
            // array处理
            JSONObject p = obj.getJSONObject("items");
            convert(p, r);
        }
    }

}
