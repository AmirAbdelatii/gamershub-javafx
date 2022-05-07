/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gaminghub;

import Entities.Category;
import Entities.Products;
import Services.CategoryService;
import Services.ProductsService;
import java.sql.SQLException;

/**
 *
 * @author meriam
 */
public class GamingHub {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        CategoryService cs = new CategoryService();
        Category c1= new Category("meriam","mhedhbi","jj.png",true);
        
        ProductsService ps=new ProductsService();
        //Products p1=new Products(6,54,"product1","aaa.png","desc",12.2f,true);
        try {
          //ps.add(p1);
          //p1.setId(8);
          //p1.setProductName("bb");
          //ps.update(p1);
          System.out.println(cs.show().toString());
          ps.delete(7);
          
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
