package com.ehub_sales.e.hub_sales.UI;

import javax.swing.*;
import com.ehub_sales.e.hub_sales.Sales.Inventory;
import com.ehub_sales.e.hub_sales.Sales.Product;
import com.ehub_sales.e.hub_sales.Users.Customer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.List;

public class Customer_Dashboard extends javax.swing.JFrame {
    private Customer customer;
    private Inventory inventory;
    private DefaultListModel<Product> productListModel;
    private JList<Product> productList;
    private JTextArea productDetailsArea;

    public Customer_Dashboard(Customer customer) {
        this.customer = customer;
        this.inventory = new Inventory();
        loadProducts();
        setupListeners();
    }

    private void loadProducts() {
        List<Product> products = inventory.getProducts();
        DefaultListModel<Product> model = new DefaultListModel<>();
        for (Product product : products) {
            model.addElement(product);
        }
        List_Products.setModel(model);
    }

    private void setupListeners() {
        List_Products.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Product selectedProduct = List_Products.getSelectedValue();
                if (selectedProduct != null) {
                    displayProductDetails(selectedProduct);
                }
            }
        });

        btnAddToCart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                addToCart();
            }
        });

        btnRemoveFromCart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                removeFromCart();
            }
        });
    }

    private void displayProductDetails(Product product) {
        lblProductName.setText(product.getName());
        lblProductDesc.setText(product.getDescription());
        // Set the image if available
        lblProductImage.setIcon(new ImageIcon("path/to/image/" + product.getProductId() + ".png")); // Example path
    }

    private void addToCart() {
        Product selectedProduct = List_Products.getSelectedValue();
        if (selectedProduct != null) {
            int quantity = 1; // You can prompt the user for quantity input
            customer.addProductToCart(selectedProduct.getProductId(), quantity);
            updateCartTable();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a product to add to the cart.", "No Product Selected", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void removeFromCart() {
        int selectedRow = Table_ShoppingCart.getSelectedRow();
        if (selectedRow != -1) {
            String productId = (String) Table_ShoppingCart.getValueAt(selectedRow, 0); // Assuming ID is in the first column
            customer.removeProductFromCart(productId);
            updateCartTable();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a product from the cart to remove.", "No Product Selected", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void updateCartTable() {
        cartTableModel = new DefaultTableModel(new Object[]{"ID", "Name", "Quantity", "Price"}, 0);
        for (Product product : customer.getCart().getCartItems().keySet()) {
            int quantity = customer.getCart().getCartItems().get(product);
            cartTableModel.addRow(new Object[]{product.getProductId(), product.getName(), quantity, product.getPrice()});
        }
        Table_ShoppingCart.setModel(cartTableModel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MainPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table_ShoppingCart = new javax.swing.JTable();
        lblShoppingCart = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        List_Products = new javax.swing.JList<>();
        lblProductImage = new javax.swing.JLabel();
        lblProductName = new javax.swing.JLabel();
        lblProductDesc = new javax.swing.JLabel();
        lblSupplierName = new javax.swing.JLabel();
        btnCheckOut = new javax.swing.JButton();
        btnAddToCart = new javax.swing.JButton();
        btnRemoveFromCart = new javax.swing.JButton();
        btnBack1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        MainPanel.setBackground(new java.awt.Color(248, 248, 248));

        Table_ShoppingCart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Quantity", "Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(Table_ShoppingCart);

        lblShoppingCart.setFont(new java.awt.Font("Helvetica", 1, 18)); // NOI18N
        lblShoppingCart.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblShoppingCart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Cart.png"))); // NOI18N
        lblShoppingCart.setText("Shopping Cart");

        List_Products.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Product 1", "Product 2", "Product 3", "Product 4" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(List_Products);

        lblProductImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProductImage.setText("ProductImage");

        lblProductName.setFont(new java.awt.Font("Helvetica", 1, 24)); // NOI18N
        lblProductName.setText("Product Name");

        lblProductDesc.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
        lblProductDesc.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblProductDesc.setText("Product Description");

        lblSupplierName.setFont(new java.awt.Font("Helvetica", 1, 14)); // NOI18N
        lblSupplierName.setText("Supplier Name");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblProductImage, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblProductName)
                            .addComponent(lblProductDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(lblSupplierName)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblProductImage, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(lblProductName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblProductDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblSupplierName)))
                .addGap(252, 252, 252))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2))
        );

        btnCheckOut.setBackground(new java.awt.Color(153, 153, 255));
        btnCheckOut.setFont(new java.awt.Font("Helvetica", 1, 14)); // NOI18N
        btnCheckOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/CheckOut.png"))); // NOI18N
        btnCheckOut.setText("Check Out");
        btnCheckOut.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCheckOut.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btnCheckOut.setIconTextGap(10);
        btnCheckOut.setMargin(new java.awt.Insets(2, 0, 3, 0));
        btnCheckOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckOutActionPerformed(evt);
            }
        });

        btnAddToCart.setBackground(new java.awt.Color(153, 153, 255));
        btnAddToCart.setFont(new java.awt.Font("Helvetica", 1, 12)); // NOI18N
        btnAddToCart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/AddToCart.png"))); // NOI18N
        btnAddToCart.setText("Add To Cart");
        btnAddToCart.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddToCart.setMargin(new java.awt.Insets(2, 0, 3, 0));
        btnAddToCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddToCartActionPerformed(evt);
            }
        });

        btnRemoveFromCart.setBackground(new java.awt.Color(153, 153, 255));
        btnRemoveFromCart.setFont(new java.awt.Font("Helvetica", 1, 12)); // NOI18N
        btnRemoveFromCart.setText("Remove");
        btnRemoveFromCart.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRemoveFromCart.setMargin(new java.awt.Insets(2, 0, 3, 0));
        btnRemoveFromCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveFromCartActionPerformed(evt);
            }
        });

        btnBack1.setBackground(new java.awt.Color(153, 153, 255));
        btnBack1.setFont(new java.awt.Font("Helvetica", 1, 12)); // NOI18N
        btnBack1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/back.png"))); // NOI18N
        btnBack1.setText("Go Back");
        btnBack1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBack1.setMargin(new java.awt.Insets(2, 0, 3, 0));
        btnBack1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBack1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MainPanelLayout = new javax.swing.GroupLayout(MainPanel);
        MainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCheckOut, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                        .addComponent(btnBack1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblShoppingCart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addComponent(btnAddToCart, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRemoveFromCart, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 722, Short.MAX_VALUE))
        );
        MainPanelLayout.setVerticalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblShoppingCart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBack1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAddToCart, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRemoveFromCart, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCheckOut, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCheckOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckOutActionPerformed
        Login_Form login = Login_Form.getInstance();
        login.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnCheckOutActionPerformed

    private void btnBack1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBack1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBack1ActionPerformed

    private void btnAddToCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddToCartActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddToCartActionPerformed

    private void btnRemoveFromCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveFromCartActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRemoveFromCartActionPerformed

    
    
    // Separate action method for viewing products
    private void viewProductsAction() {
        // Code to display available products
    }

    // Separate action method for making a purchase
    private void makePurchaseAction() {
        // Code to handle purchasing a product
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> List_Products;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JTable Table_ShoppingCart;
    private javax.swing.JButton btnAddToCart;
    private javax.swing.JButton btnBack1;
    private javax.swing.JButton btnCheckOut;
    private javax.swing.JButton btnRemoveFromCart;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblProductDesc;
    private javax.swing.JLabel lblProductImage;
    private javax.swing.JLabel lblProductName;
    private javax.swing.JLabel lblShoppingCart;
    private javax.swing.JLabel lblSupplierName;
    // End of variables declaration//GEN-END:variables
}
