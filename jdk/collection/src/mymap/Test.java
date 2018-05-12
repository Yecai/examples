package mymap;

public class Test {
	public static void main(String[] args) {
		for(int i=1;i<20;i++) {
			hash(i);
		}
	}
	
	public static void hash(Object o) {
		int h = o.hashCode();
		int a = o.hashCode() & 15;
		System.out.print("a=" + a + ",");
		System.out.print("h="+h + ",");
		int l = h >>> 16;
		System.out.print("l="+l+",");
		System.out.println("h^l="+(h ^ l));
	}
}
