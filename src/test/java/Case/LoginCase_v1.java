package Case;

import Util.HttpUtils_Form;
import Util.HttpUtils_JSON;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class LoginCase_v1 {

    //get请求
    @Test
    public void testByGet() {
        //1、创建一个Request连接
        //2、填写url和参数
        //3、添加参数
        //4、发送请求
        //5、获取响应请求
        //6、格式化
        //7、控制台输出报文

        //1、声明一个url
        String url = "http://test.lemonban.com/futureloan/mvc/api/member/login";
        String parameters = "mobilephone=18812345678&pwd=123456";
        url = url+"?"+parameters;
        //2、声明请求方式
        HttpGet get = new HttpGet(url);
        //3、声明HTTPClient对象
        HttpClient client = HttpClients.createDefault();//F4--查看一个类的继承树
        try {
            //4、发送请求，获取响应对象
            HttpResponse response = client.execute(get);
            //5、获取响应状态码
            int statusCode = response.getStatusLine().getStatusCode();
            //6、格式化响应结果
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            //7、获取响应头信息
            String allHeaders = Arrays.toString(response.getAllHeaders());

            System.out.println("响应头信息："+allHeaders);
            System.out.println("状态："+statusCode);
            System.out.println("响应报文："+result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //post请求
    @Test
    public void testByPost() {
        //1、声明一个url
        String url = "http://test.lemonban.com/futureloan/mvc/api/member/login";
        //2、声明请求方式
        HttpPost post = new HttpPost(url);
        //绑定参数
        ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
        params.add(new BasicNameValuePair("mobilephone","18812345678"));
        params.add(new BasicNameValuePair("pwd","123456"));
        try {
            //设置参数
            post.setEntity(new UrlEncodedFormEntity(params));
            //3、声明HTTPClient对象
            HttpClient client = HttpClients.createDefault();//F4--查看一个类的继承树
            //4、发送请求，获取响应对象
            HttpResponse response = client.execute(post);
            //5、获取响应状态码
            int statusCode = response.getStatusLine().getStatusCode();
            //6、格式化响应结果
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            //7、获取响应头信息
            String allHeaders = Arrays.toString(response.getAllHeaders());

            System.out.println("响应头信息："+allHeaders);
            System.out.println("状态："+statusCode);
            System.out.println("响应报文："+result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //post请求
    @Test
    public void testByPost2() {
        //1、声明一个url
        String url = "http://test.lemonban.com/futureloan/mvc/api/member/login";
        //2、声明请求方式
        HttpPost post = new HttpPost(url);
        //绑定参数
        ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
        params.add(new BasicNameValuePair("mobilephone","18812345678"));
        params.add(new BasicNameValuePair("pwd","123456"));
        try {
            //设置值请求头，以表单方式提交
            post.setHeader("Content-Type","application/x-www-form-urlencoded");
            //设置参数
            post.setEntity(new StringEntity("mobilephone=18812345678&pwd=123456","utf-8"));

            //3、声明HTTPClient对象
            HttpClient client = HttpClients.createDefault();//F4--查看一个类的继承树
            //4、发送请求，获取响应对象
            HttpResponse response = client.execute(post);
            //5、获取响应状态码
            int statusCode = response.getStatusLine().getStatusCode();
            //6、格式化响应结果
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            //7、获取响应头信息
            String allHeaders = Arrays.toString(response.getAllHeaders());

            System.out.println("响应头信息："+allHeaders);
            System.out.println("状态："+statusCode);
            System.out.println("响应报文："+result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testByPost3() {
        //1、声明一个url
        String url = "http://test.lemonban.com/futureloan/mvc/api/member/login";
        //2、声明请求方式
        HttpPost post = new HttpPost(url);
        //绑定参数
        ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
        params.add(new BasicNameValuePair("mobilephone","18812345678"));
        params.add(new BasicNameValuePair("pwd","123456"));
        try {
            //设置值请求头，以表单方式提交
            post.setHeader("Content-Type","application/x-www-form-urlencoded");
            //设置参数
            post.setEntity(new StringEntity("mobilephone=18812345678&pwd=123456","utf-8"));
            //3、声明HTTPClient对象
            HttpClient client = HttpClients.createDefault();//F4--查看一个类的继承树
            //设置代理
            HttpHost proxy = new HttpHost("127.0.0.1",8888);
            //4、发送请求，获取响应对象
            HttpResponse response = client.execute(proxy,post);
            //5、获取响应状态码
            int statusCode = response.getStatusLine().getStatusCode();
            //6、格式化响应结果
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            //7、获取响应头信息
            String allHeaders = Arrays.toString(response.getAllHeaders());

            System.out.println("响应头信息："+allHeaders);
            System.out.println("状态："+statusCode);
            System.out.println("响应报文："+result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //调用封装后的get方法-以form表单格式提交参数列表
    @Test
    public void formGetCase(){
        String url = "http://localhost:8011/member/loginGetForm";
        ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
        params.add(new BasicNameValuePair("mobilephone","18812345678"));
        params.add(new BasicNameValuePair("pwd","123456"));
        HttpUtils_Form.testByGet(url,params,"utf-8");
    }

    //调用封装后的post方法-以form表单格式提交参数列表
    @Test
    public void formPostCase(){
        String url = "http://localhost:8011/member/loginPostForm";
        ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
        params.add(new BasicNameValuePair("mobilephone","18812345678"));
        params.add(new BasicNameValuePair("pwd","123456"));
        HttpUtils_Form.testByPost(url,params,"utf-8");
    }

    //调用封装后的post方法-以JSON格式提交参数列表
    @Test
    public void JSONPostCase(){
        String url = "http://localhost:8011/member/loginPostJSON";
        ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
        params.add(new BasicNameValuePair("mobilephone","18812345678"));
        params.add(new BasicNameValuePair("pwd","a123456"));
        HttpUtils_JSON.testByPost(url,params,"utf-8");
    }

    //调用封装后的get方法-以JSON格式提交参数列表
    @Test
    public void JSONGetCase(){
        String url = "http://localhost:8011/member/loginGetJSON";
        ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
        params.add(new BasicNameValuePair("mobilephone","admin"));
        params.add(new BasicNameValuePair("pwd","123456"));
        HttpUtils_JSON.testByGet(url,params,"utf-8");
    }

    //调用封装后的get方法-以JSON格式提交参数列表
    @Test
    public void JSONGetCaseString(){
        String url = "http://localhost:8011/member/loginGetJSON";
        String params = "{\"mobilephone\":\"18812345678\",\"pwd\":\"a123456\"}";
        HttpUtils_JSON.testByGet(url,params,"utf-8");
    }

    //调用封装后的get方法-以JSON格式提交参数列表
    @Test
    public void JSONPostCaseString(){
        String url = "http://localhost:8011/member/loginPostJSON";
        String params = "{\"mobilephone\":\"18812345678\",\"pwd\":\"a123456\"}";
        HttpUtils_JSON.testByPost(url,params,"utf-8");
    }
}
