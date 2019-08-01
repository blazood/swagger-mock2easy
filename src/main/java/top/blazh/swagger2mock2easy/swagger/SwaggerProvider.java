package top.blazh.swagger2mock2easy.swagger;

import com.alibaba.fastjson.JSONObject;
import lombok.Builder;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.apache.http.client.fluent.Request;
import top.blazh.swagger2mock2easy.SourceProvider;

import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * swagger 提供者
 * @author blazh
 */
@Builder
public class SwaggerProvider implements SourceProvider<JSONObject> {

    /**
     * swagger 地址
     */
    @NonNull
    private String url;

    private static final Pattern PATTERN = Pattern.compile("\"\\$ref\":\"(.*?)\"", Pattern.DOTALL);


    @Override
    @SneakyThrows
    public JSONObject provid() {

        // 获取原始 json

        //Charset.forName("utf-8")
        String source = Request.Get(url).execute().returnContent().asString(Charset.forName("utf-8"));

        System.out.println(source);

        // 替换 $ref 格式的问题, fastjson 引用格式问题
        Matcher matcher = PATTERN.matcher(source);
        String newSpurce = source + "";
        while (matcher.find()) {
            String group = matcher.group(1);
            String newGroup = group.replaceAll("/", ".");
            newGroup = newGroup.replace("#", "$");
            newSpurce = newSpurce.replace(group, newGroup);
        }

        return JSONObject.parseObject(newSpurce);
    }

}
