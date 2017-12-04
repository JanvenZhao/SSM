package com.mybatis.Utils;

import com.github.wxpay.sdk.WXPayConfig;

import java.io.*;

/**
 * @Author: janven
 * @Descrption: Created in ${Time} on 2017/11/30.
 */
public class MyConfig implements WXPayConfig {


    private byte[] certData;

    public MyConfig() throws Exception {
//        String certPath = "/path/to/apiclient_cert.p12";
//        File file = new File(certPath);
//        InputStream certStream = new FileInputStream(file);
//        this.certData = new byte[(int) file.length()];
//        certStream.read(this.certData);
//        certStream.close();
    }

    public String getAppID() {
        return "XXXXXXXx";
    }

    public String getMchID() {
        return "xxxxxx";
    }

    public String getKey() {
        return "xxxxxxxx";
    }

    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    public int getHttpReadTimeoutMs() {
        return 10000;
    }

}
