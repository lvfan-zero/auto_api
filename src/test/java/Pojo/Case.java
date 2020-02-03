package Pojo;

public class Case {
    private String caseId;//用例编号
    private String desc;//用例描述
    private String apiId;//接口编号
    private String params;//参数
    private String expectedResponseData;//期望响应数据
    private String actualResponseData;//实际响应数据
    private String validateSQL;//SQL脚本
    private String beforeResult;//接口执行前数据库验证
    private String afterResult;//接口执行后数据库验证

//    @Override
//    public String toString() {
//        return "Case [caseId = "+caseId+",desc = "+desc+",apiId = "+apiId+",params = "+params+",expectedResponseData = "
//                +expectedResponseData+",actualResponseData = "+actualResponseData+",validateSQL = "+validateSQL+
//                ",beforeResult = "+beforeResult+",afterResult = "+afterResult;
//    }

    @Override
    public String toString() {
        return "Case{" +
                "caseId='" + caseId + '\'' +
                ", desc='" + desc + '\'' +
                ", apiId='" + apiId + '\'' +
                ", params='" + params + '\'' +
                ", expectedResponseData='" + expectedResponseData + '\'' +
                ", actualResponseData='" + actualResponseData + '\'' +
                ", validateSQL='" + validateSQL + '\'' +
                ", beforeResult='" + beforeResult + '\'' +
                ", afterResult='" + afterResult + '\'' +
                '}';
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getExpectedResponseData() {
        return expectedResponseData;
    }

    public void setExpectedResponseData(String expectedResponseData) {
        this.expectedResponseData = expectedResponseData;
    }

    public String getActualResponseData() {
        return actualResponseData;
    }

    public void setActualResponseData(String actualResponseData) {
        this.actualResponseData = actualResponseData;
    }

    public String getValidateSQL() {
        return validateSQL;
    }

    public void setValidateSQL(String validateSQL) {
        this.validateSQL = validateSQL;
    }

    public String getBeforeResult() {
        return beforeResult;
    }

    public void setBeforeResult(String beforeResult) {
        this.beforeResult = beforeResult;
    }

    public String getAfterResult() {
        return afterResult;
    }

    public void setAfterResult(String afterResult) {
        this.afterResult = afterResult;
    }
}
