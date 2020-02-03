package Case;

import Util.ExcelUtil;
import Util.HttpUtils_Form;
import Util.HttpUtils_JSON;
import Util.PropertiesUtils;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class LoginCaseExcel {

    @Test(dataProvider = "DataProvider")
    public void testLogin(String url,String type,String mobilephone,String pwd){
        ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
        params.add(new BasicNameValuePair("mobilephone",mobilephone));
        params.add(new BasicNameValuePair("pwd",pwd));
        String contentType = PropertiesUtils.prop.getProperty("api.content.type");
        System.out.println(contentType);
        if("json".equalsIgnoreCase(contentType)){
            if("get".equalsIgnoreCase(type)){
                HttpUtils_JSON.testByGet(url,params,"utf-8");
            }else{
                HttpUtils_JSON.testByPost(url,params,"utf-8");
            }
        }else if("form".equalsIgnoreCase(contentType)){
            if("get".equalsIgnoreCase(type)){
                HttpUtils_Form.testByGet(url,params,"utf-8");
            }else{
                HttpUtils_Form.testByPost(url,params,"utf-8");
            }
        }
    }

    @DataProvider(name = "DataProvider")
    public Object[][] datas(){
        int[] rows = {1,2,3,4,5};
        int[] ceslls = {2,3,5,6};
        Object[][] datas = ExcelUtil.read(rows,ceslls );
        return datas;
    }
}
