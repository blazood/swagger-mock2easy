package top.blazh.swagger2mock2easy.swagger.formator.impl;

import com.alibaba.fastjson.JSONObject;
import top.blazh.swagger2mock2easy.mock2easy.Mock2easyRequestEntity;
import top.blazh.swagger2mock2easy.mock2easy.Mock2easyResponseParameter;
import top.blazh.swagger2mock2easy.swagger.formator.Formators;
import top.blazh.swagger2mock2easy.swagger.formator.TypeEnum;

import static top.blazh.swagger2mock2easy.mock2easy.Mock2easyResponseParameter.Kind;
import static top.blazh.swagger2mock2easy.swagger.formator.TypeEnum.ARRAY;

public class ArrayFormator extends BaseFormator {

    @Override
    protected Kind mockType() {
        return Kind.array;
    }

    @Override
    public TypeEnum type() {
        return ARRAY;
    }

    @Override
    public Mock2easyResponseParameter format(String name, JSONObject obj, Mock2easyResponseParameter r, Mock2easyRequestEntity entity) {
        Mock2easyResponseParameter format = super.format(name, obj, r, entity);
        JSONObject p = obj.getJSONObject("items");
        Formators.format(null , p, format, entity);
        return format;
    }

}
