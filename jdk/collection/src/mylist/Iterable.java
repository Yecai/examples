package mylist;

import java.util.Iterator;

/**
 * ʵ��foreachѭ���ĵ������ӿ�
 * 
 * @param <T> ���������صĶ���
 */
public interface Iterable<T> {
    
    /**
     * ����T����ĵ�����
     * @return ������
     */
    Iterator<T> iterator();

}
