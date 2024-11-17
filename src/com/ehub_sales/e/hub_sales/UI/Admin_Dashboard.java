package com.ehub_sales.e.hub_sales.UI;

import javax.swing.*;
import com.ehub_sales.e.hub_sales.Sales.Inventory;
import com.ehub_sales.e.hub_sales.Sales.Product;
import com.ehub_sales.e.hub_sales.Users.Admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Admin_Dashboard extends javax.swing.JFrame {
    private Admin admin;
    private Inventory inventory;
    private DefaultListModel<Product> productListModel;
    private JList<Product> productList;

    public Admin_Dashboard(Admin admin) {
        this.admin = admin;
        this.inventory = new Inventory();
        initComponents();
        customizeComponents();
        loadProducts();
    }

    private void customizeComponents() {
        setTitle("Admin Dashboard");
        setSize(800, 600);
        setLocationRelativeTo(null);
        productListModel = new DefaultListModel<>();
        productList = new JList<>(productListModel);
    }

    private void loadProducts() {
        productListModel.clear();
        for (Product product : inventory.getProducts()) {
            productListModel.addElement(product);
        }
    }
    
    private void addProductActionPerformed(ActionEvent evt) {
        // Logic to add a new product
        String productName = JOptionPane.showInputDialog(this, "Enter product name:");
        String productDescription = JOptionPane.showInputDialog(this, "Enter product description:");
        String priceStr = JOptionPane.showInputDialog(this, "Enter product price:");
        String quantityStr = JOptionPane.showInputDialog(this, "Enter product quantity:");

        if (productName != null && productDescription != null && priceStr != null && quantityStr != null) {
            try {
                double price = Double.parseDouble(priceStr);
                int quantity = Integer.parseInt(quantityStr);
                Product newProduct = new Product(productName, productDescription, price, quantity);
                inventory.addProduct(newProduct);
                loadProducts();
                JOptionPane.showMessageDialog(this, "Product added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid price or quantity.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void updateProductActionPerformed(ActionEvent evt) {
        Product selectedProduct = productList.getSelectedValue();
        if (selectedProduct != null) {
            String newProductName = JOptionPane.showInputDialog(this, "Enter new product name:", selectedProduct.getProductName());
            String newProductDescription = JOptionPane.showInputDialog(this, "Enter new product description:", selectedProduct.getDescription());
            String newPriceStr = JOptionPane.showInputDialog(this, "Enter new product price:", selectedProduct.getPrice());
            String newQuantityStr = JOptionPane.showInputDialog(this, "Enter new product quantity:", selectedProduct.getQuantity());

            if (newProductName != null && newProductDescription != null && newPriceStr != null && newQuantityStr != null) {
                try {
                    double newPrice = Double.parseDouble(newPriceStr);
                    int newQuantity = Integer.parseInt(newQuantityStr);
                    selectedProduct.setProductName(newProductName);
                    selectedProduct.setDescription(newProductDescription);
                    selectedProduct.setPrice(newPrice);
                    selectedProduct.setQuantity(newQuantity);
                    loadProducts();
                    JOptionPane.showMessageDialog(this, "Product updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Invalid price or quantity.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a product to update.", "Selection Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteProductActionPerformed(ActionEvent evt) {
        Product selectedProduct = productList.getSelectedValue();
        if (selectedProduct != null) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete " + selectedProduct.getProductName() + "?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                inventory.removeProduct(selectedProduct);
                loadProducts();
                JOptionPane.showMessageDialog(this, "Product deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a product to delete.", "Selection Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setText("Admin");

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(btnBack))
                .addContainerGap(207, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBack)
                .addContainerGap(249, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 115, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        Login_Form login = Login_Form.getInstance();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
