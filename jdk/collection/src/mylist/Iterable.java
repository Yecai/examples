package mylist;

import java.util.Iterator;

/**
 * 实现foreach循环的迭代器接口
 * 
 * @param <T> 迭代器返回的对象
 */
public interface Iterable<T> {
    
    /**
     * 返回T对象的迭代器
     * @return 迭代器
     */
    Iterator<T> iterator();

}
