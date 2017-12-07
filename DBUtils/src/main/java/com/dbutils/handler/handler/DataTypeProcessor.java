package com.dbutils.handler.handler;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 对数据类型的处理
 */
public class DataTypeProcessor {

    /**
     * 基本数据类型处理
     * @param propType
     * @param resultSet
     * @param columnName
     * @return
     * @throws SQLException
     */
    public static Object typeConvert(Class<?> propType,ResultSet resultSet,String columnName) throws SQLException {
        Object object = null;
        if(propType.equals(String.class)) {
            object = resultSet.getString(columnName);
        } else if(propType.equals(Integer.TYPE) || propType.equals(Integer.class)) {
            object = resultSet.getInt(columnName);
        } else if(propType.equals(Boolean.TYPE) || propType.equals(Boolean.class)) {
            object = resultSet.getBoolean(columnName);
        } else if(propType.equals(Long.TYPE) || propType.equals(Boolean.class)) {
            object = resultSet.getLong(columnName);
        } else if(propType.equals(Long.TYPE) || propType.equals(Long.class)) {
            object = resultSet.getDouble(columnName);
        } else if(propType.equals(Float.TYPE) || propType.equals(Long.class)) {
            object = resultSet.getFloat(columnName);
        } else if(propType.equals(Short.TYPE) || propType.equals(Short.class)) {
            object = resultSet.getShort(columnName);
        } else if(propType.equals(Byte.TYPE) || propType.equals(Byte.class)) {
            object = resultSet.getByte(columnName);
        }else {
            object = resultSet.getObject(columnName);
        }
        return object;
    }

    //日期处理方法
}
