package mylist;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;

public abstract class AbstractList<E> extends AbstractCollection<E> implements List<E> {
	/**
	 * 唯一无参构造函数（用于子类构造函数调用，通常是隐式调用）
	 */
	protected AbstractList(){};
	
	public boolean add(E e) {
		add(size(), e);
		return true;
	}
	
	abstract public E get(int index);
	
	public E set(int index, E element) {
		throw new UnsupportedOperationException();
	}
	
	public void add(int index, E element) {
		throw new UnsupportedOperationException();
	}
	
	public E remove(int index) {
		throw new UnsupportedOperationException();
	}
	
	//搜索操作
	public int indexOf(Object o) {
		ListIterator<E> it = listIterator();
		if (o == null) {
			while (it.hasNext()) {
				if (it.next() == null) {
					return it.previousIndex();
				}
			}
		} else {
			while (it.hasNext()) {
				if (o.equals(it.next())) {
					return it.previousIndex();
				}
			}
		}
		return -1;
	}
	
	public int lastIndexOf(Object o) {
		ListIterator<E> it = listIterator();
		if (o == null) {
			while (it.hasPrevious()) {
				if (it.previous() == null) {
					return it.nextIndex();
				}
			}
		} else {
			while (it.hasPrevious()) {
				if (o.equals(it.previous())) {
					return it.nextIndex();
				}
			}
		}
		return -1;
	}
	
	//批量操作
	public void clear() {
		removeRange(0, size());
	}
	
	public boolean addAll(int index, Collection<? extends E> c) {
		rangeCheckForAdd(index);//范围检查
		boolean modified = false;
		for (E e : c) {
			add(index++, e);
			modified = true;
		}
		return modified;
	}
	
	//迭代器
	public Iterator<E> iterator() {
		return new Itr();
	}
	public ListIterator<E> listIterator() {
		return listIterator(0);
	}
	
	/**
	 * 返回List中指定index开始的所有元素ListIterator
	 * 该迭代器第一次调用next将返回index指向的对象
	 */
	public ListIterator<E> listIterator(final int index) {
		rangeCheckForAdd(index);
		return new ListItr(index);
	}
	
	private class Itr implements Iterator<E> {
		/**
		 * 游标，该游标指向的对象将在下次调用next()方法时返回
		 */
		int cursor = 0;
		/**
		 * 游标，最近一次被调用next()或previous()返回对象的位置，如果调用了remove()则设置为-1
		 */
		int lastRet = -1;
		
		/**
		 * 迭代器认为List所具有的modCount值，如果List中modCount被修改导致与expectedModCount不一致，说明存在并发修改操作
		 */
		int expectedModCount = modCount;
		
		@Override
		public boolean hasNext() {
			return cursor != size();
		}

		public E next() {
			checkForComodification();
			try {
				int i = cursor;
				E next = get(i);
				lastRet = i;
				cursor = i + 1;
				return next;
			} catch (IndexOutOfBoundsException e) {
				checkForComodification();
				throw new NoSuchElementException();
			}
		}
		
		public void remove() {
			if (lastRet < 0) { //lastRet游标未指向任何元素，无法remove
				throw new IllegalStateException();
			}
			checkForComodification();
			
			try {
				AbstractList.this.remove(lastRet);
				if (lastRet < cursor) {
					cursor--;
				}
				lastRet = -1;
				expectedModCount = modCount; //remove完成，modCount发生改变，重置expectedModCount
			} catch (IndexOutOfBoundsException e) {
				throw new ConcurrentModificationException();
			}
			
		}

		/**
		 * 检查是否存在并发修改，存在则抛ConcurrentModificationException异常
		 */
		final void checkForComodification() {
			if (modCount != expectedModCount) {
				throw new ConcurrentModificationException();
			}
		}
		
	}
	
	private class ListItr extends Itr implements ListIterator<E> {
		ListItr(int index) {
			cursor = index;
		}
		
		public boolean hasPrevious() {
			return cursor != 0;
		}

		public E previous() {
			checkForComodification();
			try {
				int i = cursor - 1;
				E previous = get(i);
				lastRet = cursor = i;
				return previous;
			} catch (IndexOutOfBoundsException e) {
				checkForComodification();
				throw new NoSuchElementException();
			}
		}

		public int nextIndex() {
			return cursor;
		}

		public int previousIndex() {
			return cursor - 1;
		}

		public void set(E e) {
			if (lastRet < 0) {
				throw new IllegalStateException();
			}
			checkForComodification();
			
			try {
				AbstractList.this.set(lastRet, e);
				expectedModCount = modCount;
			} catch (IndexOutOfBoundsException ex) {
				throw new ConcurrentModificationException();
			}
		}

		public void add(E e) {
			checkForComodification();
			try {
				int i = cursor;
				AbstractList.this.add(i, e);
				lastRet = i;
				cursor = i + 1;
				expectedModCount = modCount;
			} catch (IndexOutOfBoundsException ex) {
				throw new ConcurrentModificationException();
			}
		}
		
	}
	
	public List<E> subList(int fromIndex, int toIndex) {
		return (this instanceof RandomAccess ? 
				new RandomAccessSubList<>(this, fromIndex, toIndex) :
				new SubList<>(this, fromIndex, toIndex));
	}
	
	//比较与hash
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof List)) {
			return false;
		}
		
		ListIterator<E> e1 = listIterator();
		ListIterator<?> e2 = ((List<?>) o).listIterator();
		while(e1.hasNext() && e2.hasNext()) {
			E o1 = e1.next();
			Object o2 = e2.next();
			if (!(o1==null ? o2 == null : o1.equals(o2))) {
				return false;
			}
		}
		return !(e1.hasNext() || e2.hasNext());
	}
	
	public int hashCode() {
		int hashCode = 1;
		for (E e : this) {
			hashCode = 31*hashCode + (e == null ? 0 : e.hashCode());
		}
		return hashCode;
	}

	protected void removeRange(int fromIndex, int toIndex) {
		ListIterator<E> it = listIterator(fromIndex);
		for (int i=0, n=toIndex-fromIndex; i<n ;i++) {
			it.next();
			it.remove();
		}
	}
	
	/**
	 * 这个List结构被修改的次数
	 * 结构修改主要指使List的大小发生改变的操作，或者其他会扰乱迭代器正确性的操作
	 * 
	 * modCount主要由itrotor()方法和listIteraot()方法返回的Iterator和ListIterator使用，
	 * 如果这个值发生了变化，迭代器在调用next, remove, previous, set 或 add方法时，就会抛ConcurrentModificationException异常
	 * 这提供了一种“fail-fast”快速失败的行为，而不是在迭代过程中面对并发修改的“non-deterministic”非确定性行为
	 * 
	 * fail-fast：
	 * 1、fail-fast机制是一种错误处理机制，用于多个线程对同一个集合内容操作时
	 * 2、迭代器的快速失败行为应该仅用于检测程序错误，不能用于运行时捕捉处理，因为在非同步并发修改状态下，fast-fail无法保证正确
	 * 
	 * 子类可以选择性使用此字段。
	 * 如果子类想实现fail-fast迭代器，就需要在所有修改List结构的方法中
	 * 将此字段加一，比如add和remove操作(不能多加，否则会抛出假的ConcurrentModificationException异常)。
	 * 如果子类不想实现fail-fast迭代器，则可以忽略这个字段
	 */
	protected transient int modCount = 0;

	private void rangeCheckForAdd(int index) {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
		}
	}

	private String outOfBoundsMsg(int index) {
		return "Index: " + index + ", Size: " + size();
	}
}

class SubList<E> extends AbstractList<E> {
	private final AbstractList<E> l; //父list
	private final int offset; //起始index
	private int size;//元素数量
	
	SubList(AbstractList<E> list, int fromIndex, int toIndex) {
		if (fromIndex < 0) {
			throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
		}
		if (toIndex > list.size()) {
			throw new IndexOutOfBoundsException("toIndex = " + toIndex);
		}
		if (fromIndex > toIndex) {
			throw new IllegalArgumentException("fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
		}
		l = list;
		offset = fromIndex;
		size = toIndex - fromIndex;
		this.modCount = l.modCount;
	}
	
	@Override
	public E set(int index, E element) {
		rangeCheck(index);
		checkForComodification();
		return l.set(index + offset, element);
	}

	@Override
	public E get(int index) {
		rangeCheck(index);
		checkForComodification();
		return l.get(index + offset);
	}

	@Override
	public int size() {
		checkForComodification();
		return size;
	}
	
	@Override
	public void add(int index, E element) {
		rangeCheckForAdd(index);
		checkForComodification();
		l.add(index + offset, element);
		this.modCount = l.modCount;
		size++;
	}
	
	@Override
	public E remove(int index) {
		rangeCheck(index);
		checkForComodification();
		E result = l.remove(index + offset);
		this.modCount = l.modCount;
		size--;
		return result;
	}
	
	@Override
	protected void removeRange(int fromIndex, int toIndex) {
		checkForComodification();
		l.removeRange(fromIndex + offset, toIndex + offset);
		this.modCount = l.modCount;
		size -= (toIndex - fromIndex);
	}
	
	@Override
	public boolean addAll(Collection<? extends E> c) {
		return addAll(size, c);
	}
	
	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		rangeCheckForAdd(index);
		int cSize = c.size();
		if (cSize == 0) {
			return false;
		}
		
		checkForComodification();
		l.addAll(offset + index, c);
		this.modCount = l.modCount;
		size += cSize;
		return true;
	}
	
	@Override
	public Iterator<E> iterator() {
		return listIterator();
	}
	
	@Override
	public ListIterator<E> listIterator(final int index) {
		checkForComodification();
		rangeCheckForAdd(index);
		
		return new ListIterator<E>() {
			private final ListIterator<E> i = l.listIterator(index + offset);

			@Override
			public boolean hasNext() {
				return nextIndex() < size;
			}

			@Override
			public E next() {
				if (hasNext()) {
					return i.next();
				} else {
					throw new NoSuchElementException();
				}
			}

			@Override
			public boolean hasPrevious() {
				return previousIndex() >= 0;
			}

			@Override
			public E previous() {
				if (hasPrevious()) {
					return i.previous();
				} else {
					throw new NoSuchElementException();
				}
			}

			@Override
			public int nextIndex() {
				return i.nextIndex() - offset;
			}

			@Override
			public int previousIndex() {
				return i.previousIndex() - offset;
			}

			@Override
			public void remove() {
				i.remove();
				SubList.this.modCount = l.modCount;
				size--;
			}

			@Override
			public void set(E e) {
				i.set(e);
			}

			@Override
			public void add(E e) {
				i.add(e);
				SubList.this.modCount = l.modCount;
				size++;
			}
			
		};
	}
	
	public List<E> subList(int fromIndex, int toIndex) {
		return new SubList<>(this, fromIndex, toIndex);
	}
	
	
	private void rangeCheck(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
		}
	}
	
	private void rangeCheckForAdd(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
		}
	}
	
	private String outOfBoundsMsg(int index) {
		return "Index: " + index + ", Size: " + size;
	}

	private void checkForComodification() {
		if (this.modCount != l.modCount) {
			throw new ConcurrentModificationException();
		}
	}
}

class RandomAccessSubList<E> extends SubList<E> implements RandomAccess {

	RandomAccessSubList(AbstractList<E> list, int fromIndex, int toIndex) {
		super(list, fromIndex, toIndex);
	}
	
	public List<E> subList(int fromIndex, int toIndex) {
		return new RandomAccessSubList<>(this, fromIndex, toIndex);
	}
	
}
