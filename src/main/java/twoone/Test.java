package twoone;

public class Test {
	
	public static void main(String[] args) {
		twoBase();
	}
	static void twoBase(){
		for(int i = 0 ;i<10;i++){
			for(int j = 0;j<10;j++){
				for(int k = 0 ;k<10;k++){
					int xyz = Integer.parseInt(i+""+j+""+k);
					int zyx = Integer.parseInt(k+""+j+""+i);
					if(Math.pow(10, xyz) == Math.pow(9, zyx)){
						System.out.println(Math.pow(10, xyz));
						System.out.println(Math.pow(9, zyx));
						System.out.println(i+" "+j+" "+k);
					}
				}
			}
		}
	}
}
