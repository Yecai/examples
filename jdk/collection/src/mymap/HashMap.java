package mymap;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.Set;

/**
 * 实现了MAP接口，提供了所有可选操作，且允许null key和null value
 * HashMap与HashTable类似，只是非同步，且允许null值
 * HashMap对元素顺序是不保证顺序的
 * 
 * HashMap中基本操作（get和put）都是常量时间，前提是hash函数将元素均匀的在Buckets中分布
 * 迭代时间与map的容量以及键值对数量成正比。因此，如果迭代时间很重要，不要把HashMap的初始容量设置得太高（或负载因子太低）
 *
 * HashMap有两个影响性能的因素：初始容量和负载因子
 * 容量：hash表中的Buckets桶数量，初始容量是创建hash表时的容量
 * 负载因子：hash表在扩容之前，允许达到的最满的程度
 * 算法：当hash表中的元素数量，大于负载因子和当前容量的乘积时，hash表重新构建（内部数据结构全部重新构建），完成后hash表就有两倍的桶数量
 * 
 * 一般来说，默认的负载因子（0.75）能够在时间和空间中提供一个很好的平衡。
 * 值越高，能减少空间开销，但增加查找成本。
 * 在初始化容量时，需要考虑预期的条目数量和负载因子，以便减少hash表重新构建的次数
 * 
 * 如果有非常多的元素要放入HashMap，那么直接创建一个初始容量特别的的map能够使效率更高，而不是让HashMap根据元素数量自动扩容，会增加很多hash表重建操作
 * 注意：如果存储的大部分key具有相同的hashCode()值，HashMap的效率会降低。
 * 
 * 注意：HashMap不是同步的。如果有多线程并发访问,且同时多个线程修改map结构，必须做外部同步
 * （结构修改包括增加、删除键值对；仅仅修改已存在的key对应的value不是结构修改）
 * 如果不使用外部同步，可以使用Collections.synchronizedMap方法包装成同步map
 *     Map m = Collections.synchronizedMap(new HashMap(...));
 * 在并发修改下，这个map会体现出快速失败（fast-fail）机制，直接抛出ConcurrentModificationException异常，而不是使结构存在不确定性
 *
 */
//TODO hash
//TODO tableSizeFor
public class HashMap<K, V> extends AbstractMap<K, V> implements Map<K, V>, Cloneable, Serializable {

	private static final long serialVersionUID = -1615861806529452896L;
	
	/**
	 * 默认容量（必须是2的乘方）
	 */
	static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; //aka 16
	
	/**
	 * 最大容量
	 */
	static final int MAXIMUM_CAPACITY = 1 << 30;
	
	/**
	 * 默认负载因子
	 */
	static final float DEFAULT_LOAD_FACTOR = 0.75f;
	
	/**
	 * 桶中的元素组织结构，由List转换到Tree的最小数量。
	 * 桶中添加的元素数量大于这个值后，元素数据结构转换为tree保存
	 * 这个值应该大于2，最小为8，在删除元素到小于这个值时，元素恢复为list保存
	 */
	static final int TREEIFY_THRESHOLD = 8;
	static final int UNTREEIFY_THRESHOLD = 6;
	static final int MIN_TREEIFY_CAPACITY = 64;
	
	/**
	 * 基础hash bin节点
	 */
	static class Node<K, V> implements Map.Entry<K, V> {
		final int hash;
		final K key;
		V value;
		Node<K, V> next;
		
		public Node(int hash, K key, V value, Node<K, V> next) {
			this.hash = hash;
			this.key = key;
			this.value = value;
			this.next = next;
		}

		public final K getKey() { return key; }
		public final V getValue() { return value; }
		public final String toString() { return key + "=" + value; }
		
		@Override
		public final int hashCode() {
			return Objects.hashCode(key) ^ Objects.hashCode(value);
		}
		
		@Override
		public final V setValue(V newValue) {
			V oldValue = value;
			value = newValue;
			return oldValue;
		}
		
		@Override
		public boolean equals(Object o) {
			if (o == this) {
				return true;
			}
			if (o instanceof Map.Entry) {
				Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
				if (Objects.equals(key, e.getKey()) &&
					Objects.equals(value, e.getValue())) {
					return true;
				}
			}
			return false;
		}
		
	}
	
	/* ------------------ 静态公共方法 -------------------- */
	
	static final int hash(Object key) {
		int h;
		return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
	}
	/**
	 * 取x的可比较方法接口对象
	 */
	static Class<?> comparableClassFor(Object x) {
		if (x instanceof Comparable) {
			Class<?> c;Type[] ts, as;Type t; ParameterizedType p;
			if ((c = x.getClass()) == String.class) {//bypass checks
				return c;
			}
			if ((ts = c.getGenericInterfaces()) != null) {
				for (int i = 0; i < ts.length; ++i) {
					if (((t = ts[i]) instanceof ParameterizedType) &&
						((p = (ParameterizedType)t).getRawType() == Comparable.class) &&
						(as = p.getActualTypeArguments()) != null &&
						as.length == 1 &&
						as[0] == c) {
						return c;
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * 比较k和x是否相等
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	static int compareComparables(Class<?> kc, Object k, Object x) {
		return (x == null || x.getClass() != kc ? 0 :
			((Comparable)k).compareTo(x));
	}
	
	static final int tableSizeFor(int cap) {
		int n = cap - 1;
		n |= n >>> 1;
		n |= n >>> 2;
		n |= n >>> 4;
		n |= n >>> 8;
		n |= n >>> 16;
		return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
	}
	
	/* --------------- 属性 ----------------- */
	/**
	 * 第一次使用时初始化的表，并根据需求调整大小。
	 * 当分配时，大小总是2的幂
	 * （在某些操作中，也允许长度为零，以允许目前不需要的自举机制）
	 */
	transient Node<K, V>[] table;
	
	/**
	 * 保存缓存entrySet()
	 * 注意，AbstractMap的字段用于keySet()和values()方法
	 */
	transient Set<Map.Entry<K, V>> entrySet;
	
	/**
	 * map中键值对数量
	 */
	transient int size;
	
	/**
	 * HashMap发生结构性改变的次数
	 * 结构性改变指的是改变HashMap中元素个数或改变内部数据结构的操作
	 * 这个属性用于迭代器多线程下快速失败
	 */
	transient int modCount;
	
	/**
	 * 要调整大小的下一个大小值（容量*负载因子）
	 */
	int threshold;
	
	/**
	 * 负载因子
	 */
	final float loadFactor;
	
	/* --------------- 公开方法 ----------------- */
	/**
	 * 构造具有指定初始容量和负载因子的空HashMap
	 */
	public HashMap(int initialCapacity, float loadFactor) {
		if (initialCapacity < 0) {
			throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
		}
		if (initialCapacity > MAXIMUM_CAPACITY) {
			initialCapacity = MAXIMUM_CAPACITY;
		}
		if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
			throw new IllegalArgumentException("Illegal load factor" + loadFactor);
		}
		this.loadFactor = loadFactor;
		this.threshold = tableSizeFor(initialCapacity);
	}
	
	/**
	 * 构造具有指定初始容量和默认负载因子（0.75）的空HashMap
	 */
	public HashMap(int initialCapacity) {
		this(initialCapacity, DEFAULT_LOAD_FACTOR);
	}
	
	/**
	 * 构造具有默认初始容量（16）和默认负载因子（0.75）的空HashMap
	 */
	public HashMap() {
		this.loadFactor = DEFAULT_LOAD_FACTOR;
	}
	
	/**
	 * 构建新的HashMap装载旧map的键值对数据，
	 * 新HashMap默认负载因子（0.75），默认容量
	 */
	public HashMap(Map<? extends K, ? extends V> m) {
		this.loadFactor = DEFAULT_LOAD_FACTOR;
		putMapEntries(m, false);
	}
	
	final void putMapEntries(Map<? extends K, ? extends V> m, boolean evict) {
		int s = m.size()	;
		if (s > 0) {
			if (table == null) {
				float ft = ((float) s / loadFactor) + 1.0F;
				int t = ((ft < (float)MAXIMUM_CAPACITY) ? (int)ft : MAXIMUM_CAPACITY);
				if (t > threshold) {
					threshold = tableSizeFor(t);
				}
			} else if (s > threshold) {
				resize();
			}
			for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
				K key = e.getKey();
				V value = e.getValue();
				putVal(hash(key), key, value, false, evict);
			}
		}
	}
	
	final Node<K, V>[] resize() {
		Node<K, V>[] oldTab = table;
		int oldCap = (oldTab == null) ? 0 : oldTab.length;
		int oldThr = threshold;
		int newCap, newThr = 0;
		if (oldCap > 0) {
			if (oldCap >= MAXIMUM_CAPACITY) {
				threshold = Integer.MAX_VALUE;
				return oldTab;
			} else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY && 
					oldCap >= DEFAULT_INITIAL_CAPACITY) {
				newThr = oldThr << 1; // 阀值双倍
			}
		} else if (oldThr > 0) {
			newCap = oldThr; //初始容量设置为阀值
		} else { //初始阀值为0，代表使用默认设置
			newCap = DEFAULT_INITIAL_CAPACITY;
			newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
		}
		
		if (newThr == 0) {
			float ft = (float)newCap * loadFactor;
			newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
					 (int)ft : Integer.MAX_VALUE);
		}
		threshold = newThr;
		Node<K, V>[] newTab = (Node<K, V>[])new Node[newCap];
		table = newTab;
		if (oldTab != null) {
			for (int j = 0; j < oldCap; ++j) {
				Node<K, V> e;
				if ((e = oldTab[j]) != null) {
					oldTab[j] = null;
					if (e.next == null) {
						newTab[e.hash & (newCap - 1)] = e;
					} else if (e instanceof TreeNode) {
						((TreeNode<K, V>)e).split(this, newTab, j, oldCap);
					}
				}
			}
		}
	}
	
	@Override
	public Set<Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/* ------------------------------------------------------------ */
    // Tree bins
	
	static class LinkedEntry<K, V> extends Node<K, V> {
		Entry<K, V> before, after;
		public LinkedEntry(int hash, K key, V value, Node<K, V> next) {
			super(hash, key, value, next);
		}
	}
	static final class TreeNode<K, V> extends LinkedEntry<K, V> {
		TreeNode<K, V> parent; //红黑树的链接
		TreeNode<K, V> left;
		TreeNode<K, V> right;
		TreeNode<K, V> prev; //需要在删除后取消链接
		boolean red;
		public TreeNode(int hash, K key, V value, Node<K, V> next) {
			super(hash, key, value, next);
		}
		
		/**
		 * 返回包含此节点的树的根节点
		 */
		final TreeNode<K, V> root() {
			for (TreeNode<K, V> r = this, p;;) {
				if ((p = r.parent) == null) {
					return r;
				}
				r = p;
			}
		}
		
		
	}

}
