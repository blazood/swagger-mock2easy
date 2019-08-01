package top.blazh.swagger2mock2easy;

/**
 * 源提供者
 * @param <T>
 * @author blazh
 */
@FunctionalInterface
public interface SourceProvider<T> {

    /**
     * 提供原始数据
     * @return
     */
    T provid();

}
