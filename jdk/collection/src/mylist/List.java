package mylist;

import java.util.Iterator;

public interface List<E> extends Collection<E> {
    //��ѯ����
    
    /**
     * ����list�е�Ԫ������
     * 
     * @return list�е�Ԫ������
     */
    int size();
    
    /**
     * list�Ƿ�Ϊ��
     * 
     * @return true:listΪ��
     */
    boolean isEmpty();
    
    boolean contains(Object o);
    
    Iterator<E> iterator();
    
    Object[] toArray();
    
    <T> T[] toArray(T[] a);
    
    //�޸Ĳ���
    boolean add(E e);
    
    boolean remove(Object o);
    
    //�����޸Ĳ���
    boolean containsAll(Collection<?> c);
}
