package top.blazh.swagger2mock2easy.swagger.formator;

import com.alibaba.fastjson.JSONObject;
import top.blazh.swagger2mock2easy.mock2easy.Mock2easyRequestEntity;
import top.blazh.swagger2mock2easy.mock2easy.Mock2easyResponseParameter;
import top.blazh.swagger2mock2easy.swagger.formator.impl.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Formators {

    /**
     * 默认的字段格式化集合
     */
    public static final List<TypeFormator> FORMATORS = Arrays.asList(
            new BoolFormator(),
            new IntegerFormator(),
            new NumberFormator(),
            new ObjectFormator(),
            new StringFormator(),
            new ArrayFormator()
    );

    public static final Map<TypeEnum, TypeFormator> FORMAT_MAP = FORMATORS.stream().collect(ConcurrentHashMap::new, (m, e) -> m.put(e.type(), e), ConcurrentHashMap::putAll);

    /**
     * 设置自己的格式处理器
     * @param typeFormator
     */
    public static void setFormator(TypeFormator typeFormator){
        FORMAT_MAP.put(typeFormator.type(), typeFormator);
    }


    public static void format(String name, JSONObject obj, Mock2easyResponseParameter parent, Mock2easyRequestEntity entity){
        TypeEnum type = TypeEnum.valueOf(obj.getString("type").toUpperCase());
        TypeFormator typeFormator = FORMAT_MAP.get(type);
        typeFormator.format(name, obj, parent, entity);
    }


}
