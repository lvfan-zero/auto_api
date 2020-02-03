package Case;

import Util.CaseUtil;
import org.testng.annotations.DataProvider;

public class RegisterCase extends BaseCase{

    @Override
    @DataProvider(name = "datas")
    public Object[][] datas() {
//        String[] caseName = {"CaseId","Params","ApiId"};
        Object[][] caseDatas = CaseUtil.datas("1",cellsName);
        return caseDatas;
    }
}
