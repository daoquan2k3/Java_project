package model;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Customer {
    private int id;
    private String name;
    private String phone;
    private String email;
    private String address;
    public Customer(){}
    public Customer(String name, String phone, String email, String address) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void inputData(Scanner sc) throws SQLException {
        System.out.println("Nhập vào tên khách hàng: ");
        this.name = sc.nextLine();
        System.out.println("Nhập vào số điện thoại khách hàng: ");
        this.phone = sc.nextLine();
        System.out.println("Nhập vào email khách hàng: ");
        this.email = sc.nextLine();
        System.out.println("Nhập vào địa chỉ khách hàng: ");
        this.address = sc.nextLine();
    }

    @Override
    public String toString() {
        return "Tên khách hàng: " + name + ", Số điện thoại: " + phone + ", Email: " + email + ", Address: " + address;
    }

    public void printCustomerTable(List<Customer> customers) {
        String headerFormat = "| %-5s | %-20s | %-12s | %-25s | %-20s |\n";
        String line = "+-------+----------------------+--------------+---------------------------+----------------------+\n";

        System.out.print(line);
        System.out.printf(headerFormat, "ID", "HỌ TÊN", "SỐ ĐT", "EMAIL", "ĐỊA CHỈ");
        System.out.print(line);

        for (Customer c : customers) {
            System.out.printf(headerFormat,
                    c.getId(),
                    truncate(c.getName(), 20),
                    c.getPhone(),
                    truncate(c.getEmail(), 25),
                    truncate(c.getAddress(), 20));
        }
        System.out.print(line);
    }

    // Hàm phụ để cắt bớt chữ nếu quá dài, tránh làm vỡ bảng
    private String truncate(String text, int size) {
        if (text == null) return "";
        return text.length() > size ? text.substring(0, size - 3) + "..." : text;
    }
}
