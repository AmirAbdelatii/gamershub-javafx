/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Category;
import Entities.Products;
import Utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author meriam
 */
public class ProductsService implements InterfaceService<Products> {

    public ProductsService() {
        con = MyDB.getInstance().getCon();
    }

    Connection con;
    Statement stm;

    @Override
    public void add(Products t) throws SQLException {
        String req = "INSERT INTO `products`(`category_id`, `name_product`, `price`, `quantity_stocked`, `image`, `description`, `creation_date`, `is_enabled`) VALUES (?,?,?,?,?,?,NOW(),?)";
        PreparedStatement pstm = con.prepareStatement(req);
        pstm.setInt(1, t.getCatId());
        pstm.setString(2, t.getProductName());
        pstm.setFloat(3, t.getPrice());
        pstm.setInt(4, t.getQuantityStocked());
        pstm.setString(5, t.getImage());
        pstm.setString(6, t.getDescription());
        pstm.setBoolean(7, true);
        pstm.executeUpdate();
    }

    @Override
    public void update(Products t) throws SQLException {
        String req = "UPDATE `products` SET `name_product`='"+t.getProductName()+
                "',`price`='"+t.getPrice()+"',`quantity_stocked`='"+t.getQuantityStocked()+
                "',`category_id`='"+t.getCatId()+
                "',`image`='"+t.getImage()+"',`description`='"+t.getDescription()+
                "',`modification_date`=NOW(),`is_enabled`="+t.getIsEnabled()+" WHERE `id`="+t.getId()+"";

        PreparedStatement pstm = con.prepareStatement(req);
        System.out.println(req);
        pstm.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        String req = "DELETE FROM `products` WHERE `id`="+id;
        PreparedStatement pstm = con.prepareStatement(req);
       // pstm.setInt(1, id);
        pstm.executeUpdate();
    }

    @Override
    public List<Products> show() throws SQLException {
        String req = "Select * from `products`";
        stm = con.createStatement();
        ResultSet rst = stm.executeQuery(req);
        System.out.println(rst.toString());
        List<Products> products = new ArrayList<Products>();
        while (rst.next()) {

            Products c = new Products(rst.getInt(1),rst.getInt(2),rst.getString(3),rst.getFloat(4), rst.getInt(5),rst.getString(6),rst.getString(7),rst.getBoolean(10));
            c.setCreationDate(rst.getDate(8));
            c.setModificationDate(rst.getDate(9));
            products.add(c);

        }
        return products;
    }

}
