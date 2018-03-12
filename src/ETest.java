import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
class Student<C>{
	private String name;
	private Integer age;
	private Date date;
	private C result;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public C getSome(){
		return result;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Student(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean equals(Object obj) {
		System.out.println("equals");
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public int hashCode() {
		System.out.println("hashCode");
		// TODO Auto-generated method stub
		return 1111;
	}
	
}
public class ETest {
	public static Integer i;
	
	public static Integer getI() {
		return i;
	}
	public static void setI(Integer i) {
		ETest.i = i;
	}
	public void sq(){
		
	}
	public ETest(){
		
	}
	public static void add(Integer i){
		i++;
		i=10;
	}
	 //标准差σ=sqrt(s^2)  
    public static double getStandardDiviation(int[] x) {   
        int m=x.length;  
        double sum=0;  
        for(int i=0;i<m;i++){//求和  
            sum+=x[i];  
        }  
        double dAve=sum/m;//求平均值  
        System.out.println(dAve);
        double dVar=0;  
        for(int i=0;i<m;i++){//求方差  
            dVar+=(x[i]-dAve)*(x[i]-dAve);  
        }  
        return Math.sqrt(dVar/m);     
    }  
	public static void main(String[] args) throws Exception{
		String ss = "1111\n33333";
		System.out.println(ss);
//		int[] x = new int[]{65496 ,53774 ,42145 ,28777 ,51271 ,35145 ,30232 ,23120 ,19173 ,19480 ,18466 ,16693 ,16566 ,12037 ,4365};
//		System.out.println(getStandardDiviation(x));
		/*StopWatch sw = new StopWatch();
		sw.start("1");
		Thread.sleep(100);
		sw.stop();
		sw.start("2");
		Thread.sleep(500);
		sw.stop();
		System.out.println(sw.prettyPrint());*/
	/*	List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		System.out.println(list.contains(1));
		System.out.println(String.format("你好%s,%ss", "dd","ss"));*/
		/*Student<String> s = new Student<String>();
		String ssw = s.getSome();
		System.out.println(ssw);
		Set<String> set = new TreeSet<String>();
		List<Integer> list = new ArrayList<Integer>();
		list.add(3);
		list.add(5);
		list.add(1);
		Collections.sort(list);
		Integer i = 5;
		String ss = "ca";
		System.out.println(i.compareTo(45634645));*/
	/*	String ss = "dd";
		String ss1 = ss;
		System.out.println(ss);
		System.out.println(ss1);*/
//		list.remove(0);
//		list.add(1,"33");
		
	}
	public static void test(Map map,List list) throws Exception{
		System.out.println("before");
			list.get(0);
			map.size();
			System.out.println("after");
	}
}
