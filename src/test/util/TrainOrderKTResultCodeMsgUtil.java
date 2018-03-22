/**
 * 版权所有, 空铁无忧
 * Author: 火车票 H5-微信端 项目开发组
 * copyright: 2017
 */
package test.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className: com.ccservice.yunku.app.train.order.web.servlet.TrainOrderKTResultCodeMsgUtil
 * @description: TODO - 
 * @author: 郑州-技术-郭伟强   E-mail:gwq20521@163.com
 * @createTime: 2017年3月10日 下午1:50:38 
 * @version: v 1.0
 * @since 
 *
 */
public class TrainOrderKTResultCodeMsgUtil {

    public static List<Map<Integer, String>> getTrainOrderKTResultCodeMsg() {

        List<Map<Integer, String>> trainOrderKTResultCodeMsgList = new ArrayList<Map<Integer, String>>();

        Map<Integer, String> trainOrderKTResultCodeMsgSuccess = new HashMap<Integer, String>();

        //通用输出参数信息标识

        //success - true

        trainOrderKTResultCodeMsgSuccess.put(100, "处理或操作成功");

        trainOrderKTResultCodeMsgList.add(trainOrderKTResultCodeMsgSuccess);

        Map<Integer, String> trainOrderKTResultCodeMsgSoftwareError = new HashMap<Integer, String>();

        //参数传递错误，等程序错误
        //success - false

        trainOrderKTResultCodeMsgSoftwareError.put(101, "传入的json为空对象");
        trainOrderKTResultCodeMsgSoftwareError.put(102, "传入的json格式错误");
        trainOrderKTResultCodeMsgSoftwareError.put(103, "通用参数缺失");
        trainOrderKTResultCodeMsgSoftwareError.put(104, "账户无效");
        trainOrderKTResultCodeMsgSoftwareError.put(105, "错误的通用参数");
        trainOrderKTResultCodeMsgSoftwareError.put(106, "接口不存在");
        trainOrderKTResultCodeMsgSoftwareError.put(107, "业务参数缺失");
        trainOrderKTResultCodeMsgSoftwareError.put(108, "错误的业务参数");

        //下述几个值和信息无法判定是否是程序的错误，还是票务的错误 - 暂且留存 - 来自空铁接口文档

        //需要手动处理的程序逻辑错误
        //success - false

        //转移到 需要入库处理的位置

        //trainOrderKTResultCodeMsgSoftwareError.put(112, "订单状态不正确");//空铁自己的
        //在空铁确认出票中，相同的112代码提示了 - "该订单状态下，不能确认出票"
        //trainOrderKTResultCodeMsgSoftwareError.put(113, "当前时间不提供服务");//空铁自己的程序有错

        //trainOrderKTResultCodeMsgSoftwareError.put(402, "订单不存在");

        trainOrderKTResultCodeMsgSoftwareError.put(403, "账户余额不足");//占座的发起，需要先行判定 - 虚拟账户资金问题
        trainOrderKTResultCodeMsgSoftwareError.put(404, "账户金额被冻结");

        //在提交空铁订单的时候，返回了相关的新的错误信息

        //错误的身份信息在占座结果回调中也会出现999的相关信息 - 儿童票 - "code":999,"success":false,"msg":"1234566@三天假额他觉得特别打印机@添加失败"

        //trainOrderKTResultCodeMsgSoftwareError.put(999, "账户余额不足以支付此订单！！！");

        //在退票申请中多了这么一个应该抛出运行期异常的相关的信息
        trainOrderKTResultCodeMsgSoftwareError.put(116, "请求特征参数长度不符合要求");

        trainOrderKTResultCodeMsgList.add(trainOrderKTResultCodeMsgSoftwareError);

        //转移到 需要入库处理的位置

        Map<Integer, String> trainOrderKTResultCodeMsgBusinessError = new HashMap<Integer, String>();

        trainOrderKTResultCodeMsgBusinessError.put(112, "订单状态不正确");//空铁自己的
        //在空铁确认出票中，相同的112代码提示了 - "该订单状态下，不能确认出票"
        trainOrderKTResultCodeMsgBusinessError.put(113, "当前时间不提供服务");//空铁自己的程序有错

        trainOrderKTResultCodeMsgBusinessError.put(402, "订单不存在");

        trainOrderKTResultCodeMsgList.add(trainOrderKTResultCodeMsgBusinessError);

        return trainOrderKTResultCodeMsgList;
    }

    public static List<Map<Integer, String>> getTicketConfirmRebookResultCodeMsg() {

        List<Map<Integer, String>> trainTicketConfirmRebookResultCodeMsgList = new ArrayList<Map<Integer, String>>();

        //添加通用输出参数队列，以进行相关内容的比对
        trainTicketConfirmRebookResultCodeMsgList.addAll(TrainOrderKTResultCodeMsgUtil.getTrainOrderKTResultCodeMsg());

        //火车票业务信息参数标识

        //success - false

        Map<Integer, String> trainTicketConfirmRebookResultCodeMsgBusinessError = new HashMap<Integer, String>();

        trainTicketConfirmRebookResultCodeMsgBusinessError.put(401, "请求时间已超时，出票失败");

        //请求时间已超时是指没有在规定的时间内发出此出票请求
        trainTicketConfirmRebookResultCodeMsgBusinessError.put(1003, "确认改签的请求时间已超过规定的时间");

        trainTicketConfirmRebookResultCodeMsgList.add(trainTicketConfirmRebookResultCodeMsgBusinessError);

        return trainTicketConfirmRebookResultCodeMsgList;
    }

}
