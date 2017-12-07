package com.dbutils.handler;

import java.sql.*;

/**
 * SQL执行器
 */
public class SQLExecutor {
    private Connection connection;
    //自动关闭
    private boolean autoClose = true;
    //构造方法
    public SQLExecutor(Connection connection) {
        this.connection = connection;
    }
    //获得链接
    public Connection getConnection() {
        return connection;
    }

    public <T> T executeQuery(String sql,ResultSetHandler<T> handler,Object...params) throws SQLException {
        if(connection == null){
            throw new SQLException("Null connection");
        }
        if(sql == null){
            close();
            throw new SQLException("Null sql statement");
        }
        if(handler==null){
            close();
            throw new SQLException("Null ResultSetHandler");
        }
        PreparedStatement ps = null;
        ResultSet rs = null;
        T t = null;
        try {
        ps = connection.prepareStatement(sql);
        //调用设置sql参数方法
             setParameters(ps,params);
             rs = ps.executeQuery();
            t = handler.handle(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(rs);
            close(ps);
            if (autoClose) {
                close();
            }
        }
        return t;
    }


    /**
     * 设置SQL参数
     * @param ps
     * @param params
     * @throws SQLException
     */
    private void setParameters(PreparedStatement ps, Object[] params)
            throws SQLException {
        for (int i = 0; i < params.length; i++) {
            ps.setObject(i + 1, params[i]);
        }
    }


    /**
     * 关闭连接
     */
    private void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
        }
    }

    /**
     * 关闭结果集
     *
     * @param rs
     */
    private void close(ResultSet rs) {
        if (rs != null)
            try {
                rs.close();
            } catch (SQLException e) {
            }
    }
    /**
     * 关闭Statement
     *
     * @param st
     */
    private void close(Statement st) {
        if (st != null)
            try {
                st.close();
            } catch (SQLException e) {
            }
    }

}
