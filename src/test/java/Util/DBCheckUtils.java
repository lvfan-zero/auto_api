package Util;

import Pojo.SQLData;
import Pojo.SQLResult;
import Pojo.WriteBackData;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DBCheckUtils {
    public static void query(String caseId,String cellName,String validataSql){
        if(StringUtils.isBlank(validataSql)){
            return;
        }
        //解析json字符串
        List<SQLData> list = JSONObject.parseArray(validataSql, SQLData.class);
        List<SQLResult> results = new ArrayList<SQLResult>();
        for (SQLData sqlData:list) {
            QueryRunner qr = new QueryRunner();
            Connection conn = JDBCUtils.getConnection();
            String sql = sqlData.getSql();
            String no = sqlData.getNo();
            try {
//                qr.update(conn,sql,param);//修改删除
//                qr.insert(conn,sql,rsh);//插入
                //数据库查询操作
                Map<String,Object> map = qr.query(conn,sql,new MapHandler());
//                System.out.println(map);

                SQLResult sr = new SQLResult();
                sr.setNo(no);
                sr.setColumnLabelsAndValues(map);
                results.add(sr);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        String result = JSONObject.toJSONString(results);
        WriteBackData writeBackData = new WriteBackData(caseId,cellName,result);
        ExcelUtil.listWriteDate.add(writeBackData);
    }

//    public static void main(String[] args) {
//        String validataSql = "[\n" +
//                " {\n" +
//                "  \"no\":\"1\",\n" +
//                "  \"sql\":\"select count(*) as totalNum from member where mobilephone = '18811111121'\"\n" +
//                " }\n" +
//                "]";
//        query(validataSql);
//    }
}
