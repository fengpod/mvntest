package single;

public class Test {
	private static Test t = new Test();
	private Test(){}
	public static Test getT(){
		return t;
	}
	public static void main(String[] args) {
		getT();
	}
}
