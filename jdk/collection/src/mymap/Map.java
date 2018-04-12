package mymap;

import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Set;

public interface Map<K, V> {
	//查询操作
	
	/**
	 * 返回map中key-value对的数量
	 * 如果数量大于Integer.MAX_VALUE，返回Integer.MAX_VALUE
	 * @return map中key-value对的数量
	 */
	int size();
	
	/**
	 * 如果map中一个key-value对都没有，返回true
	 * 
	 * @return true:map为空，没有一个key-value对
	 */
	boolean isEmpty();
	
	/**
	 * 是否包含指定key，如果map中包含指定的key，返回true
	 * 
	 * @return map是否包含指定key
	 */
	boolean containsKey(Object key);
	
	/**
	 * 判断map是否包含指定value
	 * 
	 * @return 是否包含指定value
	 */
	boolean containsValue(Object value);
	
	/**
	 * 返回指定key对应的value，如果没有对应的key，返回null
	 * 
	 * @return
	 */
	V get(Object key);
	
	//修改操作
	/**
	 * 将指定的key-value对存储到map，如果map中已经存在该key，则新的value将会覆盖旧的value
	 * 
	 * @return 该key对应的旧value，如果原来没有这个key，返回null
	 */
	V put(K key, V value);
	
	/**
	 * 从map中移除key对应的key-value对，返回旧的value值，如果map中没有对应的key，返回null
	 * 
	 * @return
	 */
	V remove(Object key);
	
	//批量操作
	
	/**
	 * 将指定m中的所有key-value对拷贝到当前map中
	 */
	void putAll(Map<? extends K, ? extends V> m);
	
	/**
	 * 清空map中的所有键值对
	 */
	void clear();
	
	/**
	 * 以Set形式返回map中所有的key
	 * 该Set值与map相互关联，map的修改会映射到set，反之亦然
	 * @return map中所有的key组成的set
	 */
	Set<K> keySet();
	
	/**
	 * 返回所有value，以collection形式
	 * 该collection与map关联，对map的操作会映射到collection
	 * 
	 * @return map中所有map组成的collection
	 */
	Collection<V> values();
	
	Set<Map.Entry<K, V>> entrySet();
	
	/**
	 * map键值对对象
	 */
	interface Entry<K, V> {
		 /**
		  * 返回key
		  */
		 K getKey();
		 /**
		  * 返回value
		  */
		 K getValue();
		 /**
		  * 替换原value为指定的value
		  * 
		  * @return 原value
		  */
		 V setValue(V value);
		 
		 boolean equals(Object o);
		 
		 int hashCode();
		 
		 public static <K extends Comparable<? super K>, V> Comparator<Map.Entry<K, V>> comparingByKey() {
			 return (Comparator<Map.Entry<K, V>> & Serializable) 
				(c1, c2) -> c1.getKey().compareTo(c2.getKey());
		 }
	}
}
