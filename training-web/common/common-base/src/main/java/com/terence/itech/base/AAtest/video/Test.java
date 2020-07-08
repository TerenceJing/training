package com.terence.itech.base.AAtest.video;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Test {
    public  static  void main(String[] args) throws IOException{
        System.out.println("11111");
//        检测url有效性
//上午
//        String url="http://vodplay-a.xdysoft.com/xdylive-P6jXubmv--2020032609";
//        int[] mm={14,20};

       // String url="http://vodplay-a.xdysoft.com/xdylive-P6jXubmv--2020051210";
       // int[] mm={17,27};
//下午
//        String url="http://vodplay-a.xdysoft.com/xdylive-P6jXubmv--2020032513";
//        int[] mm={10,15};

       String url="http://vodplay-a.xdysoft.com/xdylive-P6jXubmv--2020051914";
       int[] mm={13,20};
       //  String url="http://vodplay-a.xdysoft.com/xdylive-P6jXubmv--2020051915";
       //  int[] mm={0,70};

//晚上
//        String url="http://vodplay-a.xdysoft.com/xdylive-P6jXubmv--2020042919";
//        int[] mm={24,40};

          // String url="http://vodplay-a.xdysoft.com/xdylive-P6jXubmv--2020042920";
          // int[] mm={27,40};

//        String url="http://vodplay-a.xdysoft.com/xdylive-P6jXubmv--2020041219";
//      int[] mm={50,59};



        for(int i=mm[0];i<=mm[1];){
            for(int j=0;j<60;){

                String httpUrl;
                if(i<10&&j<10){
                    httpUrl=url+"0"+i+"0"+j+".mp4";
                }else if(i<10&&j>=10){
                    httpUrl=url+"0"+i+j+".mp4";
                }else if(i>=10&&j<10){
                    httpUrl=url+i+"0"+j+".mp4";
                }else{
                    httpUrl=url+i+j+".mp4";
                }

                if(j==59){
                    i++;
                    j=0;
                }else{
                    j++;
                }
             System.out.println(httpUrl);
             try{
                 sendGet(httpUrl);
                 System.out.println("================合格："+httpUrl);
                 break;
             }catch (Exception e){
                 System.out.println("异常");
             }
            }
        }
    }

        /**
         * Http get请求
         * @param 
         * @return
         * @throws IOException
         */
        public static String sendGet(String httpUrl) throws IOException {

            URL url = new URL(httpUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置连接方式
            conn.setRequestMethod("GET");
            //设置主机连接时间超时时间3000毫秒
            conn.setConnectTimeout(3000);
            //设置读取远程返回数据的时间3000毫秒
            conn.setReadTimeout(3000);
            //发送请求
            conn.connect();
            //获取输入流
            InputStream is = conn.getInputStream();
            //封装输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            //接收读取数据
            StringBuffer sb = new StringBuffer();

            String line = null;
            while((line = br.readLine())!=null) {
                sb.append(line);
                sb.append("\r\n");
            }
            if(null!=br) {
                br.close();
            }
            if(null!=is) {
                is.close();
            }
            //关闭连接
            conn.disconnect();
            return sb.toString();
        }




}
