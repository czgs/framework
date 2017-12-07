package com.dbutils.handler.handler;

import com.dbutils.handler.ResultSetHandler;
import com.dbutils.handler.RowProcessor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class MapHandler implements ResultSetHandler<Map<String,Object>> {

    @Override
    public Map<String, Object> handle(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException {
        return rs.next()? RowProcessor.toMap(rs):null;
    }
}
