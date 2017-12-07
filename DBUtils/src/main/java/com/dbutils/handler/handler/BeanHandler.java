package com.dbutils.handler.handler;

import com.dbutils.handler.ResultSetHandler;
import com.dbutils.handler.RowProcessor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BeanHandler implements ResultSetHandler {
    private Class clzz;
    public BeanHandler(Class clzz){
        this.clzz = clzz;
    }
    @Override
    public Object handle(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException {
        return rs.next()? RowProcessor.toBean(rs,clzz):null;
    }
}
