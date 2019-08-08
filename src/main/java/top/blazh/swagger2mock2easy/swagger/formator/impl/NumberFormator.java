package top.blazh.swagger2mock2easy.swagger.formator.impl;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import top.blazh.swagger2mock2easy.mock2easy.Mock2easyResponseParameter;
import top.blazh.swagger2mock2easy.mock2easy.Mock2easyResponseParameter.Kind;
import top.blazh.swagger2mock2easy.swagger.formator.TypeEnum;
import top.blazh.swagger2mock2easy.swagger.formator.TypeFormator;

import java.util.EnumSet;

import static java.util.Optional.ofNullable;
import static top.blazh.swagger2mock2easy.swagger.formator.TypeEnum.INTEGER;
import static top.blazh.swagger2mock2easy.swagger.formator.TypeEnum.NUMBER;

/**
 * 数值类型的处理器
 * @author blazh
 */
public class NumberFormator extends IntegerFormator {

    @Override
    public TypeEnum type() {
        return NUMBER;
    }

    @Override
    public String defaultVal() {
        return "0.0";
    }

}
