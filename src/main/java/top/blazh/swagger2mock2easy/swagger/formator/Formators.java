package top.blazh.swagger2mock2easy.swagger.formator;

import com.alibaba.fastjson.JSONObject;
import top.blazh.swagger2mock2easy.mock2easy.Mock2easyRequestEntity;
import top.blazh.swagger2mock2easy.mock2easy.Mock2easyResponseParameter;
import top.blazh.swagger2mock2easy.swagger.formator.impl.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Formators {

    public static List<TypeFormator> FORMATORS = Arrays.asList(
            new BoolFormator(),
            new IntegerFormator(),
            new NumberFormator(),
            new ObjectFormator(),
            new StringFormator(),
            new ArrayFormator()
    );

    public static Map<TypeEnum, TypeFormator> FORMAT_MAP = FORMATORS.stream().collect(Collectors.toMap(TypeFormator::type, Function.identity()));


    public static void format(String name, JSONObject obj, Mock2easyResponseParameter parent, Mock2easyRequestEntity entity){
        TypeEnum type = TypeEnum.valueOf(obj.getString("type").toUpperCase());
        TypeFormator typeFormator = FORMAT_MAP.get(type);
        typeFormator.format(name, obj, parent, entity);
    }


}
