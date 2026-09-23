<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 

<%@ page import="java.sql.*" %> 

<!DOCTYPE html> 

<html> 

<head> 

    <title>Order Status - Epic Cart</title> 

</head> 

<body> 

    <center> 

        <h1>EPIC CART SHOPPING WEBSITE</h1> 

        <p>Order Processing System</p> 

    </center> 

    <hr> 

 

    <% 

        // 1. Capture the form input data 

        String customerName = request.getParameter("customer_name"); 

        String email = request.getParameter("email"); 

        String phone = request.getParameter("phone"); 

        String purchaseDateStr = request.getParameter("purchase_date"); 

        String quantityStr = request.getParameter("quantity"); 

        String paymentMethod = request.getParameter("payment_method"); 

        String address = request.getParameter("address"); 

 

        if (customerName != null) { 

            Connection conn = null; 

            PreparedStatement pstmt = null; 

 

            try { 

                // 2. Establish connection directly to the 'mysql' database shown in your Services tab 

                Class.forName("com.mysql.cj.jdbc.Driver"); 

                String dbUrl = "jdbc:mysql://localhost:3306/mysql?useSSL=false&allowPublicKeyRetrieval=true&zeroDateTimeBehavior=convertToNull"; 

                conn = DriverManager.getConnection(dbUrl, "root", "test@123"); 

 

                // 3. Insert new record into customer_details 

                String sql = "INSERT INTO customer_details (customer_name, email, phone, purchase_date, quantity, payment_method, address) VALUES (?, ?, ?, ?, ?, ?, ?)"; 

                pstmt = conn.prepareStatement(sql); 

                 

                pstmt.setString(1, customerName); 

                pstmt.setString(2, email); 

                pstmt.setString(3, phone); 

                pstmt.setDate(4, Date.valueOf(purchaseDateStr)); 

                pstmt.setInt(5, Integer.parseInt(quantityStr)); 

                pstmt.setString(6, paymentMethod); 

                pstmt.setString(7, address); 

 

                int rowsInserted = pstmt.executeUpdate(); 

                if (rowsInserted > 0) { 

                    out.println("<h3 style='color:black; text-align:center;'>Success! Your order has been placed successfully.</h3>"); 

                } 

            } catch (Exception e) { 

                out.println("<h3 style='color:red; text-align:center;'>❌ Database Error: " + e.getMessage() + "</h3>"); 

            } finally { 

                if(pstmt != null) pstmt.close(); 

                if(conn != null) conn.close(); 

            } 

        } 

    %> 

 

    <!-- 4. Fetch and display all orders stored in customer_details --> 

    <center> 

        <h2>All Placed System Orders</h2> 

        <table border="1" cellpadding="8" style="width:90%; text-align:center; margin-bottom: 30px;"> 

            <tr > 

                <th>Order ID</th> 

                <th>Name</th> 

                <th>Email</th> 

                <th>Phone</th> 

                <th>Purchase Date</th> 

                <th>Qty</th> 

                <th>Payment Method</th> 

                <th>Delivery Address</th> 

            </tr> 

            <% 

                Connection displayConn = null; 

                Statement stmt = null; 

                ResultSet rs = null; 

                try { 

                    Class.forName("com.mysql.cj.jdbc.Driver"); 

                    String dbUrl = "jdbc:mysql://localhost:3306/mysql?useSSL=false&allowPublicKeyRetrieval=true&zeroDateTimeBehavior=convertToNull"; 

                    displayConn = DriverManager.getConnection(dbUrl, "root", "test@123"); 

                     

                    stmt = displayConn.createStatement(); 

                    rs = stmt.executeQuery("SELECT * FROM customer_details ORDER BY order_id DESC"); 

                     

                    while(rs.next()) { 

            %> 

            <tr> 

                <td><%= rs.getInt("order_id") %></td> 

                <td><%= rs.getString("customer_name") %></td> 

                <td><%= rs.getString("email") %></td> 

                <td><%= rs.getString("phone") %></td> 

                <td><%= rs.getDate("purchase_date") %></td> 

                <td><%= rs.getInt("quantity") %></td> 

                <td><%= rs.getString("payment_method") %></td> 

                <td><%= rs.getString("address") %></td> 

            </tr> 

            <%  

                    } 

                } catch(Exception e) { 

                    out.println("<tr><td colspan='8' style='color:red;'>Failed to load table logs: " + e.getMessage() + "</td></tr>"); 

                } finally { 

                    if(rs != null) rs.close(); 

                    if(stmt != null) stmt.close(); 

                    if(displayConn != null) displayConn.close(); 

                } 

            %> 

        </table> 

         

        <br> 

        <a href="index.html"Back to Shopping</a> 

    </center> 

</body> 

</html> 