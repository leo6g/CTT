package test.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * @className: com.ccservice.yunku.util.CommonUtils
 * @description: TODO - 通用工具类
 * @author: 郑州-技术-郭伟强   E-mail:gwq20521@163.com
 * @createTime: 2017年3月3日 下午2:09:41 
 * @version: v 1.0
 * @since 
 *
 */
public class CommonUtils {
	
	//0占座中 1是等待支付 2是等待出票3是已出票4是正在抢票5是已取消
	public static String orderState_getSeat = "0";
	public static String orderState_waitPay = "1";
	public static String orderState_waitTicket = "2";
	public static String orderState_successTicket = "3";
	public static String orderState_grabTicket = "4";
	public static String orderState_cancle = "5";

    /**
     * 订单号
     * @return
     */
    public static String timeMinID() {
        Random random = new Random();
        int rannum = (int) (random.nextDouble() * (999 - 100 + 1)) + 100;// 获取3位随机数  
        return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + rannum;
    }



    /**
     * 转换字符串为java.util.Date
     * @return
     */
    public static Date strToDate(String datetime) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(datetime);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 转换字符串为java.util.Date - Birthday - yyyy-MM-dd - 数据库获取的是字符串，没有这个date类型
     * @return
     */
    public static Date strToBirthday(String datetime) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(datetime);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 转换字符串为java.sql.Timestamp
     * @return
     */
    public static Timestamp strToTimestamp(String datetime) {
        try {
            Date d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(datetime);
            return new Timestamp(d.getTime());
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 转换字符串为java.util.Date
     * @return
     */
    public static Date getNowDate() {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
                    .parse(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 转换yyyy-MM-dd字符串为 M月d日
     * @return
     */
    public static String strToMonthDate(String datetime) {
        Date Date = null;
        try {
            Date = new SimpleDateFormat("yyyy-MM-dd").parse(datetime);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Date);
        return calendar.get(Calendar.MONTH) + 1 + "月" + calendar.get(Calendar.DAY_OF_MONTH) + "日";
    }
    
    
    /**
     * 转换hh:mm - h:m
     * @return
     */
    public static String strToHourMinute(String datetime) {
        Date Date = null;
        try {
            Date = new SimpleDateFormat("hh:mm").parse(datetime);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Date);
        return calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);
    }
    
    /**
     * 12车厢,005座 转换为 12车5号
     * @return
     */
    public static String getCoachSeatNo(String CoachSeatNo) {
        return CoachSeatNo.replace("厢,", "").replace("0", "").replace("座", "号");
    }

	private static String convertSeatType(String seatType) {
    	String code = "";
    	switch (seatType) {
		case "ydz":
			code="15";
			break;
		case "一等座":
			code="15";
			break;
		case "edz":
			code="16";
			break;
		case "二等座":
			code="16";
			break;
		case "tdz":
			code="17";
			break;
		case "特等座":
			code="17";
			break;
		case "yz":
			code="1";
			break;
		case "硬座":
			code="1";
			break;
		case "rz":
			code="2";
			break;
		case "软座":
			code="2";
			break;
		case "yw":
			code="3";
			break;
		case "硬卧":
			code="3";
			break;
		case "rw":
			code="4";
			break;
		case "软卧":
			code="4";
			break;
		case "gjrw":
			code="6";
			break;
		case "高级软卧":
			code="6";
			break;
		case "swz":
			code="9";
			break;
		case "商务座":
			code="9";
			break;
		case "wz":
			code="0";
			break;
		case "无座":
			code="0";
			break;
		default:
			System.out.println("座席类型匹配错误 seatType="+seatType);
			break;
		}
    	return code;
    }
	
    public static String getSeatTypeCode(String seatTypes) {
    	StringBuilder codeSb = new StringBuilder();
    	String[] seatTypeArr = seatTypes.split(",");
    	for(String seatType :seatTypeArr) {
    		String code = convertSeatType(seatType);
    		if("".equals(code)) {
    			return "";
    		}
    		codeSb.append(code+",");
    	}
    	return codeSb.substring(0, codeSb.length()-1);
    }
    /**
     * @param partnerid
     * @param method
     * @param reqtime
     * @param key
     * @return
     */
    public static String getTrainSubmitKTSign(String partnerid, String method, String reqtime, String key) {
        String sign = "";
        try {
            sign = MD5Util.MD5(partnerid + method + reqtime + MD5Util.MD5(key));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return sign;
    }
    
    public static String SeatTypeShowName(String SeatType) {
        switch (SeatType) {
        case "0":
            return "无座";
        case "1":
            return "硬座";
        case "2":
            return "软座";
        case "3":
            return "硬卧";
        case "31":
            return "硬卧上";
        case "32":
            return "硬卧中";
        case "33":
            return "硬卧下";
        case "34":
            return "软卧";
        case "35":
            return "软卧上";
        case "36":
            return "软卧下";
        case "6":
            return "高级软卧";
        case "61":
            return "高级软卧上";
        case "63":
            return "高级软卧下";
        case "9":
            return "商务座";
        case "15":
            return "一等座";
        case "16":
            return "二等座";
        case "17":
            return "特等座";
        }
        return "展示席别类型异常，请检查相关字段值";
    }
    
    
}
