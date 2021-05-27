package com.fan.wuye.util;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号,开发时使用沙箱提供的APPID，生产环境改成自己的APPID
    public static String APP_ID = "2016101800714145";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String APP_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCS4/H+HJNNNSK81Wu523JS4Nkle+53n0xqspiVtrkumJFsCQlDjMK64BzQG6h/qXBJ+ko3+eh38e8TBnSt1E2UOb6OJb3WKlZBO+Q3vtXv5FV6mD9vancIbT1yYaTpC24ds12wUHUv4KxVle/4M6hMyirmoAZhuudn0gwT7+14THqXYarH4uTa61rXOFSVV2sJsNM37tihdeRvCWoOb0qB7zZNhvIi92S0em6VZkv/LqHxGAT7iWQo5+KrYP6qH2Pq5TT4rtn5b/pH0oSFH1VOQeCjN5x5EgJ2O1r1ogHm4T84STTns6v/x5C0x3zku7mvQAGlIduETnnsQvvQgQ0BAgMBAAECggEAVQL5ZbDSC2gKL7FwxELd+ltJMSl1jOOzzTiJmHQE3pPL7fOY2fBYLuFPq52mOiQs6ZOiap0GzoxFagRdeNEbCQMWZpwvLwiXnO91mFGo7Up/shD0/Z1nQIhAfLdIQturoiLRwR3/hHMKKgo5tLLo33BNveW1a9BI5swo000TaYAvPcxkcldXGhV0uI5bRJNBzALVB1Rd7vv9PjWE2JiEnfyV4yISLwMwBktFT+dE+yBA2tJO4YTmFFG/1gzYbI6yry3bl+MJ5GkPstrbXxa/1gN76Mjf66e+2CtdM4VqHr6ru4iRRuxGZe9P5N/L+/BPyVcqfmvtdTPVUwZtxrlKMQKBgQDc/mwvA+TchR0ty4efogX5HUIvCJUjzFjEsWve66yWmKeU6wz+O+SgngzrpHoin95FsJC0hv6DZ/VzrgQyNAw4FZRSGGWV9TatDx7R34KIZz2h7nRHPE/2vurdH3CMGSOdOAUgCFOnKo6dRmZKNSEk2P6b4gb/oatjHtTISC/RVQKBgQCqKIknMKPDK7EYCNdqiA1jRMj4EOoaNrDtwL30OVd+N0goYCleb3hyO9aHZQJZDDu6N6paSlU49fjMF88arYw9xHPBZNBuhYKKCpe6ARLSZC0gfKt2TlsnWwxJK+Z9WvDS/nLEzqcn7AD6lZbe33726KlajB1Be+Nfwh0BuHJ8/QKBgQCj42Kjc0XUbDt5bMkFj4jSd+pPKxQ/WhoytEkFJs31WPxeqfoWyKACpXN7hZy19M1H7WtDFiHFneEfitu9x2+QYCCAyWr8zL9hlCcNCa6qLbY9UrOU6Jt8p5VRqzEyFKqeu57BZKt0+1CMPls5iTEtPGeFfxgll+WcOo8egLpFiQKBgC7om97c7th4LAsXLOOmfCJYrRqN9yug1ifFEdjJEhCuhsryr5TkIA1F579NnlcUI0cT33eitFnOnXcOPT76hwXkKZEwDLHbRoBtqSRS9XBgw3wIQnCL618vH3TwkEsQjgFsuhD9c2FzE1YBdSUd8e95dm6K3+nUkHsulTaLNay1AoGBAJuDsj2w0abHcWEIqYf22Be5j1T5+iI4hPiVnSeAYp8V3YZTezcWKFeIfbbBz5X4czdg84SrCIRaK/WxMcYW/cAZ2c4ymTiubXjKDUJpJ2he4PCacBA3zkTWhQ3PqJ2iPRMMgJ1knU0eKeaBg4svoLIzSFyfkENbEgA9+JHk+Snn";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAk0r8iVHxEieIn4n8sZN7zdpDsv/TVepXLhS9aZ3Nc77BBnAnPPsiq28f6LB/RHn6EC9/uUAEQCR8waecYgoGHKg9P3/9HuWdTghGfj9dVAPtVVelzAYLGTGISCa/+llaggaz6KX+/xQZazPpQJXndXe4o6naRaaryHx+H3w8E6jwz9PELcW2wGvVC3ZePtcJP+PGAH0EkZ3S22pQ8HOtLhDLysR+mMH8wfb9jXhvAzUFY3zMjlhWvMUuVi6T+l5oStxhycLK5v/rkJJKJlJWpJt6QKDvYplMXW2suCYyL78DN+JpNSqrUMtLZgXyymjvFjH8LXpf106pFGKlRbrnqwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8080/user/index";
    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问(其实就是支付成功后返回的页面)
    public static String return_url = "http://localhost:8080/user/index";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String CHARSET = "utf-8";

    // 支付宝网关，这是沙箱的网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";




    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

