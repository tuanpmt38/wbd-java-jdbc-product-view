package services;

import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceIplm implements ProductService {

    @Override
    public List<Product> findAll () throws SQLException,ClassNotFoundException{

        List<Product> products = new ArrayList<>();

        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassCastException e){
            e.printStackTrace();
        }

        Connection conn = null;
        Statement stmt = null;
        try{
            String url = "jdbc:mysql://localhost:3306/AwesomeBusiness";
            conn = DriverManager.getConnection(url,"root","root");
            stmt = conn.createStatement();
            String sql = "SELECT `id`,`code`,`name` FROM `Product`";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setCode(rs.getString("code"));
                product.setName(rs.getString("name"));

                products.add(product);
            }
        }catch (SQLException e){
            e.getStackTrace();
        }
        stmt.cancel();
        conn.close();

        return products;
    }

}
