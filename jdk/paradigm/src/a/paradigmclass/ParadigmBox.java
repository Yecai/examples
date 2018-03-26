package a.paradigmclass;

/**
 * 
 * @Description: 范型类
 */
public class ParadigmBox<T> {
	private T t;

	public T get() {
		return t;
	}

	public void set(T t) {
		this.t = t;
	}
	
	
	public static void main(String[] args) {
		//这样我们的Box类便可以得到复用，我们可以将T替换成任何我们想要的类型：
		
		ParadigmBox<String> sbox = new ParadigmBox<>();
		sbox.set("test");
		
		ParadigmBox<Integer> ibox = new ParadigmBox<>();
		ibox.set(123);
		
		ParadigmBox<Boolean> bbox = new ParadigmBox<>();
		bbox.set(false);
	}
}
