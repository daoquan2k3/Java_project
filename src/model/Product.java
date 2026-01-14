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
        System.out.println("Nhập vào tên sản phẩm: ");
        this.name = sc.nextLine();
        System.out.println("Nhập nhãn hiệu sản phẩm: ");
        this.brand = sc.nextLine();
        System.out.println("Nhập giá sản phẩm: ");
        this.price = Double.parseDouble(sc.nextLine());
        System.out.println("Nhập số lượng sản phẩm: ");
        this.stock = Integer.parseInt(sc.nextLine());
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", brand=" + brand + ", price=" + price;
    }
}
