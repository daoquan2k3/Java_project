package model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

public class Invoice {
    private int id;
    private int customerId;
    private LocalDate creationDate;
    private double amount;
    public Invoice(){}
    public Invoice(int customerId, LocalDate creationDate, double amount) {
        this.customerId = customerId;
        this.creationDate = creationDate;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void inputData(Scanner sc) {
        System.out.println("Nhập vào mã khách hàng: ");
        this.customerId = Integer.parseInt(sc.nextLine());
        System.out.println("Nhập vào tổng tiền sản phẩm: ");
        this.amount = Double.parseDouble(sc.nextLine());
    }

    @Override
    public String toString() {
        return "Mã đơn hàng: " + id + " , Mã khách hàng: " + customerId + " , Ngày tạo đơn hàng: " + creationDate + " , Tổng tiền: " + amount;
    }
}
