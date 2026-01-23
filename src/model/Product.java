package model;

import java.util.Scanner;

public class Product {
    private int id;
    private String name;
    private String brand;
    private double price;
    private int stock;
    public Product(){}
    public Product(String name, String brand, double price, int stock) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void inputData(Scanner sc) {
        this.name = inputName(sc);
        System.out.println("Nhập nhãn hiệu sản phẩm: ");
        this.brand = sc.nextLine();
        this.price = inputPrice(sc);
        this.stock = inputStock(sc);
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", brand=" + brand + ", price=" + price;
    }

    private String inputName(Scanner sc) {
        while (true) {
            System.out.println("Nhập vào tên sản phẩm: ");
            String name = sc.nextLine();
            if(name.length() <= 100) {
                return  name;
            }
            System.err.println("Số lượng ký tự không hợp lệ vui lòng nhập lại!");
        }
    }

    private double inputPrice(Scanner sc) {
        while (true) {
            System.out.println("Nhập vào giá sản phẩm: ");
            double price = Double.parseDouble(sc.nextLine());
            if(price > 0) {
                return price;
            }
            System.err.println("Giá không được âm! Vui lòng nhập lại!");
        }
    }

    private int inputStock(Scanner sc) {
        while (true) {
            System.out.println("Nhập vào số lượng sản phẩm: ");
            int stock = Integer.parseInt(sc.nextLine());
            if(stock > 0) {
                return stock;
            }
            System.err.println("Số lượng sản phẩm phải lớn hơn 0!");
        }
    }
}
