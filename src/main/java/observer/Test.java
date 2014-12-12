package observer;

public class Test {
	public static void main(String[] args) {
		TObservable tble = new TObservable();
	
		
		TObserver ter = new TObserver();
		TObserver ter3 = new TObserver();
		TOberver2 ter2 = new TOberver2();
		tble.addObserver(ter);
		tble.addObserver(ter3);
		tble.addObserver(ter2);
		tble.setName("zh");
		tble.setId(1);
	}
}
