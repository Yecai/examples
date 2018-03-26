package a.paradigmclass;

/**
 * https://www.ziwenxie.site/2017/03/01/java-generic/
 * 简单的Box类,Box里面现在只能装入String类型的元素,今后如果我们需要装入Integer等其他类型的元素，还必须要另外重写一个Box，代码得不到复用，使用泛型可以很好的解决这个问题。
 */
public class SimpleBox {
	private String Object;

	public String get() {
		return Object;
	}

	public void set(String object) {
		Object = object;
	}
	
}
