package com.xuwenxing.securityDemo.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * BCrypt加密方法测试类
 * Created by xuwx on 2018/10/11.
 */
public class TestEncoder{

    public static void main(String[] args) {
        new TestEncoder().findMemoryMonitor();
        String password= "1024";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4);
        String encodePassword = encoder.encode(password);
        System.out.println(encodePassword);
    }

    /**
     * 内存监控
     */
    public void findMemoryMonitor(){
        try {
            String cmd="jconsole";
            StringBuffer cmdout = new StringBuffer();
            System.out.println("执行命令：" + cmd);
            Process process = Runtime.getRuntime().exec(cmd);     //执行一个系统命令
            InputStream fis = process.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line = null;
            while ((line = br.readLine()) != null) {
                cmdout.append(line).append(System.getProperty("line.separator"));
            }
            System.out.println("执行系统命令后的结果为：\n" + cmdout.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public  static  void  niceXNice(int i, int y){
        for (int i1=0;i1<=i;i1++){

        }

    }
}
