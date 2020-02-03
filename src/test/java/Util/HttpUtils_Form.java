package Util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class HttpUtils_Form {

    //get请求
    public static String testByGet(String url, List<BasicNameValuePair> parameters, String charset) {
        //1、创建一个Request连接
        //2、填写url和参数
        //3、添加参数
        //4、发送请求
        //5、获取响应请求
        //6、格式化
        //7、控制台输出报文

        //1、设置参数
        String parametersString = URLEncodedUtils.format(parameters,charset);
        url = url +"?"+ parametersString;
        //2、声明请求方式
        HttpGet get = new HttpGet(url);
        //3、声明HTTPClient对象
        HttpClient client = HttpClients.createDefault();//F4--查看一个类的继承树
        String result = null;
        try {
            get.setHeader("Content-Type","application/x-www-form-urlencoded");
            AuthenticationCookieUtil.setCookiesInRequest(get);
            //4、发送请求，获取响应对象
            HttpResponse response = client.execute(get);
            AuthenticationCookieUtil.getCookiesByResponse(response);
            //5、获取响应状态码
            int statusCode = response.getStatusLine().getStatusCode();
            //6、格式化响应结果
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity);
            //7、获取响应头信息
            String allHeaders = Arrays.toString(response.getAllHeaders());

            System.out.println("响应头信息："+allHeaders);
            System.out.println("状态："+statusCode);
            System.out.println("响应报文："+result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    //post请求
    public static String testByPost(String url, List<BasicNameValuePair> parameters, String charset) {
        //1、声明一个url
        String parametersString = URLEncodedUtils.format(parameters,charset);
        //2、声明请求方式
        HttpPost post = new HttpPost(url);
        String result = null;
        //绑定参数
        try {
            //设置参数
            post.setHeader("Content-Type","application/x-www-form-urlencoded");
            AuthenticationCookieUtil.setCookiesInRequest(post);
            post.setEntity(new StringEntity(parametersString,charset));
            //3、声明HTTPClient对象
            HttpClient client = HttpClients.createDefault();//F4--查看一个类的继承树
            //4、发送请求，获取响应对象
            HttpResponse response = client.execute(post);
            AuthenticationCookieUtil.getCookiesByResponse(response);
            //5、获取响应状态码
            int statusCode = response.getStatusLine().getStatusCode();
            //6、格式化响应结果
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity);
            //7、获取响应头信息
            String allHeaders = Arrays.toString(response.getAllHeaders());

            System.out.println("响应头信息："+allHeaders);
            System.out.println("状态："+statusCode);
            System.out.println("响应报文："+result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
