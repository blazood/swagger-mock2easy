package top.blazh.swagger2mock2easy.swagger;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import top.blazh.swagger2mock2easy.DocTransformer;
import top.blazh.swagger2mock2easy.SourceProvider;
import top.blazh.swagger2mock2easy.mock2easy.Mock2easyRequestEntity;
import top.blazh.swagger2mock2easy.mock2easy.Mock2easyResponseParameter;


import java.util.*;
import java.util.stream.Collectors;

/**
 * swagger 转 mock2easy 文档
 * @author blazh
 */
@Builder
public class SwaggerToMock2easyTransformer implements DocTransformer<JSONObject, Mock2easyRequestEntity> {

    @NonNull
    private ParametersHandler parametersHandler;

    @NonNull
    private CodeResponsesHandler codeResponsesHandler;

    @NonNull
    private ResponsesHandler responsesHandler;

    @NonNull
    private String suffix;

    public static SwaggerToMock2easyTransformer DEFAULT = SwaggerToMock2easyTransformer.builder()
            .codeResponsesHandler(new CodeResponsesHandler())
            .parametersHandler(new ParametersHandler())
            .responsesHandler(new ResponsesHandler())
            .suffix(".json")
            .build();

    @Override
    public List<Mock2easyRequestEntity> transform(SourceProvider<JSONObject> provider) {
        ArrayList<Mock2easyRequestEntity> entities = new ArrayList<>();

        JSONObject j = provider.provid();

        Map<String, String> tags = j.getJSONArray("tags").stream().map(o -> (JSONObject) o).collect(Collectors.toMap(o -> o.getString("name"), o -> o.getString("description")));

        JSONObject paths = j.getJSONObject("paths");

        for (String path : paths.keySet()) {

            JSONObject api = paths.getJSONObject(path);

            // 单个api
            Mock2easyRequestEntity entity = new Mock2easyRequestEntity();
            JSONObject apiContent = null;
            if (api.containsKey("post")){
                apiContent = api.getJSONObject("post");
                entity.setInterfaceType("POST");
            } else {
                apiContent = api.getJSONObject("get");
                entity.setInterfaceType("GET");
            }

            String tag = tags.get(apiContent.getJSONArray("tags").getString(0));

            entity.setInterfaceUrl(path + suffix);
            entity.setInterfaceName(tag + "-" +apiContent.getString("summary"));


            // responses 处理
            JSONObject responses = apiContent.getJSONObject("responses");
            codeResponsesHandler.handle(responses , j, entity);

            // 入参
            JSONArray parameters = apiContent.getJSONArray("parameters");
            parametersHandler.handle(parameters, j, entity);

            // 出参是多级,嵌套的
            JSONObject jsonObject = apiContent.getJSONObject("responses").getJSONObject("200").getJSONObject("schema");
            responsesHandler.handle(jsonObject, j, entity);

            entities.add(entity);
        }
        return entities;
    }



}
