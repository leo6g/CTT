import java.util.ArrayList;
import java.util.List;

public class UtilTest {
	public static void main(String[] args) {
		
		String needDelCitys = "南京@泰兴@宝应@沭阳@泗阳@连云港@杭州@萧山@宁波@温岭市@天台@仙居@温州@苍南@乐清@乐清商旅@瑞安市@平阳@桐乡市@嘉善县@嘉兴市@海盐县@绍兴市@嵊州市@新昌市@永康市@浦江县@开化@江山@龙游县@安吉";
		String originCitys = "南京市@宝应县@靖江市@涟水县@泰兴市@沭阳县@泗阳县@连云港市@宁波市@宁海市@温岭市@玉环县@天台县@仙居县@温州市@瑞安市@平阳@苍南县@乐清市@湖州@安吉县@海盐@濮院镇@崇福镇@乌镇@嘉善县@嘉兴市@绍兴市@嵊州市@新昌市@丽水市@松阳县@义乌市@永康市@浦江县@衢州市@开化县@江山市@龙游县@常山市@池州@宣城市@广德县@郎溪县@旌德@泾县@绩溪@马鞍山@马鞍山@当涂@和县@濉溪@庐江@含山@巢湖市@歙县@祁门县@休宁@黄山区@黄山@徽州区@黟县@芜湖@芜湖@芜湖@芜湖@湾沚@繁昌@南陵@无为@蚌埠市@蚌埠市@怀远@阜阳@阜阳@阜南@界首@六安@六安@六安@金寨@霍邱@寿县@宿州市@砀山@淮北市@凤台@淮南@福州@厦门@泉州@莆田@三明@南平@宁德@龙岩@益阳市@郴州市@岳阳市@长沙@浏阳@遂宁市@甘孜自治州@射洪县@大英@江油市@南宁市@崇左市@贵港@融安@桂林@城南@昭平@贺州@柳城@武鸣@富川@凭祥县@北海市@防城港市@来宾市@金秀县@象州县@合山县@忻城县@桐木县@来宾市@玉林市@百色市@柳州市@钦州市@荆州市@枣阳市@浠水@远安@汉川@赤壁@云梦@崇阳市@公安县@襄阳市@宜昌市@宜都@古夫镇@黄冈市@湖北监利@枝江市@蕲春县@仙桃@阳新县@随州@广水@孝感@安陆@嘉鱼@罗田县@咸宁市@武穴市@恩施市@渔洋关@奉节县@汉中@西安@渭南@延安@安康@咸阳@铜川@宝鸡@商洛@平凉市@庆阳市@陇南市@天水市@武威市@张掖@白银@定西市@嘉峪关@潍坊@淄博@沂源@诸城@临朐@滨州@威海@荣成@临沂@烟台@济南@日照@德州@平原@禹城@聊城@安丘@寿光@洛阳市@南阳市@许昌市@开封市@鹤壁市@濮阳@焦作@商丘@宋城@驻马店@安阳@周口市@平顶山@长垣县@郸城县@沈丘县@太康县@西华县@扶沟县@鹿邑县@淮阳县@项城市@信阳市@光山县@新安县@孟津县@新乡@固始县@三门峡@罗山县@息县@潢川县@商城县@淮滨县@翼城县@洪洞县@朔州市@阳泉市@石楼县@昔阳县@銀川@吴忠市@大武口@石嘴山@固原市@灵武市@盐池县@同心县@惠农区@平罗@海原县@西吉县@宁武县@中卫市@西宁市@共和@西宁市@北京@天津@沧州@邯郸@衡水@邢台@保定@承德@张家口@秦皇岛@唐山@大庆@鹤岗@齐齐哈尔@鄂尔多斯@包头@呼和浩特@巴彦淖尔@乌海@赤峰@赣州@瑞金@宜春@高安@丰城@上高@铜鼓@樟树@宜丰@奉新@靖安@张家口@海口@琼海市@三亚市@儋州市@东方市@湛江市@惠州市@珠海市@茂名市@深圳市@广州市";
		String[] originCitysArr = originCitys.split("@");
		String[] needDelCitysArr = needDelCitys.split("@");
		List<String> needDelCityList = new ArrayList<String>();
		for(int i=0;i<needDelCitysArr.length;i++) {
			needDelCityList.add(needDelCitysArr[i]);
		}
		StringBuffer sb = new StringBuffer();
		int j = 0;
		StringBuffer sb1 = new StringBuffer();
		for(int i=0;i<originCitysArr.length;i++) {
			if(!needDelCityList.contains(originCitysArr[i])) {
				sb.append(originCitysArr[i]+"@");
				j++;
			}
		}
		System.out.println("原始城市数量"+originCitysArr.length);
		System.out.println("去除后城市数量"+j);
		System.out.println("结果："+sb.toString());
		
	}
}