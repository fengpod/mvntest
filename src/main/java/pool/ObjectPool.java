package pool;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.google.common.collect.Sets;

public class ObjectPool {
	private int minNum = 10;
	private int maxNum = 50;
	private Set set = null;
	
	public ObjectPool(){
		
	}
	
	public synchronized void createPool(){
		if(set == null){
			return;
		}
		set = Sets.newHashSet();
		for(int i = 0 ;i<minNum;i++){
			Object obj = new Object();
			set.add(obj);
		}
	}
	
	public synchronized Object getObject(){
		if(set == null){
			return null;
		}
		Object obj = getFreeObject();
		while(obj == null){
			wait(500);
			obj = getFreeObject();
		}
		return obj;
	}
	private void wait(int seconds){
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	private Object getFreeObject() {
		Object obj = findFreeObject();
		if(obj == null){
			createObjects(minNum);
			obj = findFreeObject();
			if(obj == null){
				return null;
			}
		}
		return obj;
	}

	private void createObjects(int minNum) {
		for(int i = 0;i<minNum;i++){
			Object obj = new Object();
			set.add(obj);
		}
	}

	private Object findFreeObject() {
		Object object = null;
		PooledObject pObj = null;
		
		for(Object obj : set){
			pObj = (PooledObject) obj;
			if(!pObj.isBusy()){
				object = pObj.getObject();
				pObj.setBusy(true);
			}
		}
		return object;
	}
	public void returnObject(Object obj){
		if(set == null){
			return;
		}
		PooledObject pObj = null;
		for(Object object : set){
			pObj = (PooledObject) object;
			if(obj == pObj.getObject()){
				pObj.setBusy(false);
				break;
			}
		}
	}
	
	public synchronized void closePool(){
		if(set == null){
			return;
		}
		PooledObject pObj = null;
		for(Object obj : set){
			pObj = (PooledObject) obj;
			if(pObj.isBusy()){
				wait(5000);
			}
			set.remove(obj);
		}
		set = null;
	}
	 public static void main(String[] args) throws Exception {     
	        ObjectPool objPool = new ObjectPool();
	        objPool.createPool();     
	        Object obj = objPool.getObject();     
	        objPool.returnObject(obj);
	        objPool.closePool();     
	    }   
	
}
class PooledObject{
	Object obj = null;
	boolean busy = false;
	
	public PooledObject(Object obj){
		this.obj = obj;
	}
	public Object getObject(){
		return obj;
	}
	public void setObject(Object obj){
		this.obj = obj;
	}
	public boolean isBusy(){
		return busy;
	}
	public void setBusy(boolean busy){
		this.busy = busy;
	}
}
