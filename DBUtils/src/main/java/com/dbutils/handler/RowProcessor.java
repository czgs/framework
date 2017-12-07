package com.dbutils.handler;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class RowProcessor {

    /**
     * 将结果集转换成bean对象
     * @param rs
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T toBean(ResultSet rs,Class<?> clazz) throws IllegalAccessException, SQLException, InstantiationException {
        return (T)BeanUtil.createBean(rs,clazz);
    }

    /**
     * 将结果集存入Map集合
     * @param resultSet
     * @return
     * @throws SQLException
     */
    public static Map<String,Object> toMap(ResultSet resultSet) throws SQLException {
        Map<String,Object> map = new HashMap<String,Object>();
        //创建操作工具
        ResultSetMetaData metaData = resultSet.getMetaData();
        //遍历结果集
        for(int i = 1;i<metaData.getColumnCount();i++){
            map.put(metaData.getColumnLabel(i),resultSet.getObject(i));
        }
        return map;
    }

    /**
     * 将结果集存放Object数组（Array）
     * @param resultSet
     * @return
     * @throws SQLException
     */
    public static Object[] toArray(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        Object[] objects = new Object[metaData.getColumnCount()];
        for(int i = 0;i<metaData.getColumnCount();i++){
            objects[i] = resultSet.getObject(i+1);
        }
        return objects;
    }


}
