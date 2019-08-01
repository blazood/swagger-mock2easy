package top.blazh.swagger2mock2easy.mock2easy;


import lombok.Data;

import java.io.Serializable;


/**
 * mock2easy 的 请求
 * @author blazh
 */
@Data
public class Mock2easyRequestParameter implements Serializable {

    /**
     * mock 的 请求名
     */
    private String name;

    /**
     * mock 的 是否必须
     */
    private Boolean required = Boolean.TRUE;

    /**
     * mock 的 备注
     */
    private String remark;


}
