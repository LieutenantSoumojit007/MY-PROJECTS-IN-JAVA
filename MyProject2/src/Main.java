import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ProductService service = new ProductService();
        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n========== PRODUCT MANAGEMENT ==========");
            System.out.println("1. View All Products");
            System.out.println("2. Search Product By Name");
            System.out.println("3. Search Product By Place");
            System.out.println("4. Out Of Warranty Products");
            System.out.println("5. Search By Text");
            System.out.println("6. Add Product");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {

                case 1:
                    List<Product> products = service.getAllProducts();

                    for (Product p : products) {
                        System.out.println(p);
                    }
                    break;

                case 2:
                    System.out.print("Enter Product Name: ");
                    String name = sc.nextLine();

                    Product product = service.getProduct(name);

                    if (product != null)
                        System.out.println(product);
                    else
                        System.out.println("Product Not Found.");

                    break;

                case 3:
                    System.out.print("Enter Place: ");
                    String place = sc.nextLine();

                    List<Product> placeProducts =
                            service.findAllProductByPlace(place);

                    if (placeProducts.isEmpty()) {
                        System.out.println("No Product Found.");
                    } else {
                        for (Product p : placeProducts) {
                            System.out.println(p);
                        }
                    }

                    break;

                case 4:
                    System.out.print("Enter Warranty Year: ");
                    int year = sc.nextInt();

                    List<Product> warranty =
                            service.OurOfWarrenty(year);

                    if (warranty.isEmpty()) {
                        System.out.println("No Product Found.");
                    } else {
                        for (Product p : warranty) {
                            System.out.println(p);
                        }
                    }

                    break;

                case 5:
                    System.out.print("Enter Search Text: ");
                    String text = sc.nextLine();

                    List<Product> search =
                            service.findAllByText(text);

                    if (search.isEmpty()) {
                        System.out.println("No Product Found.");
                    } else {
                        for (Product p : search) {
                            System.out.println(p);
                        }
                    }

                    break;

                case 6:

                    System.out.print("Product Name: ");
                    String pname = sc.nextLine();

                    System.out.print("Type: ");
                    String type = sc.nextLine();

                    System.out.print("Place: ");
                    String pplace = sc.nextLine();

                    System.out.print("Warranty: ");
                    int warranty1 = sc.nextInt();
                    sc.nextLine();

                    service.addProduct(
                            new Product(pname, type, pplace, warranty1));

                    System.out.println("Product Added Successfully.");

                    break;

                case 7:

                    System.out.println("Thank You!");
                    sc.close();
                    System.exit(0);

                default:

                    System.out.println("Invalid Choice.");
            }
        }
    }
}
