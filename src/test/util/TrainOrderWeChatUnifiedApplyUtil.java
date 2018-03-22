/**
 * 版权所有, 空铁无忧
 * Author: 火车票 H5-微信端 项目开发组
 * copyright: 2017
 */
package test.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
/**
 * @className: com.ccservice.yunku.app.train.order.web.servlet.TrainOrderWeChatUnifiedApplyUtil
 * @description: TODO - 抽取微信下单可复用的方法
 *  
 * @author: 郑州-技术-郭伟强   E-mail:gwq20521@163.com
 * @createTime: 2017年3月17日 上午11:46:34 
 * @version: v 1.0
 * @since 
 *
 */
public class TrainOrderWeChatUnifiedApplyUtil {

    /**
     * @description: TODO - 微信的加密传输算法
     *  
     * @author: 郑州-技术-郭伟强   E-mail:gwq20521@163.com
     * @createTime: 2017年3月17日 上午11:50:52
     * @param errorMsg
     * @return
     */
    public static String strEncryptURLEncoder(String jsonStr) {
        //加密 - BASE64
        jsonStr = EncryptUtil.encrypt(jsonStr);
        //URL编码 - 后边的参数要进行URL编码再传递
        try {
            jsonStr = URLEncoder.encode(jsonStr, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return jsonStr;
    }

    public static String getSubmitTrainOrderWeChatUnifiedErrorMsg(String errorMsg) {
        switch (errorMsg) {
        case "orderParamError":
            return "没有传入参数或错误";
        case "bodyParamError":
            return "必传参数body为空";
        case "total_feeParamError":
            return "必传参数total_fee为空";
        case "notify_urlParamError":
            return "必传参数notify_url为空";
        case "openidParamError":
            return "必传参数openid为空";
        case "unifiedOrderError":
            return "统一下单失败";
        case "typeError":
            return "未传入或暂不支持此种类型的售票";
        }
        return "submitTrainOrderWeChatUnifiedErrorMsg异常，相关信息不存在";
    }

}
