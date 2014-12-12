package serializable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectInputStream.GetField;
import java.io.ObjectOutputStream;
import java.io.ObjectOutputStream.PutField;
import java.io.Serializable;

public class Child extends Parent implements Serializable{
	private int b;
	private String password;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setB(int b) {
		this.b = b;
	}
	public int getB() {
		return b;
	}
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		Child child = new Child();
		child.setA(1);
		child.setB(2);
		child.setPassword("zhrs");
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("d:/s.txt"));
		oos.writeObject(child);
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("d:/s.txt"));
		Child child2 = (Child) ois.readObject();
		System.out.println(child2.getA());
		System.out.println(child2.getB());
		System.out.println(child2.getPassword());
	}
	
	private void writeObject(ObjectOutputStream out) throws IOException{
		PutField fields = out.putFields();
		fields.put("password",this.getPassword()+"123");
		fields.put("b", this.getB());
		out.writeFields();
	}
	
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
		GetField fields = in.readFields();
		Object obj = fields.get("password", "");
		password = obj.toString();
		b = fields.get("b", 0);
	}
}
