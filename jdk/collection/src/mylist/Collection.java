package mylist;

import java.util.Iterator;

/**
 * 集合接口
 * @param <E>集合元素
 */
public interface Collection<E> extends Iterable<E>{
    
    //查询操作
    /**
     * 返回集合元素的数量
     * 如果数量大于<tt>Integer.MAX_VELUE</tt>，返回<tt>Integer.MAX_VELUE</tt>
     * 
     * @return 集合元素的数量
     */
    int size();
    
    /**
     * 集合是否为空，如果为空，返回true
     * 
     * @return true:集合为空
     */
    boolean isEmpty();
    
    /**
     * 集合中是否包含特定元素o
     * 集合不为空且包含，才返回true
     * 
     * @return true:集合包含元素o
     */
    boolean contains(Object o);
    
    /**
     * 返回一个包含集合所有元素的迭代器
     * 迭代器中的元素是无需的（除非特殊有序集合）
     * 
     * @return 包含集合所有元素的迭代器
     */
    Iterator<E> iterator();
    
    /**
     * 返回包含集合所有元素的数组
     * 数组元素顺序由集合保证
     * 
     * 返回的数组是“安全的”，即返回的是一个新建的数组，操作不会影像到原集合
     * 
     * @return 包含集合所有元素的数组
     */
    Object[] toArray();
    
    /**
     * 返回特定对象数组，包含集合所有元素
     * 数组元素顺序由集合保证
     * 如果传入的对象数组为空，则会新建一个数组返回，如果传入的数组过大，则数组后续对象将被设置为null返回
     * 
     * @return 特定对象数组，包含集合所有元素
     */
    <T> T[] toArray(T[] a);
    
    //修改操作
    
    /**
     * 往集合新增元素
     * 
     * @return true:添加成功
     */
    boolean add(E e);
    
    /**
     * 从集合删除元素
     * 
     * @return true:删除成功
     */
    boolean remove(Object e);
    
    
    //批量操作
    
    /**
     * 集合是否包含给定集合中的所有元素
     * 
     * @return true:集合包含给定集合中的所有元素
     */
    boolean containsAll(Collection<?> c);
    
    /**
     * 将给定集合中的所有元素添加到集合
     * 
     * @return true:添加成功
     */
    boolean addAll(Collection<? extends E> c);
    
    /**
     * 从集合中删除指定集合中的所有元素
     * 删除成功后，将没有与指定集合重复的元素
     * 
     * @return true:集合中有元素变动
     */
    boolean removeAll(Collection<?> c);
    
    /**
     * 保留集合中所有与指定集合重复的元素，其他元素都被删除
     * 
     * @return true:集合中有元素变动
     */
    boolean retainAll(Collection<?> c);
    
    /**
     * 删除集合中所有元素
     */
    void clear();
    
    //比较和hash
    boolean equals(Object o);
    int hashCode();
}
