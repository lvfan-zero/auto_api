package Case;

import Pojo.WriteBackData;
import Util.*;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public abstract class BaseCase {
    //创建日志对象，在哪个类用，传哪个类的字节码对象
    private Logger log = Logger.getLogger(BaseCase.class);
    public String[] cellsName = {"CaseId","Params","ApiId","ValidateSQL"};

    @BeforeSuite
    public void init(){
        log.info("==============开始执行套件================");
    }

    @Test(dataProvider = "datas")
    public void test(String caseId,String params,String apiId,String validateSql){
        log.info("===============开始执行"+caseId+"测试用例==================");
        String url = ApiInfoUtil.getUrlByApiId(apiId);
        String type = ApiInfoUtil.getTypeByApiId(apiId);

        //执行变量替换方法
        params = CaseUtil.replaceVariable(params);
        validateSql = CaseUtil.replaceVariable(validateSql);
        log.info("params="+params);
        log.info("validateSql="+validateSql);
        //执行接口调用之前的数据库操作
        DBCheckUtils.query(caseId,"BeforeResult",validateSql);

        String result = ApiExecutor.toService(url,type,params);

        //执行接口调用之后的数据库操作
        DBCheckUtils.query(caseId,"AfterResult",validateSql);

        log.info("================添加回写信息======================");
        //测试结果回写
        WriteBackData writeBackData = new WriteBackData(caseId,"ActualResponseData",result);
        ExcelUtil.listWriteDate.add(writeBackData);//将writeBackData对象添加到list集合中(执行多少个用例，则声明多少个对象)

        log.info("===============结束"+caseId+"测试用例==================");
    }

    @AfterSuite
    public void batchWrite(){
        log.info("===================批量回写=========================");
        ExcelUtil.batchWrite();
        log.info("===================套件执行完成======================");
    }

    //定义抽象方法datas(),交给子类实现
    public abstract Object[][] datas();
}
