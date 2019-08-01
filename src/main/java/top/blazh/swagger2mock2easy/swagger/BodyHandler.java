package top.blazh.swagger2mock2easy.swagger;

import com.alibaba.fastjson.JSONObject;
import top.blazh.swagger2mock2easy.mock2easy.Mock2easyRequestEntity;

@FunctionalInterface
public interface BodyHandler<I, R> {

    /**
     * 处理局部
     * @param obj
     * @param root
     * @param entity
     * @return
     */
    R handle(I obj, JSONObject root, Mock2easyRequestEntity entity);

}
