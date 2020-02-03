package Util;

import Pojo.Case;
import Pojo.Variable;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class CaseUtil {
    public static List<Case> list;
    public static List<Variable> variables;

    static {
        list = ExcelUtil.read("用例",Case.class);
        variables = ExcelUtil.read("变量",Variable.class);
    }

    //将list集合转化为Object[][]二维数组
    public static Object[][] datas(String[] cellsName){
        Object[][] datas = new Object[list.size()][cellsName.length];
        for (int i = 0; i < list.size(); i++) {
            Case caseData = list.get(i);
            for (int j = 0; j < cellsName.length; j++) {
                String methodName = "get" + cellsName[j];
                try {
                    Method method = Case.class.getMethod(methodName);
                    Object value = method.invoke(caseData);
                    datas[i][j] = value;
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }

        }
        return datas;
    }

    public static Object[][] datas(String apiId,String[] cellsName){
        List<Case> listCase;
        if(StringUtils.isEmpty(apiId)){
            listCase = list;
        }else {
            listCase = filterCaseByApiId(apiId);
        }
        Object[][] datas = new Object[listCase.size()][cellsName.length];
        for (int i = 0; i < listCase.size(); i++) {
            Case caseData = listCase.get(i);
            for (int j = 0; j < cellsName.length; j++) {
                String methodName = "get" + cellsName[j];
                try {
                    Method method = Case.class.getMethod(methodName);
                    Object value = method.invoke(caseData);
                    datas[i][j] = value;
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }

        }
        return datas;
    }

    /**
     * 替换参数
     * @param apiId
     * @return
     */
    public static List<Case> filterCaseByApiId(String apiId){
        List<Case> listCase = new ArrayList<Case>();
        for (int i = 0; i < list.size(); i++) {
            if(apiId.equals(list.get(i).getApiId())){
                listCase.add(list.get(i));
            }
        }
        return listCase;
    }

    public static String replaceVariable(String str){
        if(StringUtils.isBlank(str)){
            return null;
        }
        for (Variable var:variables) {
            str = str.replace(var.getName(),var.getValue());
        }
        return str;
    }
}
