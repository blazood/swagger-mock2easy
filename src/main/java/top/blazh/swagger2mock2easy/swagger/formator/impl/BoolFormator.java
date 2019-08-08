package top.blazh.swagger2mock2easy.swagger.formator.impl;

import top.blazh.swagger2mock2easy.swagger.formator.TypeEnum;

import java.util.EnumSet;

import static top.blazh.swagger2mock2easy.swagger.formator.TypeEnum.BOOLEAN;
import static top.blazh.swagger2mock2easy.swagger.formator.TypeEnum.NUMBER;

/**
 * 数值类型的处理器
 * @author blazh
 */
public class BoolFormator extends IntegerFormator {

    @Override
    public TypeEnum type() {
        return BOOLEAN;
    }

    @Override
    public String defaultVal() {
        return "true";
    }

}
