package top.blazh.swagger2mock2easy.swagger.formator;

import top.blazh.swagger2mock2easy.mock2easy.Mock2easyRequestEntity;
import top.blazh.swagger2mock2easy.mock2easy.Mock2easyResponseParameter;

public interface TypeFormator<T> {

    /**
     * 处理的swagger类型
     * @return
     */
    TypeEnum type();

    /**
     * 默认值
     * @return
     */
    String defaultVal();

    /**
     * 实际的转换
     *  @param name
     * @param obj
     * @param parent 上级
     * @param entity
     * @return
     */
    Mock2easyResponseParameter format(String name, T obj, Mock2easyResponseParameter parent, Mock2easyRequestEntity entity);

}
