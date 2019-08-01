package top.blazh.swagger2mock2easy.mock2easy;

import lombok.Getter;
import lombok.Setter;
import top.blazh.swagger2mock2easy.DocEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * mock2easy 的实体
 * @author blazh
 */
@Getter
@Setter
public class Mock2easyRequestEntity extends DocEntity implements Serializable {

    /**
     * 请求 类型 "POST" 或者 "GET"
     */
    private String interfaceType;

    /**
     * mock 的 请求列表
     */
    private List<Mock2easyRequestParameter> requiredParameters = new ArrayList<>();

    /**
     * mock 的 响应列表
     */
    private List<Mock2easyResponseParameter> responseParameters = new ArrayList<>();

    /**
     * 似乎没用
     */
    private List<String> reqError = new ArrayList<>();

    /**
     * 似乎没用
     */
    private List<String> docError = new ArrayList<>();

    /**
     * mock 接口地址
     */
    private String interfaceUrl;

    /**
     * mock 的 接口名字
     */
    private String interfaceName;

    /**
     * mock 的 懒加载 "no" "yes"
     */
    private String lazyLoad = "no";

    /**
     * 是不是使用 json 字符格式的 返回
     * 设置了 {@link responseParameters }, 这里就是 false
     * 设置了 {@link responseJson }, 这里就应该是 true
     */
    private Boolean responseParametersType = Boolean.FALSE;

    /**
     * mock 的 json 格式的返回
     */
    private String responseJson;

}
