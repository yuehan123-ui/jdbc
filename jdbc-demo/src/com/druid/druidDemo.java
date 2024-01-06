package com.druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

public class druidDemo {
    /**
     * Druid 数据库连接池
     */
    public static void main(String[] args) throws Exception{
        // 1. 导入jar包
        // 2. 定义配置文件  -> src目录下 druid.properties
        // 3, 加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("jdbc-demo/src/druid.properties"));
        // 4. 获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        // 5. 获取数据库连接 Connection
        Connection connection = dataSource.getConnection();
        System.out.println(connection);  //获取连接之后就可以进行其他操作了
    }
}
