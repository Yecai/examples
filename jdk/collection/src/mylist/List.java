package mylist;

import java.util.Iterator;

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
}
