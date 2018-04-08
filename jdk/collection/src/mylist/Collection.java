package mylist;

import java.util.Iterator;

/**
 * ���Ͻӿ�
 * @param <E>����Ԫ��
 */
public interface Collection<E> extends Iterable<E>{
    
    //��ѯ����
    /**
     * ���ؼ���Ԫ�ص�����
     * �����������<tt>Integer.MAX_VELUE</tt>������<tt>Integer.MAX_VELUE</tt>
     * 
     * @return ����Ԫ�ص�����
     */
    int size();
    
    /**
     * �����Ƿ�Ϊ�գ����Ϊ�գ�����true
     * 
     * @return true:����Ϊ��
     */
    boolean isEmpty();
    
    /**
     * �������Ƿ�����ض�Ԫ��o
     * ���ϲ�Ϊ���Ұ������ŷ���true
     * 
     * @return true:���ϰ���Ԫ��o
     */
    boolean contains(Object o);
    
    /**
     * ����һ��������������Ԫ�صĵ�����
     * �������е�Ԫ��������ģ������������򼯺ϣ�
     * 
     * @return ������������Ԫ�صĵ�����
     */
    Iterator<E> iterator();
    
    /**
     * ���ذ�����������Ԫ�ص�����
     * ����Ԫ��˳���ɼ��ϱ�֤
     * 
     * ���ص������ǡ���ȫ�ġ��������ص���һ���½������飬��������Ӱ��ԭ����
     * 
     * @return ������������Ԫ�ص�����
     */
    Object[] toArray();
    
    /**
     * �����ض��������飬������������Ԫ��
     * ����Ԫ��˳���ɼ��ϱ�֤
     * �������Ķ�������Ϊ�գ�����½�һ�����鷵�أ����������������������������󽫱�����Ϊnull����
     * 
     * @return �ض��������飬������������Ԫ��
     */
    <T> T[] toArray(T[] a);
    
    //�޸Ĳ���
    
    /**
     * ����������Ԫ��
     * 
     * @return true:��ӳɹ�
     */
    boolean add(E e);
    
    /**
     * �Ӽ���ɾ��Ԫ��
     * 
     * @return true:ɾ���ɹ�
     */
    boolean remove(Object e);
    
    
    //��������
    
    /**
     * �����Ƿ�������������е�����Ԫ��
     * 
     * @return true:���ϰ������������е�����Ԫ��
     */
    boolean containsAll(Collection<?> c);
    
    /**
     * �����������е�����Ԫ����ӵ�����
     * 
     * @return true:��ӳɹ�
     */
    boolean addAll(Collection<? extends E> c);
    
    /**
     * �Ӽ�����ɾ��ָ�������е�����Ԫ��
     * ɾ���ɹ��󣬽�û����ָ�������ظ���Ԫ��
     * 
     * @return true:��������Ԫ�ر䶯
     */
    boolean removeAll(Collection<?> c);
    
    /**
     * ����������������ָ�������ظ���Ԫ�أ�����Ԫ�ض���ɾ��
     * 
     * @return true:��������Ԫ�ر䶯
     */
    boolean retainAll(Collection<?> c);
    
    /**
     * ɾ������������Ԫ��
     */
    void clear();
    
    //�ȽϺ�hash
    boolean equals(Object o);
    int hashCode();
}
