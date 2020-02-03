package Pojo;

public class SQLData {
    private String no;
    private String sql;

    @Override
    public String toString() {
        return "SQLData{" +
                "no='" + no + '\'' +
                ", sql='" + sql + '\'' +
                '}';
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
