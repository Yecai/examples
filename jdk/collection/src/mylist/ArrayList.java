package mylist;

import java.io.Serializable;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.RandomAccess;

//TODO overflow-conscious code test
//TODO batchRemove
public class ArrayList<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, Serializable {
	private static final long serialVersionUID = 8321611934533952814L;

	/**
	 * 默认初始容量
	 */
	private static final int DEFAULT_CAPACITY = 10;
	
	private static final Object[] EMPTY_ELEMENTDATA = {};
	
	/**
	 * 默认大小空实例
	 */
	private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
	
	/**
	 * ArrayList的元素存储数组。
	 * ArrayList的容量等于此数组的长度。
	 * 当添加第一个元素时，任何空ArrayList(elementData=DEFAULTCAPACITY_EMPTY_ELEMENTDATA)将被拓展到初始容量DEFAULT_CAPACITY
	 */
	transient Object[] elementData; //非私有，简化嵌套类访问
	
	/**
	 * ArrayList中元素数量
	 */
	private int size;
	
	/**
	 * 构建一个指定初始容量大小的空List
	 */
	public ArrayList(int initialCapacity) {
		if (initialCapacity > 0) {
			this.elementData = new Object[initialCapacity];
		} else if (initialCapacity == 0) {
			this.elementData = EMPTY_ELEMENTDATA;
		} else {
			throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
		}
	}
	/**
	 * 构建一个初始容量为10的空List
	 */
	public ArrayList() {
		this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
	}
	/**
	 * 构建一个包含指定集合对象的List
	 * 对象顺序由传入集合的迭代器返回顺序决定
	 */
	public ArrayList(Collection<? extends E> c) {
		elementData = c.toArray();
		if ((size = elementData.length) != 0) {
			//c.toArray可能（错误）没有返回Object[]（see 6260652）
			if (elementData.getClass() != Object[].class) {
				elementData = Arrays.copyOf(elementData, size, Object[].class);
			}
		} else {
			//替换空数组
			this.elementData = EMPTY_ELEMENTDATA;
		}
	}
	
	/**
	 * 将List容量大小缩小到元素大小
	 * 调用此方法可以减少List的占用空间
	 */
	public void trimToSize() {
		modCount++;
		if (size < elementData.length) {
			elementData = (size == 0)
				? EMPTY_ELEMENTDATA
				: Arrays.copyOf(elementData, size);
		}
	}
	
	/**
	 * 增加ArrayList的容量
	 * 尽量保证增加后的容量不小于传入的指定容量
	 */
	public void ensureCapacity(int minCapacity) {
		int minExpand = (elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA)
				//如果不是默认大小，则可以为任意大小
				? 0
				//默认大小数组
				: DEFAULT_CAPACITY;
		
		// overflow-conscious code
		if (minCapacity > minExpand) {
			ensureExplicitCapacity(minCapacity);
		}
	}
	
	private void ensureCapacityInternal(int minCapacity) {
		if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
			minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
		}
		ensureExplicitCapacity(minCapacity);
	}
	
	private void ensureExplicitCapacity(int minCapacity) {
		modCount++;
		if (minCapacity - elementData.length > 0) {
			grow(minCapacity);
		}
	}
	
	private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE;
	/**
	 * 拓展容量，能容纳下传入的最小容量
	 */
	private void grow(int minCapacity) {
		// overflow-conscious code
		int oldCapacity = elementData.length;
		int newCapacity = oldCapacity + (oldCapacity >> 1);
		if (newCapacity - minCapacity < 0) {
			newCapacity = minCapacity;
		}
		if (newCapacity - MAX_ARRAY_SIZE > 0) {
			newCapacity = hugeCapacity(minCapacity);
		}
		elementData = Arrays.copyOf(elementData, newCapacity);
	}
	
	private static int hugeCapacity(int minCapacity) {
		if (minCapacity < 0) {
			throw new OutOfMemoryError();
		}
		return (minCapacity > MAX_ARRAY_SIZE) ?
				Integer.MAX_VALUE :
				MAX_ARRAY_SIZE;
	}

	@Override
	public int size() {
		return size;
	}
	
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	@Override
	public boolean contains(Object o) {
		return indexOf(o) >= 0;
	}
	
	@Override
	public int indexOf(Object o) {
		if (o == null) {
			for (int i = 0; i < size; i++) {
				if (elementData[i] == null) {
					return i;
				}
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (o.equals(elementData[i])) {
					return i;
				}
			}
		}
		return -1;
	}
	
	@Override
	public int lastIndexOf(Object o) {
		if (o == null) {
			for (int i = size - 1; i >= 0; i--) {
				if (elementData[i] == null) {
					return i;
				}
			}
		} else {
			for (int i = size - 1; i <= 0; i--) {
				if (o.equals(elementData[i])) {
					return i;
				}
			}
		}
		return -1;
	}
	
	@Override
	protected Object clone() {
		try {
			ArrayList<?> v = (ArrayList<?>) super.clone();
			v.elementData = Arrays.copyOf(elementData, size);
			v.modCount = 0;
			return v;
		} catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable
			throw new InternalError();
		}
	}
	
	@Override
	public Object[] toArray() {
		return Arrays.copyOf(elementData, size);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] a) {
		if (a.length < size) {
			return (T[]) Arrays.copyOf(elementData, size, a.getClass());
		}
		System.arraycopy(elementData, 0, a, 0, size);
		if (a.length > size) {
			a[size] = null;
		}
		return a;
	}
	
	//位置访问操作
	
	@SuppressWarnings("unchecked")
	E elementData(int index) {
		return (E) elementData[index];
	}
	
	
	@Override
	public E get(int index) {
		rangeCheck(index);
		return elementData(index);
	}
	
	@Override
	public E set(int index, E element) {
		rangeCheck(index);
		
		E oldValue = elementData(index);
		elementData[index] = element;
		return oldValue;
	}
	
	@Override
	public boolean add(E e) {
		ensureCapacityInternal(size + 1);
		elementData[size++] = e;
		return true;
	}
	
	@Override
	public void add(int index, E element) {
		rangeCheckForAdd(index);
		
		ensureCapacityInternal(size + 1);
		System.arraycopy(elementData, index, elementData, index + 1, size - index);
		
		elementData[index] = element;
		size++;
	}
	
	@Override
	public E remove(int index) {
		rangeCheck(index);
		
		modCount++;
		E oldValue = elementData(index);
		
		int numMoved = size - index - 1;
		if (numMoved > 0) {
			System.arraycopy(elementData, index +1, elementData, index, numMoved);
		}
		elementData[--size] = null;
		return oldValue;
	}
	
	@Override
	public boolean remove(Object o) {
		if (o == null) {
			for (int i = 0; i < size; i++) {
				if (elementData[i] == null) {
					fastRemove(i);
					return true;
				}
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (o.equals(elementData[i])) {
					fastRemove(i);
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 内部remove方法，跳过了边界检查和移除值返回
	 */
	private void fastRemove(int index) {
		modCount++;
		int numMoved = size - index - 1;
		if (numMoved > 0) {
			System.arraycopy(elementData, index+1, elementData, index, numMoved);
		}
		elementData[--size] = null;
	}
	
	@Override
	public void clear() {
		modCount++;
		
		for (int i = 0; i < size; i++) {
			elementData[i] = null;
		}
		size = 0;
	}
	
	@Override
	public boolean addAll(Collection<? extends E> c) {
		Object[] a = c.toArray();
		int numNew = a.length;
		ensureCapacityInternal(size + numNew);
		System.arraycopy(a, 0, elementData, size, numNew);
		size += numNew;
		return numNew != 0;
	}
	
	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		rangeCheckForAdd(index);
		
		Object[] a = c.toArray();
		int numNew = a.length;
		ensureCapacityInternal(size + numNew); //modCount增加
		
		int numMoved = size - index;
		if (numMoved > 0) {
			System.arraycopy(elementData, index, elementData, index + numNew, numMoved);
		}
		System.arraycopy(a, 0, elementData, index, numNew);
		size += numNew;
		return numNew != 0;
	}
	
	@Override
	protected void removeRange(int fromIndex, int toIndex) {
		modCount++;
		int numMoved = size - toIndex;
		System.arraycopy(elementData, toIndex, elementData, fromIndex, numMoved);
		
		int newSize = size - (toIndex - fromIndex);
		for (int i = newSize; i < size ; i++) {
			elementData[i] = null;
		}
		size = newSize;
	}
	
	
	private void rangeCheck(int index) {
		if (index >= size) {
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
		}
	}
	private void rangeCheckForAdd(int index) {
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
		}
	}
	
	private String outOfBoundsMsg(int index) {
		return "Index: " + index + ", Size: " + size;
	}
	
	@Override
	public boolean removeAll(Collection<?> c) {
		Objects.requireNonNull(c);
		return batchRemove(c, false);
	}
	
	@Override
	public boolean retainAll(Collection<?> c) {
		Objects.requireNonNull(c);
		return batchRemove(c, true);
	}
	
	private boolean batchRemove(Collection<?> c, boolean complement) {
		final Object[] elementData = this.elementData;
		int r = 0, w = 0;
		boolean modified = false;
		try {
			for (; r < size; r++) {
				if (c.contains(elementData[r]) == complement) {
					elementData[w++] = elementData[r];
				}
			}
		} finally {
			if (r != size) {
				System.arraycopy(elementData, r, elementData, w, size - r);
				w += size - r;
			}
			if (w != size) {
				for (int i = w;i < size; i++) {
					elementData[i] = null;
				}
				modCount += size - w;
				size = w;
				modified = true;
			}
		}
		return modified;
	}
	
	/**
	 * 将ArrayList写入到流
	 * 序列化
	 */
	private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException {
		//Write out element count, and any hidden stuff
		int expectedModCount = modCount;
		s.defaultWriteObject();
		
		//Write out size as capacity for behavioural compatibility with clone()
		s.writeInt(size);
		
		// Write out all elements in the proper order
		for (int i = 0; i < size; i++) {
			s.writeObject(elementData[i]);
		}
		
		if (modCount != expectedModCount) {
			throw new ConcurrentModificationException();
		}
	}
	
	/**
	 * 从流中解析出ArrayList
	 * 反序列化
	 */
	private void readObject(java.io.ObjectInputStream s) throws ClassNotFoundException, java.io.IOException {
		elementData = EMPTY_ELEMENTDATA;
		
		//Read in size, and any hidden stuff
		s.defaultReadObject();
		
		//read in capacity
		s.readInt(); //ignored
		
		if (size > 0) {
			//与clone一样，根据size分配容量，而不是根据容量设置容量
			ensureCapacityInternal(size);
			
			Object[] a = elementData;
			// 顺序读取所有元素
			for (int i = 0; i < size; i++) {
				a[i] = s.readObject();
			}
		}
	}
	
	@Override
	public ListIterator<E> listIterator(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index: "+index);
		}
		return new ListItr(index);
	}
	
	@Override
	public ListIterator<E> listIterator() {
		return listIterator(0);
	}
	
	@Override
	public Iterator<E> iterator() {
		return new Itr();
	}
	
	/**
	 * AbstractList.Itr的优化版本
	 */
	private class Itr implements Iterator<E> {
		int cursor; //游标，指向next返回的元素
		int lastRet = -1; //游标，指向前一个返回的元素；如果没有则为-1
		int expectedModCount = modCount;
		
		@Override
		public boolean hasNext() {
			return cursor != size;
		}

		@SuppressWarnings("unchecked")
		@Override
		public E next() {
			checkForComodification();
			int i = cursor;
			if (i >= size) {
				throw new NoSuchElementException();
			}
			Object[] elementData = ArrayList.this.elementData;
			if (i >= elementData.length) {
				throw new ConcurrentModificationException();
			}
			cursor = i + 1;
			return (E) elementData[lastRet = i];
		}
		
		@Override
		public void remove() {
			if (lastRet < 0) {
				throw new IllegalStateException();
			}
			checkForComodification();
			
			try {
				ArrayList.this.remove(lastRet);
				cursor = lastRet;
				lastRet = -1;
				expectedModCount = modCount;
			} catch (IndexOutOfBoundsException e) {
				throw new ConcurrentModificationException();
			}
		}
		
		final void checkForComodification() {
			if (modCount != expectedModCount) {
				throw new ConcurrentModificationException();
			}
		}
		
	}
	
	private class ListItr extends Itr implements ListIterator<E> {
		ListItr(int index) {
			super();
			cursor = index;
		}
		
		@Override
		public boolean hasPrevious() {
			return cursor != 0;
		}

		@Override
		public int nextIndex() {
			return cursor;
		}

		@Override
		public int previousIndex() {
			return cursor - 1;
		}

		@SuppressWarnings("unchecked")
		@Override
		public E previous() {
			checkForComodification();
			int i = cursor - 1;
			if (i < 0) {
				throw new NoSuchElementException();
			}
			Object[] elementData = ArrayList.this.elementData;
			if (i > elementData.length) {
				throw new ConcurrentModificationException();
			}
			cursor = i;
			return (E) elementData[lastRet = i];
		}

		@Override
		public void set(E e) {
			if (lastRet < 0) {
				throw new IllegalStateException();
			}
			checkForComodification();
			
			try {
				ArrayList.this.set(lastRet, e);
			} catch (IndexOutOfBoundsException ex) {
				throw new ConcurrentModificationException();
			}
		}

		@Override
		public void add(E e) {
			checkForComodification();
			
			try {
				int i = cursor;
				ArrayList.this.add(i, e);
				cursor = i + 1;
				lastRet = -1;
				expectedModCount = modCount;
			} catch (IndexOutOfBoundsException ex) {
				throw new ConcurrentModificationException();
			}
		}
		
	}
	
	public List<E> subList(int fromIndex, int toIndex) {
		subListRangeCheck(fromIndex, toIndex, size);
		return new SubList(this, 0, fromIndex, toIndex);
	}
	private void subListRangeCheck(int fromIndex, int toIndex, int size2) {
		if (fromIndex < 0) {
			throw new IndexOutOfBoundsException("fromIndex= " + fromIndex);
		}
		if (toIndex > size) {
			throw new IndexOutOfBoundsException("toIndex= " + toIndex);
		}
		if (fromIndex > toIndex) {
			throw new IllegalArgumentException("fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
		}
	}
	
	private class SubList extends AbstractList<E> implements RandomAccess{
		private final AbstractList<E> parent;
		private final int parentOffset;
		private final int offset;
		int size;
		
		SubList(AbstractList<E> parent, int offset, int fromIndex, int toIndex) {
			this.parent = parent;
			this.parentOffset = fromIndex;
			this.offset = offset + fromIndex;
			this.size = toIndex - fromIndex;
			this.modCount = ArrayList.this.modCount;
		}
		
		@Override
		public E set(int index, E e) {
			rangeCheck(index);
			checkForComodification();
			E oldValue = ArrayList.this.get(offset + index);
			ArrayList.this.elementData[offset + index] = e;
			return oldValue;
		}
		
		@Override
		public E get(int index) {
			rangeCheck(index);
			checkForComodification();
			return ArrayList.this.elementData(offset + index);
		}

		@Override
		public int size() {
			checkForComodification();
			return this.size;
		}
		
		@Override
		public void add(int index, E e) {
			rangeCheckForAdd(index);
			checkForComodification();
			parent.add(parentOffset + index, e);
			this.modCount = parent.modCount;
			this.size++;
		}
		
		@Override
		public E remove(int index) {
			rangeCheck(index);
			checkForComodification();
			E result = parent.remove(parentOffset + index);
			this.modCount = parent.modCount;
			this.size--;
			return result;
		}
		
		@Override
		protected void removeRange(int fromIndex, int toIndex) {
			checkForComodification();
			parent.removeRange(parentOffset + fromIndex, parentOffset + toIndex);
			this.modCount = parent.modCount;
			this.size -= toIndex - fromIndex;
		}
		
		@Override
		public boolean addAll(Collection<? extends E> c) {
			return addAll(this.size, c);
		}
		
		@Override
		public boolean addAll(int index, Collection<? extends E> c) {
			rangeCheckForAdd(index);
			int cSize = c.size();
			if (cSize == 0) {
				return false;
			}
			
			checkForComodification();
			parent.addAll(parentOffset + index, c);
			this.modCount = parent.modCount;
			this.size += cSize;
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
			final int offset = this.offset;
			
			return new ListIterator<E>() {
				int cursor = index;
				int lastRet = -1;
				int expectedModCount = ArrayList.this.modCount;

				@Override
				public boolean hasNext() {
					return cursor != SubList.this.size;
				}

				@SuppressWarnings("unchecked")
				@Override
				public E next() {
					checkForComodification();
					int i = cursor;
					if (i > SubList.this.size) {
						throw new NoSuchElementException();
					}
					Object[] elementData = ArrayList.this.elementData;
					if (offset + i >= elementData.length) {
						throw new ConcurrentModificationException();
					}
					cursor = i + 1;
					return (E) elementData[offset + (lastRet = i)];
				}

				@Override
				public boolean hasPrevious() {
					return cursor != 0;
				}

				@SuppressWarnings("unchecked")
				@Override
				public E previous() {
					checkForComodification();
					int i = cursor -1;
					if (i < 0) {
						throw new NoSuchElementException();
					}
					Object[] elementData = ArrayList.this.elementData;
					if (offset + i >= elementData.length) {
						throw new ConcurrentModificationException();
					}
					cursor = i;
					return (E) elementData[offset + (lastRet = i)];
				}

				@Override
				public int nextIndex() {
					return cursor;
				}

				@Override
				public int previousIndex() {
					return cursor - 1;
				}

				@Override
				public void remove() {
					if (lastRet < 0) {
						throw new IllegalStateException();
					}
					checkForComodification();
					
					try {
						SubList.this.remove(lastRet);
						cursor = lastRet;
						lastRet = -1;
						expectedModCount = ArrayList.this.modCount;
					} catch (IndexOutOfBoundsException e) {
						throw new ConcurrentModificationException();
					}
				}
				
				@Override
				public void set(E e) {
					if (lastRet < 0) {
						throw new IllegalStateException();
					}
					checkForComodification();
					
					try {
						ArrayList.this.set(offset + lastRet, e);
					} catch (IndexOutOfBoundsException ex) {
						throw new ConcurrentModificationException();
					}
				}

				@Override
				public void add(E e) {
					checkForComodification();
					
					try {
						int i = cursor;
						SubList.this.add(i, e);
						cursor = i + 1;
						lastRet = -1;
						expectedModCount = ArrayList.this.modCount;
					} catch (IndexOutOfBoundsException ex) {
						throw new ConcurrentModificationException();
					}
					
				}
				
				final void checkForComodification() {
					if (expectedModCount != ArrayList.this.modCount) {
						throw new ConcurrentModificationException();
					}
				}
			};
		}
		
		public List<E> subList(int fromIndex, int toIndex) {
			subListRangeCheck(fromIndex, toIndex, size);
			return new SubList(this, offset, fromIndex, toIndex);
		}
		
		private void rangeCheck(int index) {
			if (index < 0 || index >= this.size) {
				throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
			}
		}
		private void rangeCheckForAdd(int index) {
			if (index < 0 || index > this.size) {
				throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
			}
		}
		
		private String outOfBoundsMsg(int index) {
			return "Index: " + index + ", Size: " + this.size;
		}
		
		private void checkForComodification() {
			if (ArrayList.this.modCount != this.modCount) {
				throw new ConcurrentModificationException();
			}
		}
	}
	
	
}
