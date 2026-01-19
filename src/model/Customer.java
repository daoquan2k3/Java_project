package model;

import java.util.Scanner;
import java.util.regex.Pattern;

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

    public void inputData(Scanner sc) {
        this.name = inputName(sc);
        this.phone = inputPhone(sc);
        this.email = inputEmail(sc);
        this.address = inputAddress(sc);
    }

    @Override
    public String toString() {
        return "Tên khách hàng: " + name + ", Số điện thoại: " + phone + ", Email: " + email + ", Address: " + address;
    }

    private String inputName(Scanner sc) {
        while (true) {
            System.out.println("Nhập vào tên khách hàng: ");
            String name = sc.nextLine();
            if(name.length() <= 100) {
                return  name;
            }
            System.err.println("Số lượng ký tự không hợp lệ vui lòng nhập lại!");
        }
    }

    private String inputPhone(Scanner sc) {
        String phoneRegex = "^0(3|5|7|8|9)[0-9]{8}$";
        while (true) {
            System.out.println("Nhập vào số điện thoại khách hàng: ");
            String phone = sc.nextLine();
            if(Pattern.matches(phoneRegex, phone)) {
                return phone;
            }
            System.err.println("Số điện thoại không đúng, vui lòng nhập lại!");
        }
    }

    private String inputEmail(Scanner sc) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        while (true) {
            System.out.println("Nhập vào địa chỉ email khách hàng: ");
            String email = sc.nextLine();
            if(Pattern.matches(emailRegex, email)) {
                return email;
            }
            System.err.println("Định dạng không đúng, vui lòng nhập lại!");
        }
    }

    private String inputAddress(Scanner sc) {
        while (true) {
            System.out.println("Nhập vào địa chỉ khách hàng: ");
            String address = sc.nextLine();
            if(address.length() < 255) {
                return  address;
            }
            System.err.println("Địa chỉ quá dài/ngắn vui lòng nhập lại!");
        }
    }
}