package presentation;

import business.IProductService;
import business.Impl.ProductServiceImpl;

import java.sql.SQLException;
import java.util.Scanner;

public class ProductManageView {
    public static Scanner sc = new Scanner(System.in);
    private static final IProductService productService = new ProductServiceImpl();

    public static void productManageView() throws SQLException {
        while (true) {
            System.out.println("=============== QUẢN LÝ SẢN PHẨM ===============");
            System.out.println("1. Hiển thị danh sách sản phẩm");
            System.out.println("2. Thêm sản phẩm mới");
            System.out.println("3. Cập nhật thông tin sản phẩm");
            System.out.println("4. Xóa sản phẩm theo ID");
            System.out.println("5. Tìm kiếm sản phẩm theo Brand");
            System.out.println("6. Tìm kiếm sản phẩm theo khoảng giá");
            System.out.println("7. Tìm kiếm theo tồn kho");
            System.out.println("8. Quay lại menu chính");
            System.out.println("================================================");
            System.out.println("Nhập lựa chọn: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("Danh sách sản phẩm: ");
                    productService.displayAllProducts();
                    break;
                case 2:
                    productService.addProduct();
                    break;
                case 3:
                    productService.updateProduct();
                    break;
                case 4:
                    productService.deleteProduct();
                    break;
                case 5:
                    productService.searchProductByBrand();
                    break;
                case 6:
                    productService.searchProductByPrice();
                    break;
                case 7:
                    productService.searchProductByStock();
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Vui lòng chọn từ 1-8!");
            }
        }
    }
}
