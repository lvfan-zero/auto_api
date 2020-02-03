package Util;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * cookie信息认证
 */
public class AuthenticationCookieUtil {
    public static Map<String,String> cookies = new HashMap<String, String>();
    public static final String RESPONSE_HEADER = "Set-Cookie";
    public static final String REQUEST_HEADER = "Cookie";
    public static final String COOKIE_NAME = "JSESSIONID";


    //抓取cookie信息,从响应头获取指定cookie字段
    public static void getCookiesByResponse(HttpResponse response){
        Header header = response.getFirstHeader(RESPONSE_HEADER);
        if(header != null) {
            String cookie = header.getValue();
            if (StringUtils.isNoneBlank(cookie)) {
                String[] values = cookie.split(";");
                for (String str : values) {
                    if (str.contains(COOKIE_NAME)) {
                        cookies.put(COOKIE_NAME, str);//JSESSIONID = JSESSIONID=B9DA81061D46C675ADD6AC43C6F8FCF7
                    }
                }
            }
        }
    }

    //设置请求头的cookie信息
    public static void setCookiesInRequest(HttpRequest request){
        String value = cookies.get(COOKIE_NAME);
        //判断cooki信息是否为空，不为空则添加到请求头中
        if(StringUtils.isNoneBlank(value)){
            request.setHeader(REQUEST_HEADER,value);
        }
    }

}
