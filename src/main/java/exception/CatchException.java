package exception;

public class CatchException {
	public static void main(String[] args) throws Exception {
		int a = 0;
		int b =1;
		try {
			int c = b/a;
		} catch (Exception e) {
			throw new Exception(e);
		}finally{
			System.out.println("finally");
		}
	}
}
