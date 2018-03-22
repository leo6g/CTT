package test.util;

public abstract class Constants {
	public interface MQ_QUEUE_NAME{
		String RABBITMQ_GRAB_GETSEAT="rabbitmq_grab_get_seat";
		String RABBITMQ_GRAB_CONFIRMTICKET="rabbitmq_grab_confirm_ticket";
		//mq队列名
	}
	public interface ORDER_STATE{
		//0是等待支付1占座成功 2是等待出票3是已出票4是正在抢票5是已取消
		String WAIT_PAY = "0";
		String GET_SEAT = "1";
		String WAIT_TICKET = "2";
		String SUCCESS_TICKET = "3";
		String GRABING_TICKET = "4";
		String CANCLE = "5";
	}
	
}
