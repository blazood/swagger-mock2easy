package top.blazh.swagger2mock2easy;

import java.util.List;

/**
 * 文档发送者
 * @param <T>
 * @author blazh
 */
@FunctionalInterface
public interface DocSender<T extends DocEntity> {

    /**
     * 发送
     * @param docEntity
     */
    void send(List<T> docEntity);

}
