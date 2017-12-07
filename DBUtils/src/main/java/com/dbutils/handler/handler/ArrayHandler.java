package com.dbutils.handler.handler;

import com.dbutils.handler.ResultSetHandler;
import com.dbutils.handler.RowProcessor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArrayHandler implements ResultSetHandler<Object[]> {
    @Override
    public Object[] handle(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException {
        return rs.next()? RowProcessor.toArray(rs):null;
    }
}
