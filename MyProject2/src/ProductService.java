

import java.util.List;

import Product;
import ProductDao;

public class ProductService { 
    // this is my sql part 
    ProductDao db = new ProductDao();
    public void addProduct(Product p) {
       db.save(p);
      }
    public List<Product> getAllProducts(){
      return db.getAll();
    }
    
    public Product getProduct(String name) {
    		return db.getProduct(name);
    }
    
    public List<Product> findAllProductByPlace(String place){
    		return db.findAllProductByPlace(place);
    }
    
    public List<Product> OurOfWarrenty(int year){
    	 return db.OutOfWarrenty(year);
    }
    
    public List<Product> findAllByText(String text){
   	 return db.findAllByText(text);
   }

}
