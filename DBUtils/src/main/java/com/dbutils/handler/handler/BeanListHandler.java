package com.dbutils.handler.handler;

import com.dbutils.handler.RowProcessor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BeanListHandler extends AbstractListHandler {
    private Class<?> clazz;
    public BeanListHandler(Class<?> clazz){
        this.clazz = clazz;
    }

    @Override
    protected Object getRow(ResultSet resultSet) throws SQLException, InstantiationException, IllegalAccessException {
        return RowProcessor.toBean(resultSet,clazz);
    }
}
