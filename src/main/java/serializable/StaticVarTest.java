package serializable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class StaticVarTest implements Serializable{
	
	public static int a = 5;
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("d:/s.txt"));
		out.writeObject(new StaticVarTest());
		a = 10;
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("d:/s.txt"));
		StaticVarTest svt = (StaticVarTest) ois.readObject();
		System.out.println(svt.a);
	}
}
