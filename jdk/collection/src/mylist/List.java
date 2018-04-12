package mylist;

import java.util.Iterator;
import java.util.ListIterator;

public interface List<E> extends Collection<E> {
    //查询操作
    
    /**
     * 返回list中的元素数量
     * 
     * @return list中的元素数量
     */
    int size();
    
    /**
     * list是否为空
     * 
     * @return true:list为空
     */
    boolean isEmpty();
    
    boolean contains(Object o);
    
    Iterator<E> iterator();
    
    Object[] toArray();
    
    <T> T[] toArray(T[] a);
    
    //修改操作
    boolean add(E e);
    
    boolean remove(Object o);
    
    //批量修改操作
    boolean containsAll(Collection<?> c);
    
    boolean addAll(Collection<? extends E> c);
    
    /**
     * 从List的index位置开始插入指定的集合，所有原list的后续元素后移
     * 
     * @return true:List中的元素发生变更
     */
    boolean addAll(int index, Collection<? extends E> c);
    
    boolean removeAll(Collection<?> c);
    
    boolean retainAll(Collection<?> c);
    
    void clear();
    
    //比较和哈希
    boolean equals(Object o);
    
    int hashCode();
    
    //位置访问操作
    
    /**
     * 返回List中指定位置的元素
     * 
     * @return: List中指定位置的元素
     */
    E get(int index);
    
    /**
     * 替换list中指定位置的元素
     * 
     * @return: 指定位置替换前的元素
     */
    E set(int index, E element);
    
    /**
     * 在list的指定位置插入元素，所有后续元素后移
     */
    void add(int index, E element);
    
    /**
     * 移除指定位置的元素
     * 
     * @return 返回被移除的元素
     */
    E remove(int index);
    
    //搜索操作
    /**
     * 返回list中匹配的第一个元素的位置，如果list没有任何元素匹配，返回-1
     * 匹配方式：(o==null ? get(i)==null : o.equals(get(i)))
     * 
     * @return 匹配的第一个元素的位置，或者-1表示没有任何元素匹配
     */
    int indexOf(Object o);
    
    /**
     * 返回list中匹配的最后一个元素的位置，如果list没有任何元素匹配，返回-1
     * 匹配方式：(o==null ? get(i)==null : o.equals(get(i)))
     * 
     * @return 匹配的最后一个元素的位置，或者-1表示没有任何元素匹配
     */
    int lastIndexOf(Object o);
    
    //List Iterators迭代器
    /**
     * 返回list迭代器，包含list中所有元素
     * 
     * @return list迭代器
     */
    ListIterator<E> listIterator();
    /**
     * 返回list迭代器，包含从list的指定位置开始往后所有元素
     * 
     * @return list迭代器
     */
    ListIterator<E> listIterator(int index);
    
    //查看操作
    
    /**
     * 返回一个list中从fromIndex到toIndex的子list，对这个子list的操作会影像
     * 到父list，反之亦然，所以不要对子list做新增、删除等结构性操作
     * 
     * @return 指定访问的子list
     */
    List<E> subList(int fromIndex, int toIndex);
    
}
