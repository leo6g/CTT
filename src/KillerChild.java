import java.util.List;

public class KillerChild implements Killer{
	public static String ss2 ="ds";
	public static List<String> depCityList;
	public static Integer num = 2;
	
	@Override
	public void sayHello() {
		// TODO Auto-generated method stub
		
	}
	public KillerChild(){
		this.num = num++;
	}
	
	public KillerChild(Integer num) {
		super();
		this.num = num;
	}

	public  String getSs(){
		return this.ss2;
	}
}
