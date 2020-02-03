package Case;

import Util.*;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class LoginCaseExcel_JSON_v2 {

    @Test(dataProvider = "DataProvider")
    public void testLogin(String url,String type,String params){

        String contentType = PropertiesUtils.prop.getProperty("api.content.type");
        System.out.println(contentType);
        if("json".equalsIgnoreCase(contentType)){
            if("get".equalsIgnoreCase(type)){
                HttpUtils_JSON.testByGet(url,params,"utf-8");
            }else{
                HttpUtils_JSON.testByPost(url,params,"utf-8");
            }
        }else if("form".equalsIgnoreCase(contentType)){

            ArrayList<BasicNameValuePair> parametes = new ArrayList<BasicNameValuePair>();
            //JSON字符串转List
            //1、JSON转map
            Map<String,String> map = JSONObject.parseObject(params, Map.class);
            //遍历map
//        Set<Map.Entry<String,String>> entrys = map.entrySet();
//        Iterator<Map.Entry<String,String>> it = entrys.iterator();
//        while (it.hasNext()){
//            Map.Entry<String,String> entry = it.next();
//            String key = entry.getKey();
//            String value = entry.getValue();
//            parametes.add(new BasicNameValuePair(key,value));
//        }
            //遍历map
            Set<String> keys = map.keySet();
            for(String key:keys){
                String value = map.get(key);
                parametes.add(new BasicNameValuePair(key,value));
            }

            if("get".equalsIgnoreCase(type)){
                HttpUtils_Form.testByGet(url,parametes,"utf-8");
            }else{
                HttpUtils_Form.testByPost(url,parametes,"utf-8");
            }
        }
    }

    @Test(dataProvider = "DataProvider")
    public void testLogin1(String url,String type,String params){
        ApiExecutor.toService(url,type,params);
    }

    @DataProvider(name = "DataProvider")
    public Object[][] datas(){
        int[] rows = {1,2,3,4,5};
        int[] ceslls = {2,3,5};
        Object[][] datas = ExcelUtil.read(rows,ceslls );
        for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas[i].length; j++) {
                System.out.print(datas[i][j]+",");
            }
            System.out.println();
        }
        return datas;
    }


}
