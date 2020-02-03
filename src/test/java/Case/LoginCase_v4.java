package Case;

import Util.ApiExecutor;
import Util.ApiInfoUtil;
import Util.CaseUtil;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginCase_v4 {

    @Test(dataProvider = "DataProvider")
    public void testLogin(String params,String apiId){
        String url = ApiInfoUtil.getUrlByApiId(apiId);
        String type = ApiInfoUtil.getTypeByApiId(apiId);
        ApiExecutor.toService(url,type,params);
    }

    @DataProvider(name = "DataProvider")
    public Object[][] datas(){
        String[] caseName = {"Params","ApiId"};
        Object[][] caseDatas = CaseUtil.datas("2",caseName);
        return caseDatas;
    }


}
