package observer;

import java.util.Observable;
import java.util.Observer;

public class TObserver implements Observer{

	public void update(Observable o, Object arg) {
		System.out.println("change happened:"+o.getClass()+","+arg);
		
	}

}
