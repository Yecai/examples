package a.paradigmclass;

/**
 * 
 * @Description: ������
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
		//�������ǵ�Box�����Եõ����ã����ǿ��Խ�T�滻���κ�������Ҫ�����ͣ�
		
		ParadigmBox<String> sbox = new ParadigmBox<>();
		sbox.set("test");
		
		ParadigmBox<Integer> ibox = new ParadigmBox<>();
		ibox.set(123);
		
		ParadigmBox<Boolean> bbox = new ParadigmBox<>();
		bbox.set(false);
	}
}
