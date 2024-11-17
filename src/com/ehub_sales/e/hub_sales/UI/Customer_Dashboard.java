package com.ehub_sales.e.hub_sales.UI;

import javax.swing.*;
import com.ehub_sales.e.hub_sales.Sales.Inventory;
import com.ehub_sales.e.hub_sales.Sales.Product;
import com.ehub_sales.e.hub_sales.Users.Customer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Customer_Dashboard extends javax.swing.JFrame {
    private Customer customer;
    private Inventory inventory;
    private DefaultListModel<Product> productListModel;
    private JList<Product> productList;
    private JTextArea productDetailsArea;

    public Customer_Dashboard(Customer customer) {
        this.customer = customer;
        this.inventory = new Inventory();
        initComponents();
        customizeComponents();
        loadProducts();
    }

    private void customizeComponents() {
        setTitle("Customer Dashboard");
        setSize(800, 600);
        setLocationRelativeTo(null);
        productListModel = new DefaultListModel<>();
        productList = new JList<>(productListModel);
        productDetailsArea = new JTextArea();
        productDetailsArea.setEditable(false);
    }

    private void loadProducts() {
        productListModel.clear();
        for (Product product : inventory.getProducts()) {
            productListModel.addElement(product);
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

        jLabel1.setText("Customer");

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
                .addContainerGap(229, Short.MAX_VALUE))
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
                .addGap(0, 93, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void checkoutActionPerformed(ActionEvent evt) {
        if (customer.getCart().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Your cart is empty!", "Checkout Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // Logic to proceed to checkout
            customer.checkout();
            JOptionPane.showMessageDialog(this, "Checkout successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void addToCartActionPerformed(ActionEvent evt) {
        Product selectedProduct = productList.getSelectedValue();
        if (selectedProduct != null) {
            customer.addToCart(selectedProduct);
            JOptionPane.showMessageDialog(this, selectedProduct.getProductName() + " added to cart.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a product to add to cart.", "Selection Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeFromCartActionPerformed(ActionEvent evt) {
        Product selectedProduct = productList.getSelectedValue();
        if (selectedProduct != null) {
            customer.removeFromCart(selectedProduct);
            JOptionPane.showMessageDialog(this, selectedProduct.getProductName() + " removed from cart.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a product to remove from cart.", "Selection Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        Login_Form login = Login_Form.getInstance();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    // Separate action method for viewing products
    private void viewProductsAction() {
        // Code to display available products
    }

    // Separate action method for making a purchase
    private void makePurchaseAction() {
        // Code to handle purchasing a product
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
