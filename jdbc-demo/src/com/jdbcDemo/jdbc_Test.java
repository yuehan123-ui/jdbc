package com.jdbcDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class jdbc_Test {
    /**
     * 要求：查询 tb_user 用户表数据 并封装在User对象中 并且存储到arraylist集合中
     * 1.定义实体类 User
     * 2.查询数据 封装到User对象中
     * 3.将User对象存储到list集合中
     */
    public static void main(String[] args) throws Exception{
        String url = "jdbc:mysql:///db1?useSSL=false";
        String username = "root";
        String password = "1234";
        Connection connection = DriverManager.getConnection(url,username,password);//建立连接
        String sql = "select * from tb_user";//定义sql语句
        Statement statement = connection.createStatement();//获取statement对象
        ResultSet resultSet = statement.executeQuery(sql);//执行sql语句
        ArrayList<User> list = new ArrayList<>();
        while(resultSet.next()){
            String id = resultSet.getString("id");
            String name = resultSet.getString("username");
            String word = resultSet.getString("password");
            User user  = new User(id,name,word);
            list.add(user);
        }
        System.out.println(list);
        resultSet.close();
        statement.close();
        connection.close();
    }

}
