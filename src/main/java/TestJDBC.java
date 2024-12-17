import java.sql.*;

public class TestJDBC {
    public static void main(String[] args) {
        Connection connection = null;
        ResultSet resultSet1 = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;
        PreparedStatement preparedStatement3 = null;
        PreparedStatement preparedStatement4 = null;

        try {
            // 1. 加载并注册 JDBC 驱动,创建数据库连接
            connection = JDBCUtil.getConnection();
            System.out.println(connection);

            //2. 创建 Statement 对象执行SQL查询
            String sql1="insert into customer values (?,?)";  //增
            preparedStatement1 = connection.prepareStatement(sql1);
            preparedStatement1.setInt(1,103);
            preparedStatement1.setString(2,"dd");

            String sql2="delete from  customer where c_id=? ";  //删
            preparedStatement2 = connection.prepareStatement(sql2);
            preparedStatement2.setInt(1,102);

            String sql3="update customer set c_name=? where c_id=?";  //改
            preparedStatement3 = connection.prepareStatement(sql3);
            preparedStatement3.setString(1,"Karry");
            preparedStatement3.setInt(2,101);

            String sql4="select * from customer where c_id=?";  //查
            preparedStatement4 = connection.prepareStatement(sql4);
            preparedStatement4.setInt(1,107);

            //3. 执行查询并获取结果
            preparedStatement1.executeUpdate();
            preparedStatement2.executeUpdate();
            preparedStatement3.executeUpdate();
            resultSet1 = preparedStatement4.executeQuery();

            //4. 处理查询结果并打印
            if(resultSet1.next()){
                int cId = resultSet1.getInt("c_id");
                String cName = resultSet1.getString("c_name");
                System.out.println(cId+"："+cName);
            }

        } catch (SQLException e) {
            // 处理 JDBC 错误
            System.out.println("JDBC 错误: " + e.getMessage());
        } finally {
            // 3. 确保连接被关闭
            try {
                if (connection != null) {
                    resultSet1.close();
                    preparedStatement1.close();
                    preparedStatement2.close();
                    preparedStatement3.close();
                    preparedStatement4.close();
                    connection.close();
                    System.out.println("连接已关闭。");
                }
            } catch (SQLException e) {
                System.out.println("关闭连接时发生错误: " + e.getMessage());
            }
        }
    }
}
