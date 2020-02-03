package Util;

import Pojo.ApiInfo;

import java.util.List;

public class ApiInfoUtil {
    public static List<ApiInfo> list;
    static {
        list = ExcelUtil.read("接口信息",ApiInfo.class);
    }

    public static String getTypeByApiId(String apiId) {
        if(apiId == null){
            return null;
        }
        for (ApiInfo apiInfo:list) {
            if (apiId.equals(apiInfo.getApiId())){
                return apiInfo.getType();
            }
        }
        return null;
    }

    public static String getUrlByApiId(String apiId) {
        if(apiId == null){
            return null;
        }
        for (ApiInfo apiInfo:list) {
            if (apiId.equals(apiInfo.getApiId())){
                return apiInfo.getUrl();
            }
        }
        return null;
    }

//    public static void main(String[] args) {
//        String typeByApiId = getTypeByApiId("2");
//        String urlByApiId = getUrlByApiId("2");
//        System.out.println(typeByApiId);
//        System.out.println(urlByApiId);
//    }
}
