package mylist;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

//TODO 校验toArray发生finishToArray方法
public abstract class AbstractCollection<E> implements Collection<E> {
	/**
	 * 唯一无参构造函数（用于子类构造函数调用，通常是隐式调用）
	 */
	protected AbstractCollection(){};
	
	//查询操作
	public abstract Iterator<E> iterator();
	public abstract int size();
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	public boolean contains(Object o) {
		Iterator<E> it = iterator();
		if (o == null) {
			while(it.hasNext()) {
				if (it.next() == null) {
					return true;
				}
			}
		} else {
			while(it.hasNext()) {
				if (o.equals(it.next())) {
					return true;
				}
			}
		}
		return false;
	}
	
	public Object[] toArray() {
		Object[] r = new Object[size()];
		Iterator<E> it = iterator();
		for (int i = 0; i < r.length; i++) {
			if (!it.hasNext()) //迭代器中元素数量少于size数量
				return Arrays.copyOf(r, i);
			r[i] = it.next();
		}
		return it.hasNext() ? finishToArray(r, it) : r;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] a) {
		int size = size();
		T[] r = a.length > size ? a : (T[])java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
		Iterator<E> it = iterator();
		
		for (int i = 0; i < r.length; i++) {
			if (!it.hasNext()) {//迭代器中元素数量少于size数量
				if (a == r) {
					r[i] = null; //a数组较大，r=a，后续元素置null，返回
				} else if (a.length < i) {
					return Arrays.copyOf(r, i); //a元素较小，r为新数组，截取r的i大小，返回
				} else {
					//a元素较小，r为新数组，迭代器元素数量比a元素数量还小
					//也就是说，list中实际数量比a.length小，需要返回a数组
					//将r中元素copy到a中
					System.arraycopy(r, 0, a, 0, i);
					if (a.length > i) {
						a[i] = null;
					}
				}
			}
			r[i] = (T)it.next();
		}
		return it.hasNext() ? finishToArray(r, it) : r;
	}
	
	/**最大数组长度**/
	private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
	
	/**
	 * toArray时，迭代器数量大于size，处理多出的元素
	 */
	@SuppressWarnings("unchecked")
	private <T> T[] finishToArray(T[] r, Iterator<E> it) {
		int i = r.length; //数组元素数量
		while (it.hasNext()) {
			//数组扩容
			int cap = r.length; //数组容量
			if (i == cap) {
				int newCap = cap + (cap >> 1) + 1;
				if (newCap - MAX_ARRAY_SIZE > 0)
					newCap = hugeCapacity(cap + 1);
				r = Arrays.copyOf(r, newCap);//数组扩容成功
			}
			r[i++] = (T)it.next();
		}
		return (i == r.length) ? r : Arrays.copyOf(r, i);
	}
	
	private static int hugeCapacity(int minCapacity) {
		if (minCapacity < 0) {
			throw new OutOfMemoryError("Required array size too large");
		}
		return (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
	}
	
	//修改操作
	public boolean add(E e) {
		throw new UnsupportedOperationException();
	}
	
	public boolean remove(Object o) {
		Iterator<E> it = iterator();
		if (o == null) {
			while (it.hasNext()) {
				if (it.next() == null) {
					it.remove();
					return true;
				}
			}
		} else {
			while (it.hasNext()) {
				if (o.equals(it.next())) {
					it.remove();
					return true;
				}
			}
		}
		return false;
	}
	
	//批量操作
	public boolean containsAll(Collection<?> c) {
		for (Object e : c) {
			if (!contains(e)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean addAll(Collection<? extends E> c) {
		boolean modified = false;
		for (E e : c) {
			if (add(e)) {
				modified = true;
			}
		}
		return modified;
	}
	
	public boolean removeAll(Collection<?> c) {
		Objects.requireNonNull(c);
		boolean modified = false;
		Iterator<?> it = iterator();
		while (it.hasNext()) {
			if (c.contains(it.next())) {
				it.remove();
				modified = true;
			}
		}
		return modified;
	}
	
	public boolean retainAll(Collection<?> c) {
		Objects.requireNonNull(c);
		boolean modified = false;
		Iterator<?> it = iterator();
		while (it.hasNext()) {
			if (!c.contains(it.next())) {
				it.remove();
				modified = true;
			}
		}
		return modified;
	}
	
	public void clear() {
		Iterator<E> it = iterator();
		while (it.hasNext()) {
			it.next();
			it.remove();
		}
	}
	
	//转换成字符串
	
	public String toString() {
		Iterator<E> it = iterator();
		if (!it.hasNext()) {
			return "[]";
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(;;) {
			E e = it.next();
			sb.append(e == this ? "(this Collection)" : e);
			if (!it.hasNext()) {
				return sb.append("]").toString();
			}
			sb.append(",").append(" ");
		}
	}
}
