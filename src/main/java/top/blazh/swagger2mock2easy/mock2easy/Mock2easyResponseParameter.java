package top.blazh.swagger2mock2easy.mock2easy;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * mock2easy 的 返回值
 * @author blazh
 */
@Data
public class Mock2easyResponseParameter  implements Serializable {

    /**
     * mock2easy 的类型
     */
    @AllArgsConstructor
    @SuppressWarnings("all")
    public static enum Kind {
        string("string"), array("array(object)"), object("object"), root("root"), mock("mock");
        private String msg;
        @Override
        public String toString() {
            return msg;
        }
    }

    /**
     * 得到root, 该对象不传给mockeasy, 实际需要他的children
     * @return
     */
    public static Mock2easyResponseParameter root() {
        Mock2easyResponseParameter parameter = new Mock2easyResponseParameter();
        parameter.setKind(Kind.root);
        return parameter;
    }

    /**
     * 插入一个下级
     */
    public Mock2easyResponseParameter append(Kind kind, String name, String remark, String rule) {
        Mock2easyResponseParameter mock2easyResponseParameter = new Mock2easyResponseParameter();
        children.add(mock2easyResponseParameter);
        mock2easyResponseParameter.parent = this;
        mock2easyResponseParameter.id = this.id + String.format("%02d", children.size());
        mock2easyResponseParameter.setKind(kind);
        mock2easyResponseParameter.setName(name);
        mock2easyResponseParameter.setRemark(remark);
        if (rule != null && !"".equals(rule)){
            mock2easyResponseParameter.setRule(rule);
        }
        if (this.kind == Kind.array) {
            mock2easyResponseParameter.array.add(this.id);
        }
        return mock2easyResponseParameter;
    }

    /**
     * 拉平
     * 一般是root最后调用, 得到一个平展的list
     * @return
     */
    public List<Mock2easyResponseParameter> flat() {
        ArrayList<Mock2easyResponseParameter> list = new ArrayList<>();
        list.add(this);
        for (Mock2easyResponseParameter child : this.children) {
            list.addAll(child.flat());
            child.children = null;
            child.parent = null;
        }
        if (this.kind == Kind.root) {
            return list.subList(1, list.size());
        }
        return list;
    }

    private Mock2easyResponseParameter() { }

    /**
     * 父级, 构建辅助
     */
    private Mock2easyResponseParameter parent;

    /**
     * 子项, 构建辅助
     */
    private List<Mock2easyResponseParameter> children = new ArrayList<>();

    /**
     * mock 的 id
     */
    private String id = "";

    /**
     * mock 的 类型
     */
    private Kind kind;

    /**
     * mock 的 名字
     */
    private String name;

    /**
     * mock 的 示例值
     */
    private String rule = "--";

    /**
     * mock 的 父级 array id
     */
    private List<String> array = new ArrayList<>();

    /**
     * 备注
     */
    private String remark;

    /**
     * 似乎没用
     */
    @Deprecated
    private String nameVerify;

    /**
     * 似乎没用
     */
    @Deprecated
    private String ruleVerify;

}
