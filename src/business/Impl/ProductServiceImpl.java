package business.Impl;

import business.IProductService;
import dao.IAdminDAO;
import dao.IProductDAO;
import dao.Impl.ProductDAOImpl;
import model.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ProductServiceImpl implements IProductService {
    public static Scanner sc = new Scanner(System.in);
    public static final IProductDAO productDAO = new ProductDAOImpl();


    @Override
    public void displayAllProducts() throws SQLException {
        List<Product> productList = productDAO.displayAllProducts();
        productList.forEach(System.out::println);
    }

    @Override
    public void addProduct() throws SQLException {
        Product product = new Product();
        product.inputData(sc);
        boolean result = productDAO.addProduct(product);
        if (result) {
            System.out.println("Thêm sản phẩm thành công!");
        } else {
            System.err.println("Có lỗi xuất hiện trong quá trình thêm mới!");
        }
    }

    @Override
    public void updateProduct() throws SQLException {
        System.out.println("Nhập vào mã sản phẩm cần cập nhật: ");
        int productId = Integer.parseInt(sc.nextLine());
        Product product = productDAO.findProductById(productId);
        if (product == null) {
            System.err.println("Mã sản phẩm không tồn tại!");
            return;
        }
        boolean isExist = true;
        while (isExist) {
            System.out.println("1. Cập nhật tên sản phẩm");
            System.out.println("2. Cập nhật nhãn hàng");
            System.out.println("3. Cập nhật giá sản phẩm");
            System.out.println("4. Cập nhật số lượng sản phẩm");
            System.out.println("5. Thoát");
            System.out.println("Nhập lựa chọn: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("Nhập vào tên sản phẩm mới: ");
                    product.setName(sc.nextLine());
                    break;
                case 2:
                    System.out.println("Nhập vào nhãn hàng mới: ");
                    product.setBrand(sc.nextLine());
                    break;
                case 3:
                    System.out.println("Nhập vào giá mới: ");
                    product.setPrice(Double.parseDouble(sc.nextLine()));
                    break;
                case 4:
                    System.out.println("Nhập vào số lượng mới: ");
                    product.setStock(Integer.parseInt(sc.nextLine()));
                    break;
                case 5:
                    isExist = false;
                    break;
                default:
                    System.err.println("Vui lòng chọn từ 1-5!");
            }
        }
        boolean result = productDAO.updateProduct(product);
        if (result) {
            System.out.println("Cập nhật thành công!");
        } else {
            System.err.println("Có lỗi trong quá trình cập nhật!");
        }
    }

    @Override
    public void deleteProduct() throws SQLException {
        System.out.println("Nhập vào mã sản phẩm cần xóa: ");
        int productId = Integer.parseInt(sc.nextLine());
        Product product = productDAO.findProductById(productId);
        if (product == null) {
            System.out.println("Mã sản phẩm không tồn tại!");
            return;
        }
        boolean result = productDAO.deleteProductById(productId);
        if (result) {
            System.out.println("Xóa thành công");
        } else {
            System.err.println("Có lỗi trong quá trình xóa!");
        }
    }

    @Override
    public void searchProductByBrand() throws SQLException {
        System.out.println("Nhập vào nhãn hàng cần tìm: ");
        String brand = sc.nextLine();
        List<Product> productList = productDAO.searchProductByBrand(brand);
        if (productList.isEmpty()) {
            System.out.println("Không có sản phẩm nào thuộc nhãn hàng: " + brand);
            return;
        }
        productList.forEach(System.out::println);
    }

    @Override
    public void searchProductByPrice() throws SQLException {
        System.out.println("Nhập vào khoảng giá cần tìm");
        System.out.println("Giá khởi điểm: ");
        double startPrice = Double.parseDouble(sc.nextLine());
        System.out.println("Giá kết thúc: ");
        double endPrice = Double.parseDouble(sc.nextLine());
        List<Product> productList = productDAO.searchProductByPrice(startPrice, endPrice);
        if (productList.isEmpty()) {
            System.err.println("Không có sản phẩm nào trong khoảng giá này!");
            return;
        }
        productList.forEach(System.out::println);
    }

    @Override
    public void searchProductByStock() throws SQLException {
        System.out.println("Nhập vào số lượng sản phẩm: ");
        int productStock = Integer.parseInt(sc.nextLine());
        List<Product> productList = productDAO.searchProductByStock(productStock);
        if (productList.isEmpty()) {
            System.err.printf("Không có sản phẩm nào còn dư %d sản phẩm\n", productStock);
            return;
        }
        productList.forEach(System.out::println);
    }
}
