package Case;

import Util.HttpUtils_JSON;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class LoginCaseDataProvider {
    private String url = "http://localhost:8011/member/loginGetJSON";

    @Test(dataProvider = "DataProvider")
    public void testLogin(String mobilephone,String pwd){

        ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
        params.add(new BasicNameValuePair("mobilephone",mobilephone));
        params.add(new BasicNameValuePair("pwd",pwd));
        HttpUtils_JSON.testByGet(url,params,"utf-8");
    }

    @DataProvider(name = "DataProvider")
    public Object[][] datas(){
        return new Object[][]{
            {"18812345678","a123456"},//正确的手机号密码
            {"18812345678","123456"},//正确的手机号，错误的密码
            {"1882345555","a123456"},//错误的手机号，做正确的密码
            {"18812345678",""},//正确的手机号，密码为空
            {"","a123456"}//手机号为空
        };
    }
}
