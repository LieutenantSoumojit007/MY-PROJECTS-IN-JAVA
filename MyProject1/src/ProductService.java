

import java.util.*;
import java.util.List;

public class ProductService {
    List<Product> products =new ArrayList<>();
    
    // this is the core java project now i am converting this in maven project with my sql database.
    public void addProduct(Product p) {
      products.add(p);
    }
    public List<Product> getAllProducts() {
      return products;
    }

    public Product getProduct(String a) {
      a=a.toLowerCase();
        for(Product p: products){
          String name=p.getName().toLowerCase();
          if(name.equals(name)) return p;
        }
        return null;
    }
    public List<Product> findAllProductByPlace(String b) {
      b=b.toLowerCase();
      List<Product> places =new ArrayList<>();
      for(Product p: products){
          String place=p.getPlace().toLowerCase();
          if(place.equals(b)) places.add(p);
        }
        if(places.size()!=0){
          return places;
        }
        return null;
    }

    
    public List<Product> OurOfWarrenty(int c) {
       List<Product> warrenty =new ArrayList<>();
      for(Product p: products){
          if(p.getWarranty()> c || p.getWarranty() ==c) warrenty.add(p);
        }
      if(warrenty.size()!=0){
          return warrenty;
        }
        return null;
    }
    public List<Product> findAllByText(String text) {

    text = text.toLowerCase();

    List<Product> textContain = new ArrayList<>();

    for (Product p : products) {

        String name = p.getName().toLowerCase();
        String type = p.getType().toLowerCase();
        String place = p.getPlace().toLowerCase();

        if (name.contains(text) ||
            type.contains(text) ||
            place.contains(text)) {

            textContain.add(p);
        }
    }

    return textContain;

    }
}
