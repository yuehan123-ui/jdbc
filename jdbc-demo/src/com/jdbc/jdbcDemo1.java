package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class jdbcDemo1 {
    public static void main(String[] args) throws Exception {
        //1.注册驱动 (可省略)
        Class.forName("com.mysql.jdbc.Driver");
        //2.获取链接 (连接本机MySQL且端口号为默认3306 可省略127.0.0.1：3306)
        String url = "jdbc:mysql://127.0.0.1:3306/db1?useSSL=false"; // String url = "jdbc:mysql:///db1"
        String username = "root";
        String password = "1234";
        Connection connection = DriverManager.getConnection(url,username,password);
        //3.定义sql语句
        String sql = "INSERT into tb_user(id)VALUES(2005)";
        //4.获取执行sql的对象statement
        Statement stmt = connection.createStatement();
        //5.执行sql
        int cont = stmt.executeUpdate(sql);//受影响行数
        //6.处理结果
        System.out.println(cont);
        //7.释放资源
        stmt.close();
        connection.close();
    }
}
