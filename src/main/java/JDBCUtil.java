package com.study.jdbc.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author yinyin
 * @create 2024/12/17 下午 5:49
 */
public class JDBCUtil {
    private JDBCUtil(){

    }

    public static DataSource dataSource;

    static{
        //获取properties文件
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
            dataSource= DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}
