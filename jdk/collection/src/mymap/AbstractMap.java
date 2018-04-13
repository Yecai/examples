package mymap;

import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public abstract class AbstractMap<K, V> implements Map<K, V> {

	/**
	 * 唯一无参构造函数（用于子类构造函数调用，通常是隐式调用）
	 */
	protected AbstractMap() {
	}
	
	//查询操作
	
	@Override
	public int size() {
		return entrySet().size();
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public boolean containsValue(Object value) {
		Iterator<Entry<K, V>> i = entrySet().iterator();
		if (value == null) {
			while (i.hasNext()) {
				Entry<K, V> e = i.next();
				if (e.getValue() == null) {
					return true;
				}
			}
		} else {
			while (i.hasNext()) {
				Entry<K, V> e = i.next();
				if (value.equals(e.getValue())) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean containsKey(Object key) {
		Iterator<Entry<K, V>> i = entrySet().iterator();
		if (key == null) {
			while (i.hasNext()) {
				Entry<K, V> e = i.next();
				if (e.getKey() == null) {
					return true;
				}
			}
		} else {
			while (i.hasNext()) {
				Entry<K, V> e = i.next();
				if (key.equals(e.getKey())) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public V get(Object key) {
		Iterator<Entry<K, V>> i = entrySet().iterator();
		if (key == null) {
			while (i.hasNext()) {
				Entry<K, V> e = i.next();
				if (e.getKey() == null) {
					return e.getValue();
				}
			}
		} else {
			while (i.hasNext()) {
				Entry<K, V> e = i.next();
				if (key.equals(e.getKey())) {
					return e.getValue();
				}
			}
		}
		return null;
	}
	
	//修改操作

	@Override
	public V put(K key, V value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public V remove(Object key) {
		Iterator<Entry<K, V>> i = entrySet().iterator();
		Entry<K, V> correctEntry = null;
		if (key == null) {
			while (correctEntry == null && i.hasNext()) {
				Entry<K, V> e = i.next();
				if (e.getKey() == null) {
					correctEntry = e;
				}
			}
		} else {
			while (correctEntry == null && i.hasNext()) {
				Entry<K, V> e = i.next();
				if (key.equals(e.getKey())) {
					correctEntry = e;
				}
			}
		}
		
		V oldValue = null;
		if (correctEntry != null) {
			oldValue = correctEntry.getValue();
			i.remove();
		}
		return oldValue;
	}
	
	//批量操作

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
			put(e.getKey(), e.getValue());
		}
	}

	@Override
	public void clear() {
		entrySet().clear();
	}
	
	//查看操作
	transient Set<K> keySet;
	transient Collection<V> values;
	
	public Set<K> keySet() {
		Set<K> ks = keySet;
		if (ks == null) {
			ks = new AbstractSet<K>() {
				@Override
				public Iterator<K> iterator() {
					return new Iterator<K>() {
						private Iterator<Entry<K, V>> i = entrySet().iterator();
						@Override
						public boolean hasNext() {
							return i.hasNext();
						}

						@Override
						public K next() {
							return i.next().getKey();
						}
						
						@Override
						public void remove() {
							i.remove();
						}
					};
				}

				@Override
				public int size() {
					return AbstractMap.this.size();
				}
				
				@Override
				public boolean isEmpty() {
					return AbstractMap.this.isEmpty();
				}
				
				@Override
				public void clear() {
					AbstractMap.this.clear();
				}
				
				@Override
				public boolean contains(Object k) {
					return AbstractMap.this.containsKey(k);
				}
			};
			keySet = ks;
		}
		return ks;
	}

	@Override
	public Collection<V> values() {
		Collection<V> vals = values;
		if (vals == null) {
			vals = new AbstractCollection<V>() {

				@Override
				public Iterator<V> iterator() {
					return new Iterator<V>() {
						private Iterator<Entry<K, V>> i = entrySet().iterator();
						@Override
						public boolean hasNext() {
							return i.hasNext();
						}

						@Override
						public V next() {
							return i.next().getValue();
						}
						
						@Override
						public void remove() {
							i.remove();
						}
					};
				}

				@Override
				public int size() {
					return AbstractMap.this.size();
				}
				
				@Override
				public boolean isEmpty() {
					return AbstractMap.this.isEmpty();
				}
				
				@Override
				public void clear() {
					AbstractMap.this.clear();
				}
				
				@Override
				public boolean contains(Object v) {
					return AbstractMap.this.containsValue(v);
				}
			};
			values = vals;
		}
		return vals;
	}

	public abstract Set<Entry<K, V>> entrySet();

	//比较和hash
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		
		if (!(o instanceof Map)) {
			return false;
		}
		Map<?,?> m = (Map<?,?>) o;
		if (m.size() != size()) {
			return false;
		}
		
		try {
			Iterator<Entry<K, V>> i = entrySet().iterator();
			while (i.hasNext()) {
				Entry<K, V> e = i.next();
				K key = e.getKey();
				V value = e.getValue();
				if (value == null) {
					if (!(m.get(key)==null && m.containsKey(key))) {
						return false;
					}
				} else {
					if (!value.equals(m.get(key))) {
						return false;
					}
				}
			}
		} catch (ClassCastException unused) {
			return false;
		} catch (NullPointerException unused) {
			return false;
		}
		return true;
	}
	
	public int hashCode() {
		int h = 0;
		Iterator<Entry<K, V>> i = entrySet().iterator();
		while (i.hasNext()) {
			h += i.next().hashCode();
		}
		return h;
	}
	
	public String toString() {
		Iterator<Entry<K, V>> i = entrySet().iterator();
		if (!i.hasNext()) {
			return "{}";
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for(;;) {
			Entry<K, V> e = i.next();
			K key = e.getKey();
			V value = e.getValue();
			sb.append(key == this ? "(this Map)" : key);
			sb.append("=");
			sb.append(value == this ? "(this Map)" : value);
			if (!i.hasNext()) {
				return sb.append("}").toString();
			}
			sb.append(",").append(" ");
		}
	}
	
	protected Object clone() throws CloneNotSupportedException {
		AbstractMap<?, ?> result = (AbstractMap<?, ?>)super.clone();
		result.keySet = null;
		result.values = null;
		return result;
	}
	
	/**
	 * SimpleEntry和SImpleImmutableEntry使用的工具类
	 * 比较两个对象是否相等
	 */
	private static boolean eq(Object o1, Object o2) {
		return o1 == null ? o2 == null : o1.equals(o2);
	}
	
	/**
	 * 简单的键值对Entry实现
	 */
	public static class SimpleEntry<K, V>
		implements Entry<K, V>, java.io.Serializable {
		private static final long serialVersionUID = 281522320774611612L;

		private final K key;
		private V value;
		
		public SimpleEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		public SimpleEntry(Entry<? extends K, ? extends V> entry) {
			this.key = entry.getKey();
			this.value = entry.getValue();
		}
		
		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public V setValue(V value) {
			V oldValue = value;
			this.value = value;
			return oldValue;
		}
		
		@Override
		public boolean equals(Object o) {
			if (!(o instanceof Map.Entry)) {
				return false;
			}
			Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
			return eq(key, e.getKey()) && eq(value, e.getValue());
		}
		
		@Override
		public int hashCode() {
			return (key == null ? 0 : key.hashCode()) ^
					(value == null ? 0 : value.hashCode());
		}
		
		@Override
		public String toString() {
			return key + "=" + value;
		}
		
	}
	
	/**
	 * key和value不可改变的键值对对象
	 * 不能调用setValue方法，会抛出异常
	 */
	public static class SimpleImmutableEntry<K, V>
		implements Entry<K, V>, java.io.Serializable
	{
		private static final long serialVersionUID = 3654749575543217875L;

		private final K key;
		private final V value;
		
		public SimpleImmutableEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		public SimpleImmutableEntry(Entry<? extends K, ? extends V> entry) {
			this.key = entry.getKey();
			this.value = entry.getValue();
		}
		
		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public V setValue(V value) {
			throw new UnsupportedOperationException();
		}
		
		@Override
		public boolean equals(Object o) {
			if (!(o instanceof Map.Entry)) {
				return false;
			}
			Map.Entry<?, ?> e = (Map.Entry<?, ?>)o;
			return eq(key, e.getKey()) && eq(value, e.getValue());
		}
		
		@Override
		public int hashCode() {
			return (key == null ? 0 : key.hashCode()) ^
					(value == null ? 0 : value.hashCode());
		}
		
		@Override
		public String toString() {
			return key + "=" + value;
		}
		
	}
	
}
