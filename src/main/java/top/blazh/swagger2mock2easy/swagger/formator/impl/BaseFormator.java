package top.blazh.swagger2mock2easy.swagger.formator.impl;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import top.blazh.swagger2mock2easy.mock2easy.Mock2easyRequestEntity;
import top.blazh.swagger2mock2easy.mock2easy.Mock2easyResponseParameter;
import top.blazh.swagger2mock2easy.mock2easy.Mock2easyResponseParameter.Kind;
import top.blazh.swagger2mock2easy.swagger.formator.TypeFormator;

import static java.util.Optional.ofNullable;

public abstract class BaseFormator implements TypeFormator<JSONObject> {

    @Override
    public String defaultVal() {
        return "--";
    }

    /**
     * mock的类型
     * @return
     */
    protected abstract Kind mockType();

    @Override
    public Mock2easyResponseParameter format(String name, JSONObject p, Mock2easyResponseParameter r, Mock2easyRequestEntity entity) {
        return r.append(mockType(), name, p.getString("description"), ofNullable(p.getString("example")).filter(StringUtils::isNotEmpty).orElse(defaultVal()));
    }

}