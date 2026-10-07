<center>
<h2>Purchase Form</h2>
<form action="save.php" method="post">
<table border="0" cellpadding="8">
<tr>
<td>Name</td>
<td>
<input type="text"
       name="name"
       required>
</td>
</tr>
<tr>
<td>Email</td>
<td>
<input type="email"
       name="email"
       required>
</td>
</tr>
<tr?
<td>Phone</td>
<td>
<input type="tel"
       name="phone"
       pattern="[0-9]{10}"
       placeholder="10 digit number"
       required>
</td>
</tr>
<tr>
<td>Date of Purchase</td>
<td>
<input type="date"
       name="purchase_date"
       required>
</td>
</tr>
<tr>
<td>Quantity</td>
<td>
<input type="number"
       name="quantity"
       min="1"
       max="10"
       required>
</td>
</tr>
<tr>
<td>Payment Method</td>
<td>
<select name="payment_method">
<option value="Cash on Delivery">
Cash on Delivery
</option>
<option value="UPI">
UPI
</option>
<option value="Debit Card">
Debit Card
</option>
<option value="Credit Card">
Credit Card
</option>
</select>
</td>
</tr>
<tr>
<td>Delivery Address</td>
<td>
<textarea
name="address"
rows="4"
cols="30"
required></textarea>
</td>
</tr>
<tr>
<td colspan="2" align="center">
<input type="submit"
       value="Place Order">
<input type="reset"
       value="Reset">
</td>
</tr>
</table>
</form>
<br>
<a href="display.php">
View All Orders
</a>
</center>
<hr>
<center>
<h3>
Thank You for Visiting Our Online Shopping Website
</h3>
<p>
&#169; 2026 Epic Cart Shopping Store
</p>
</center>
