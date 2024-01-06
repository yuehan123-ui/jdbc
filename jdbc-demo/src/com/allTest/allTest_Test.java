package com.allTest;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

public class allTest_Test {
    /**
     * 需求：实现对数据表的增删改查
     */


    public static void main(String[] args)throws Exception {
        /**
         * 查询所有
         * 1. SQL：select * from tb_brand;
         * 2. 参数：不需要
         * 3. 结果：List<Brand> 每一条查询记录用一个对象封装 然后全部存储到一个集合中
         */
        //  加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("jdbc-demo/src/druid.properties"));
        //  获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        //  获取数据库连接 Connection
        Connection connection = dataSource.getConnection();
        //定义sql语句
        String sql = "select * from tb_brand";
        //获取PrepareStatement对象
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //设置参数
        //执行sql
        ResultSet result = preparedStatement.executeQuery();
        //处理结果
        ArrayList<brand> brands = new ArrayList<>();
        brand brand = null;
        while (result.next()){
            //获取数据
            int id = result.getInt("id");
            String brandName = result.getString("brand_name");
            String companyName = result.getString("company_name");
            int ordered = result.getInt("ordered");
            String description = result.getString("description");
            int status = result.getInt("status");
            //封装
            brand = new brand();
            brand.setId(id);
            brand.setBrandName(brandName);
            brand.setCompanyName(companyName);
            brand.setOrdered(ordered);
            brand.setDescription(description);
            brand.setStatus(status);
            //装进集合
            brands.add(brand);
        }
        System.out.println(brands);

        //释放资源
        result.close();
        connection.close();
        preparedStatement.close();
    }
    @Test
    public void TestAdd() throws Exception{
        /**
         *
         * 1. SQL：insert into tb_brand(brand_name, company_name, ordered, description, status) values(?,?,?,?,?);
         * 2. 参数：需要除id外所有参数  id:数据库自增
         * 3. 结果：返回一个布尔值
         */
        // 接收用户页面提交的参数
        String brandName = "香飘飘";
        String companyName = "香飘飘";
        int ordered = 1;
        String description = "绕地球一圈";
        int status = 1;
        //  加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("jdbc-demo/src/druid.properties"));
        //  获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        //  获取数据库连接 Connection
        Connection connection = dataSource.getConnection();
        //定义sql语句
        String sql = "insert into tb_brand(brand_name, company_name, ordered, description, status) values(?,?,?,?,?);";
        //获取preparestatement对象
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //设置参数
        preparedStatement.setString(1,brandName);// 1-> sql语句中增加的数据参数编号 setXXX XXX->数据类型
        preparedStatement.setString(2,companyName);
        preparedStatement.setInt(3,ordered);
        preparedStatement.setString(4,description);
        preparedStatement.setInt(5,status);
        //执行sql
        int count = preparedStatement.executeUpdate();//返回值 ： 影响的行数
        //处理结果
        System.out.println(count>0); // 影响行数>0  修改成功 返回true
        //释放资源
        preparedStatement.close();
        connection.close();

    }
    @Test
    public void TestUpdate() throws Exception{
        /**
         * 修改
         * sql语句  update tb_brand
         *          set brand_name  = ?,
         *          company_name= ?,
         *          ordered     = ?,
         *          description = ?,
         *          status      = ?
         *      where id = ?
         * 参数： 所有数据
         * 结果：布尔
         */
        //接收页面 提交的数据
        String brandName = "香飘飘";
        String companyName = "香飘飘";
        int ordered = 1000;
        String description = "绕地球三圈";
        int status = 1;
        int id = 6;
        //加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("jdbc-demo/src/druid.properties"));
        //获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        //获取数据库连接
        Connection connection = dataSource.getConnection();
        //定义sql
        String sql = "update tb_brand set brand_name = ?,company_name = ?,ordered = ?,description = ?,status = ? where id = ?";
        //获取prepareStatement对象
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //设置参数
        preparedStatement.setString(1,brandName);
        preparedStatement.setString(2,companyName);
        preparedStatement.setInt(3,ordered);
        preparedStatement.setString(4,description);
        preparedStatement.setInt(5,status);
        preparedStatement.setInt(6,id);
        //执行sql
        int count = preparedStatement.executeUpdate();
        System.out.println(count>0);
        //释放资源
        preparedStatement.close();
        connection.close();

    }
    @Test
    public void TestDelete() throws  Exception{
        /**
         * 删除
         * sql ： delete from tb_brand where id = ?
         * 参数 ：id
         * 结果 返回布尔
         */
        //接收用户数据
        int id = 6;
        //加载配置文件
        Properties properties = new Properties();
        properties.load(new FileInputStream("jdbc-demo/src/druid.properties"));
        //获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        //获取数据库连接
        Connection connection = dataSource.getConnection();
        //定义sql
        String sql = "delete from tb_brand where id = ?";
        //获取preparestatement对象
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //设置参数
        preparedStatement.setInt(1,id);
        //执行sql
        int count  = preparedStatement.executeUpdate();
        System.out.println(count>0);

        preparedStatement.close();
        connection.close();
    }
}
