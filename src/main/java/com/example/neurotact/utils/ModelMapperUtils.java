package com.example.neurotact.utils;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 借助ModelMapper实现的映射工具类
 * 参考https://www.jianshu.com/p/d62c2be60617
 */
public class ModelMapperUtils {
    /**
     * 映射普通类
     * 如果源对象为null，直接返回null，而不会抛出异常
     *
     * @param sourceObject     源对象
     * @param destinationClass 目标类
     * @param <T>
     * @return 映射结果
     */
    public static <T> T mapClass(Object sourceObject, Class<T> destinationClass)
    {
        if (sourceObject == null)
        {
            return null;
        }
        ModelMapper modelMapper = new ModelMapper();
        // 如果不是STRICT，将会联想映射
        // 参考https://www.cnblogs.com/passedbylove/p/12408932.html
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(sourceObject, destinationClass);
    }

    /**
     * 映射List
     * 如果源List为[]，亦返回[]
     * 如果源List为null，直接返回[]，而不会抛出异常
     * 如果源List内元素存在null，映射结果中亦为null
     *
     * @param sourceList       源List
     * @param destinationClass 目标List内的类型
     * @param <T>
     * @return 映射结果
     */
    public static <T> List<T> mapList(List sourceList, Class<T> destinationClass)
    {
        if (sourceList == null)
        {
            return new ArrayList<>();
        }
        Type destinationType = new ParameterizedTypeImpl(List.class, new Class[]{destinationClass});
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(sourceList, destinationType);
    }

}
