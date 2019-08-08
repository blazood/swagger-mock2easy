package top.blazh.swagger2mock2easy.swagger.formator.impl;

import com.alibaba.fastjson.JSONObject;
import top.blazh.swagger2mock2easy.mock2easy.Mock2easyRequestEntity;
import top.blazh.swagger2mock2easy.mock2easy.Mock2easyResponseParameter;
import top.blazh.swagger2mock2easy.swagger.formator.Formators;
import top.blazh.swagger2mock2easy.swagger.formator.TypeEnum;

import static top.blazh.swagger2mock2easy.mock2easy.Mock2easyResponseParameter.*;
import static top.blazh.swagger2mock2easy.swagger.formator.TypeEnum.*;

public class ObjectFormator extends BaseFormator {

    @Override
    protected Kind mockType() {
        return Kind.object;
    }

    @Override
    public TypeEnum type() {
        return OBJECT;
    }

    @Override
    public Mock2easyResponseParameter format(String name, JSONObject obj, Mock2easyResponseParameter r, Mock2easyRequestEntity entity) {
        Mock2easyResponseParameter format = super.format(name, obj, r, entity);
        JSONObject properties = obj.getJSONObject("properties");
        for (String childName : properties.keySet()) {
            Formators.format(childName, properties.getJSONObject(childName), format, entity);
        }
        return format;
    }

}
