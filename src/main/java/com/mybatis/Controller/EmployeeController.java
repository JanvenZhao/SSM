package com.mybatis.Controller;

import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.mybatis.Utils.MyConfig;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;

import com.mybatis.Model.Employee;
import com.mybatis.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alipay.api.*;
import com.alipay.api.request.*;
import com.alipay.api.domain.*;
import com.alipay.api.response.*;
import com.alipay.api.AlipayApiException;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.wxpay.sdk.WXPay;


/**
 * @Author: janven
 * @Descrption: Created in ${Time} on 2017/11/28.
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @ResponseBody
    @RequestMapping(value="/listAll",method= RequestMethod.GET)

    public Map list(Map<String,Object> map){
        List<Employee> employees = employeeService.getEmployees();
       /*测试用
      if(employees==null)
         System.out.println("null");
      else
         System.out.println(employees);*/

        map.put("employees",employees);
        return map;

    }



    @RequestMapping(value = "/Pay")
    @ResponseBody
    public Map<String,String> getPayString(@RequestParam("orderNo") String outtradeno,
                                           @RequestParam("PayMentType") int type) throws Exception{

        if (type == 0){
            //实例化客户端
            String alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmuO6PgrKK0M1Xy/o6U24TFHzteIoc3L2Xb3Sl9Sj4lPfoRJOJHkAzP7kMFV0Fbog4Khm0orFjvdbnMAjict+qrssgcga80ejMEG7+8y5W5YKYWe8SKvulyUGoaLnlVandOP1fMks302pAIoIqgas+tvUnjt6+8Np+Z8kw2J+zclNLmdeGsMxJWx9CJH54PkkXGevMPYRRD9xdAU/zBYbgP+yuzhlWAIzrUuJT9EizRzAQprPyZuoAdTCjIbZROfGZ0ne/lvDTEJ9E/PCB71ItYhflaapoC3GL5Ze3jLrM6S/L5ZVPAIIXSyS/pB/rdQtomMkXkQ2udk73+UDAhq5LwIDAQAB";
            String appPrivateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCa47o+CsorQzVfL+jpTbhMUfO14ihzcvZdvdKX1KPiU9+hEk4keQDM/uQwVXQVuiDgqGbSisWO91ucwCOJy36quyyByBrzR6MwQbv7zLlblgphZ7xIq+6XJQahoueVVqd04/V8ySzfTakAigiqBqz629SeO3r7w2n5nyTDYn7NyU0uZ14awzElbH0Ikfng+SRcZ68w9hFEP3F0BT/MFhuA/7K7OGVYAjOtS4lP0SLNHMBCms/Jm6gB1MKMhtlE58ZnSd7+W8NMQn0T88IHvUi1iF+VpqmgLcYvll7eMuszpL8vllU8AghdLJL+kH+t1C2iYyReRDa52Tvf5QMCGrkvAgMBAAECggEAJ9Uk0kvIDjQsh5w0k7svCEGTIVVV/cq03QqyG+XNd6kGO9APVIXh1NZALMXtIqFJTz0/pCmYANpQLxGikg9JNfMI0R96/tGPgBuHkovlp04AwWgCHdZJd4lIGU1hrSAp1H/DZCIAbjT0n3TgIYmx9pmOJls5l4F7wXxR4gV0gss2h0eAZOVI62CDRVO0JhqTQ0HkEuFriS3r7vuwS0H51U64q5MhInEDRmhHjD/JUmQ1P9vSOrkpWQD9NGLrQUQyCKJ7c3qQhFTl+AMcW4NmJnKIF0fYLqXG7WRRiAGLfR8/qbyUYBP/oe29R8T5ImhXgKBcKtMk6lktWrxtrJWn0QKBgQD+0ar8kjFIZzBlySZQYBSJX37wsIdKepH39oBbT2784IIYK06z/XAaFqyqY2bhOSHkviGRUlEP+4OeBkiMYT/Wxl9ZScSnn2pFJWB1iRqoRLGlpk2lBkPReBfHWvRzP5F4gpmTA6bgLTIz9n9SGOC2i+dn37zlab1EIQ1Yaha/ywKBgQCbm39a/v2LC62izygzB7O0oUUa5skld/ez7MVlrCknU3j8jxC37A0jKpnrdjjlxXlEoFCBSl7qf/pZFrjKxnCwJ7puhvYh29leSUTyaa2B4d3yvuUaArhyArFe6Zp2ZNwQ+1w8mvWLjoaMwjkU7IKMrBmKcVrRJ084i1LgIsO3rQKBgQDm6CH0a5ljDbbKlYM1lEsnzLRf8Oc382fwdZwMoidl2R+qPRw9d+wsh92BLUAd2ULAYBZU9G3meLjk6ra1Fq7dtbbTV0hvyMJo1BzbhRj14AHGpLN5VfsAhr2tddtL0A/HTzlRAh8mx23VronjYA3I/W/9Lb6jeLT8VqMcOk5RDwKBgHhkvQqYk27LYFLBWObypJWy2RbRTPvScDIjlS7vHwAwU0GFJ3hGCHwW7/QAClGskgEI0IDYG8vvrLDVJbCuDG4ert3pSfI0eV+77L5qCTaenOy9yeBwDmkXsEgxEbNcoe/5qwmxEx4g7fVmKGgJ/Y/9b91visacVWz4TuzfuPIZAoGAbKziqdTK6BO2RKPMggfkQ19e99PwIbGCK5bPXPloz80qExmNw754dc7fyf3gdczaLf8d/tnR7cGxKgAVgJqSrcV3sDeT0AbU+g82MoNdpMmUIZUO8tKOLjdfyZEEkNeK+n8hSRPcEYgQ6+OZ4B9asT3SFvvzTmg7USvh6pPeVY8=";

            AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", "2015120200903763", appPrivateKey, "json", "utf-8", alipayPublicKey, "RSA2");
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
