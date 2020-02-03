package Util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class ApiExecutor {
    public static String toService(String url,String type,String params){
        String contentType = PropertiesUtils.prop.getProperty("api.content.type");
//        System.out.println(contentType);
        if("json".equalsIgnoreCase(contentType)){
            if("get".equalsIgnoreCase(type)){
               return HttpUtils_JSON.testByGet(url,params,"utf-8");
            }else{
                return HttpUtils_JSON.testByPost(url,params,"utf-8");
            }
        }else if("form".equalsIgnoreCase(contentType)){
            ArrayList<BasicNameValuePair> parametes = jsonToList(params);
            if("get".equalsIgnoreCase(type)){
                return HttpUtils_Form.testByGet(url,parametes,"utf-8");
            }else{
                return HttpUtils_Form.testByPost(url,parametes,"utf-8");
            }
        }
        return null;
    }
    
    private static ArrayList<BasicNameValuePair> jsonToList(String params){
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
        return parametes;
    }

}
