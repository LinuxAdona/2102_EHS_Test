package com.ehub_sales.e.hub_sales.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.ehub_sales.e.hub_sales.Sales.Product;
import com.ehub_sales.e.hub_sales.Sales.SalesReport;
import com.ehub_sales.e.hub_sales.Users.Customer;
import com.ehub_sales.e.hub_sales.Users.Supplier;

public class DatabaseManager {
    private String dbUrl = "jdbc:mysql://localhost:3306/oop_ehub_sales";
    private String dbUser  = "root";
    private String dbPassword = "";

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUser , dbPassword);
    }

    public void addProduct(Product product) {
        String query = "INSERT INTO products (productId, productName, description, price, quantity, supplierId) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = connect(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, product.getProductId());
            ps.setString(2, product.getProductName());
            ps.setString(3, product.getDescription());
            ps.setDouble(4, product.getPrice());
            ps.setInt(5, product.getQuantity());
            ps.setString(6, product.getSupplierId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProduct(Product product) {
        String query = "UPDATE products SET productName = ?, description = ?, price = ?, quantity = ? WHERE productId = ?";
        try (Connection con = connect(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, product.getProductName());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getQuantity());
            ps.setString(5, product.getProductId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(String productId) {
        String query = "DELETE FROM products WHERE productId = ?";
        try (Connection con = connect(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, productId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products";
        try (Connection con = connect(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Product product = new Product(rs.getString("productId"), rs.getString("productName"), rs.getString("description"),
                        rs.getDouble("price"), rs.getInt("quantity"), rs.getString("supplierId"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public void addCustomer(Customer customer) {
        String query = "INSERT INTO customers (userId, username, password) VALUES (?, ?, ?)";
        try (Connection con = connect(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, customer.getUserId());
            ps.setString(2, customer.getUsername());
            ps.setString(3, customer.getPassword());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM customers";
        try (Connection con = connect(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Customer customer = new Customer(rs.getString("userId"), rs.getString("username"), rs.getString("password"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public void addSupplier(Supplier supplier) {
        String query = "INSERT INTO suppliers (userId, username, password) VALUES (?, ?, ?)";
        try (Connection con = connect(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, supplier.getUserId());
            ps.setString(2, supplier.getUsername());
            ps.setString(3, supplier.getPassword());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Supplier> getAllSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        String query = "SELECT * FROM suppliers";
        try (Connection con = connect(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Supplier supplier = new Supplier(rs.getString("userId"), rs.getString("username"), rs.getString("password"));
                suppliers.add(supplier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suppliers;
    }

    public void recordSale(String customerId, String productId, int quantity) {
        String query = "INSERT INTO sales (customerId, productId, quantity) VALUES (?, ?, ?)";
        try (Connection con = connect(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, customerId);
            ps.setString(2, productId);
            ps.setInt(3, quantity);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<SalesReport> getSalesReport() {
        List<SalesReport> sales = new ArrayList<>();
        String query = "SELECT * FROM sales";
        try (Connection con = connect(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                SalesReport sale = new SalesReport(rs.getString("saleId"), rs.getString("customerId"), rs.getString("productId"), rs.getInt("quantity"));
                sales.add(sale);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sales;
    }
}

   