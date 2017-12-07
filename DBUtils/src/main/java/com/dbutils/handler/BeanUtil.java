package com.dbutils.handler;

import com.dbutils.handler.handler.DataTypeProcessor;
import com.dbutils.model.anno.ColumnAnno;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class BeanUtil {
    private static final Map<Class<?>, Object> primitiveDefaults = new HashMap<Class<?>, Object>();
    /**
     * 当数据库取出null值时,给基本数据类型默认值
     */
    static {
        primitiveDefaults.put(Integer.TYPE, Integer.valueOf(0));
        primitiveDefaults.put(Short.TYPE, Short.valueOf((short) 0));
        primitiveDefaults.put(Byte.TYPE, Byte.valueOf((byte) 0));
        primitiveDefaults.put(Float.TYPE, Float.valueOf(0f));
        primitiveDefaults.put(Double.TYPE, Double.valueOf(0d));
        primitiveDefaults.put(Long.TYPE, Long.valueOf(0L));
        primitiveDefaults.put(Boolean.TYPE, Boolean.FALSE);
        primitiveDefaults.put(Character.TYPE, Character.valueOf((char) 0));
    }

    /**
     * 处理实体与结果集
     * @param rs
     * @param clazz
     * @return
     */

    public static Object createBean(ResultSet rs,Class<?> clazz) throws IllegalAccessException, InstantiationException, SQLException {
        Object bean = clazz.newInstance();
        Field[] fields = clazz.getDeclaredFields();
        //创建操作结果集工具
        ResultSetMetaData metaData = rs.getMetaData();
        //获得列数并遍历
        for(int i =1;i<metaData.getColumnCount();i++){
            //得到列名
            String columnName = metaData.getColumnLabel(i);
            for(Field field : fields){
                field.setAccessible(true);
                //调用处理列与实体字段和类型处理得方法
                Field f = setProperty(columnName,field);
                if(f!=null){
                    //匹配使用哪种数据类型进行赋值
                    Object dateType = setTypevaluation(rs,columnName,f);
                    f.set(bean,dateType);
                }
            }

        }
        return bean;
    }

    //处理列和实体得名字及类型
    private static Field setProperty(String colName,Field field){
        //定义一个最终列名
        String colandemtiyName = null;
        //获得属性类型
        String fieldTypeName = field.getType().getName();
        /**
         * 判断是否有指定注解，用于匹配列名和属性名
         */
        if(field.isAnnotationPresent(ColumnAnno.class)){
            colandemtiyName = field.getAnnotation(ColumnAnno.class).value();
        }else{
            //如果没有注解直接使用属性名匹配
            colandemtiyName = field.getName();
        }
        //如果匹配通过表示可以赋值
        if(colandemtiyName.equals(colName)){
            return field;
        }
        return null;
    }

    //配置使用什么数据类型
    private static Object setTypevaluation(ResultSet resultSet ,String columnName,Field field) throws SQLException {
       Class typeName = field.getType();
       Object object = DataTypeProcessor.typeConvert(typeName,resultSet,columnName);
        //赋值前，要考虑列值为null的情况，如果列值,则赋默认初始值
        return object;
    }

}
