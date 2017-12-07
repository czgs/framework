package com.dbutils.handler.handler;

import com.dbutils.handler.RowProcessor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class MapListHandler extends AbstractListHandler<Map<String,Object>> {
    @Override
    protected Map<String, Object> getRow(ResultSet resultSet) throws SQLException, InstantiationException, IllegalAccessException {
        return RowProcessor.toMap(resultSet);
    }
}
