package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class jdbcdemo_ResultSet {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");//注册驱动
        String url = "jdbc:mysql:///db1?useSSL=false";
        String username = "root";
        String password = "1234";
        Connection connection = DriverManager.getConnection(url,username,password);//建立连接
        String sql = "select * from tb_user";//定义sql语句
        Statement statement = connection.createStatement();//获取statement对象
        ResultSet resultSet = statement.executeQuery(sql);//执行sql语句
        // 执行结果  遍历rs中所有结果
        while(resultSet.next()){ // 判断当前行是否有数据 有返回true
            String id  = resultSet.getString("id");  // getXXX（“字段名”）  XXX: 获取数据的类型
            String name  = resultSet.getString("username");
            String word = resultSet.getString("password");
            System.out.println(id);
            System.out.println(name);
            System.out.println(word);
            System.out.println("-----------");
        }
        // 释放资源
        resultSet.close();
        statement.close();
        connection.close();
    }

}
