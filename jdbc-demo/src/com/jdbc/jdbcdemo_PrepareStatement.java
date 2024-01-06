package com.jdbc;

import java.sql.*;

public class jdbcdemo_PrepareStatement {
    /**
     *  PreparedStatement作用：
     * * 预编译SQL语句并执行：预防SQL注入问题
     */
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql:///db1?useSSL=false";
        String username = "root";
        String password = "1234";
        Connection conn = DriverManager.getConnection(url,username,password);

        String sql = "select * from tb_user where username = ? and password = ?";//sql语句中的值全部用？代替 然后再赋值
        //接收用户输入的 用户名和 密码
        String name = "zhangsan";
        String pwd = "'or'1'='1";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,name);//1 -> 参数编号 从第一个？开始
        pstmt.setString(2,pwd);//2->  第二个？

        //执行
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()){
            System.out.println("登录成功");
        }else {
            System.out.println("登陆失败");
        }

        //释放资源
        rs.close();
        pstmt.close();
        conn.close();

    }
}
