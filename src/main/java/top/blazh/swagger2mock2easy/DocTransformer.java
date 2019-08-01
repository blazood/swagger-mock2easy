package top.blazh.swagger2mock2easy;

import java.util.List;

/**
 * 文档转换器
 * @param <S>
 * @param <T>
 * @author blazh
 */
@FunctionalInterface
public interface DocTransformer<S ,T extends DocEntity> {

    /**
     * 转换
     * @param provider
     * @return
     */
    List<T> transform(SourceProvider<S> provider);

}
