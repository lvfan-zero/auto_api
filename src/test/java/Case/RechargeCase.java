package Case;

import Util.CaseUtil;
import org.testng.annotations.DataProvider;

public class RechargeCase extends BaseCase{

    @Override
    @DataProvider(name = "datas")
    public Object[][] datas() {
//        String[] cellsName = {"CaseId","Params","ApiId"};
        Object[][] datas = CaseUtil.datas("3", cellsName);
        return datas;
    }

}
