
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    String url="jdbc:mysql://localhost:3306/productdb";
    String username="root";
    String pass="Soumojit@2007";
    Connection con=null;
    public ProductDao() {
        try {
            con =DriverManager.getConnection(url,username,pass);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void save(Product p) {
		String query="INSERT INTO product(name,type,place,warranty) VALUES (?,?,?,?)";
        try {
            PreparedStatement st=con.prepareStatement(query);
            st.setString(1, p.getName());
            st.setString(2, p.getType());
            st.setString(3, p.getPlace());
            st.setInt(4, p.getWarranty());
            st.execute();

        } catch (Exception e) {
          System.out.println(e);
        }

	}
    public List<Product> getAll() {
         List<Product> products =new ArrayList<>();
         String query="SELECT * FROM product";
        try {
            PreparedStatement st=con.prepareStatement(query);
            ResultSet rs=st.executeQuery();
            while(rs.next()){
                Product p=new Product();
                p.setName(rs.getString(2));
                p.setType(rs.getString(3));
                p.setPlace(rs.getString(4));
                p.setWarranty(rs.getInt(5));
                products.add(p);
            }

        } catch (Exception e) {
          System.out.println(e);
        }
         return products;
    }

	public Product getProduct(String name) {
		name=name.toLowerCase();
		String query="SELECT * FROM product WHERE LOWER(name)=?";
        try {
            PreparedStatement st=con.prepareStatement(query);
            st.setString(1, name);
            ResultSet rs=st.executeQuery();
            while(rs.next()){
            		Product p=new Product();
            		p.setName(rs.getString(2));
            		p.setType(rs.getString(3));
            		p.setPlace(rs.getString(4));
            		p.setWarranty(rs.getInt(5));
            		return p;
            }

        } catch (Exception e) {
          System.out.println(e);
        }
		return null;
	}

	public List<Product> findAllProductByPlace(String place) {
		place=place.toLowerCase();
		List<Product> productsByplace =new ArrayList<>();
		String query = "SELECT * FROM product WHERE LOWER(place) = ?";
		try {
			PreparedStatement st=con.prepareStatement(query);
			st.setString(1, place);
			ResultSet rs=st.executeQuery();
			while(rs.next()) {
				Product p = new Product();
				p.setName(rs.getString(2));
        		p.setType(rs.getString(3));
        		p.setPlace(rs.getString(4));
        		p.setWarranty(rs.getInt(5));
        		productsByplace.add(p);
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		
		return productsByplace;
	}

	public List<Product> OutOfWarrenty(int year) {
		List<Product> productsByWarrenty =new ArrayList<>();
		String query="SELECT * FROM product WHERE warranty < ?";
		try {
			PreparedStatement st=con.prepareStatement(query);
			st.setInt(1, year);
			ResultSet rs=st.executeQuery();
			while(rs.next()) {
				Product p = new Product();
				p.setName(rs.getString(2));
        		p.setType(rs.getString(3));
        		p.setPlace(rs.getString(4));
        		p.setWarranty(rs.getInt(5));
        		productsByWarrenty.add(p);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return productsByWarrenty;
	}

	public List<Product> findAllByText(String text) {
	    List<Product> products = new ArrayList<>();
	    String query = "SELECT * FROM product WHERE LOWER(name) LIKE ? OR LOWER(type) LIKE ? OR LOWER(place) LIKE ?";
	    try {
	        PreparedStatement st = con.prepareStatement(query);
	        String keyword = "%" + text.toLowerCase() + "%";
	        st.setString(1, keyword);
	        st.setString(2, keyword);
	        st.setString(3, keyword);

	        ResultSet rs = st.executeQuery();

	        while (rs.next()) {

	            Product p = new Product();

	            p.setName(rs.getString("name"));
	            p.setType(rs.getString("type"));
	            p.setPlace(rs.getString("place"));
	            p.setWarranty(rs.getInt("warranty"));

	            products.add(p);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return products;
	}
	
}
