package com.mybatis.Controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.mybatis.Utils.MyConfig;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: janven
 * @Descrption: Created in ${Time} on 2017/12/12.
 */
public class PayController {

    @RequestMapping(value = "/Pay")
    @ResponseBody
    public Map<String,String> getPayString(@RequestParam("orderNo") String outtradeno,
                                           @RequestParam("PayMentType") int type) throws Exception{

        if (type == 0){
            //实例化客户端
            String alipayPublicKey = "xxxxx";
            String appPrivateKey = "xxxx";

            AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", "XXX", appPrivateKey, "json", "utf-8", alipayPublicKey, "RSA2");
            //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
            AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
            //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
            AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
            model.setBody("我是测试数据");
            model.setSubject("App支付测试Java");
            model.setOutTradeNo(outtradeno);
            model.setTimeoutExpress("30m");
            model.setTotalAmount("0.01");
            model.setProductCode("QUICK_MSECURITY_PAY");
            request.setBizModel(model);
            request.setNotifyUrl("商户外网可以访问的异步地址");
            try {
                //这里和普通的接口调用不同，使用的是sdkExecute
                AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
                System.out.println("------------要输出了");
                System.out.println(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
                Map<String,String> amp = new HashMap<String, String>();
                amp.put("data",response.getBody());
                return amp;

            } catch (AlipayApiException e) {
                e.printStackTrace();
            }
        }else if (type == 1){

            MyConfig config = new MyConfig();
            WXPay wxpay = new WXPay(config);

            Map<String, String> data = new HashMap<String, String>();
            data.put("body", "adasdas");
            data.put("out_trade_no", outtradeno);
//            data.put("device_info", "");
            data.put("fee_type", "CNY");
            data.put("total_fee", "1");
            data.put("spbill_create_ip", "123.12.12.123");
            data.put("notify_url", "http://www.example.com/wxpay/notify");
            data.put("trade_type", "APP");  // 此处指定为扫码支付
//        data.put("product_id", "12");


            try {
                Map<String, String> resp_order = wxpay.unifiedOrder(data);

                System.out.println("接收到订单号--"+outtradeno);

                System.out.println("-统一下单返回-----"+resp_order.toString());


                Map<String,String> resp = new HashMap<String, String>();

                resp.put("appid",resp_order.get("appid"));
                resp.put("partnerid",resp_order.get("mch_id"));
                resp.put("prepayid",resp_order.get("prepay_id"));
                resp.put("package","Sign=WXPay");
                resp.put("noncestr",resp_order.get("nonce_str"));
                long time =  System.currentTimeMillis();
                resp.put("timestamp",String.valueOf(time/1000));

                //成功之后再给app 生成签名


                MyConfig myConfig = new MyConfig();

                resp.put("sign", WXPayUtil.generateSignature(resp, myConfig.getKey(), WXPayConstants.SignType.MD5));

                System.out.println("-最终返回-----"+resp.toString());

                return resp;
            } catch (Exception e) {
                e.printStackTrace();
                return new HashMap<String, String>();
            }

        }



        return new HashMap<String, String>();
    }


}
