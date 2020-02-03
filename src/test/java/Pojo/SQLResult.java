package Pojo;

import java.util.HashMap;
import java.util.Map;

public class SQLResult {
    private String no;
    private Map<String,Object> columnLabelsAndValues = new HashMap<String, Object>();

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Map<String, Object> getColumnLabelsAndValues() {
        return columnLabelsAndValues;
    }

    public void setColumnLabelsAndValues(Map<String, Object> columnLabelsAndValues) {
        this.columnLabelsAndValues = columnLabelsAndValues;
    }

    @Override
    public String toString() {
        return "SQLResult{" +
                "no='" + no + '\'' +
                ", columnLabelsAndValues=" + columnLabelsAndValues +
                '}';
    }
}
