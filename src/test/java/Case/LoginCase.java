package Case;

import Util.CaseUtil;
import org.testng.annotations.DataProvider;

public class LoginCase  extends BaseCase{

    @Override
    @DataProvider(name = "datas")
    public Object[][] datas() {
//        String[] cellsName = {"CaseId","Params","ApiId","ValidateSQL"};
        Object[][] datas = CaseUtil.datas("2", cellsName);
        return datas;
    }

}
