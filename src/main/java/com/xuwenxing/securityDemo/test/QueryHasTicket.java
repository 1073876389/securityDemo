package com.xuwenxing.securityDemo.test;

import java.io.*;

/**
 * 孙胜彬大佬写的一个已票的代码
 * Created by xuwx on 2018/10/18.
 */
public class QueryHasTicket {
    public static void main(String[] args) {
        try {
            InputStream in=new FileInputStream(new File("E:/2018yipiao/MMI1520180927_00004.DAT"));
            InputStreamReader isr=new InputStreamReader(in, "UTF-8");
            BufferedReader br=new BufferedReader(isr);

            OutputStream out=new FileOutputStream("E:/2018yipiao/1234.txt");
            OutputStreamWriter osw=new OutputStreamWriter(out);
            BufferedWriter bw=new BufferedWriter(osw);
            String line;
            int i=0;
            StringBuffer sbf=new StringBuffer();
            while((line=br.readLine())!=null){
                System.out.println(line);
                String num=String.format("%06d", i + 1);
                System.out.println(line);
                String str=line;
                String sql="INSERT INTO `localdata_autopus`.`cfg_material_ticket` "
                        + "(`ID`, `PURCHASE_DOCUMENT`, `MATERIAL_DOCUMENT`, `MATERIAL_DOCUMENT_YEAR`, `QUANTITY`, `AMOUNT`, `PURCHASE_DOCUMENT_ITEM`, `MATERIAL_DOCUMENT_ITEM`, `INVOICE_NO`) "
                        + " VALUES ('10001000"+num+"', '"+str.substring(112, 122).trim()+"', '"+str.substring(158, 168).trim()+"', '"+str.substring(154, 158).trim()+"', "+str.substring(141, 154).trim()+", "+str.substring(128, 141).trim()+", '"+ str.substring(122, 128).trim()+"', '"+str.substring(168, 172).trim()+"', '"+str.substring(47, 57).trim()+"');\r\n";
                System.out.println(sql);//主键id该1000
                bw.write(sql);
                i++;
            }
            br.close();
            isr.close();
            in.close();
            bw.close();
            osw.close();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
