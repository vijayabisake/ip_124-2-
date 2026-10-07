<!DOCTYPE html>
<html>
<head>
    <title>Book Information</title>
    <style>
        body {
            font-family: Arial;
            background-color: #f2f2f2;
        }
        h1 {
            text-align: center;
        }
        table {
            width: 80%;
            margin: auto;
           border-collapse: collapse;
        }
        th, td {
            border: 1px solid black;
            padding: 10px;
            text-align: center;
        }
        th {
            background-color: lightblue;
        }
    </style>
</head>
<body>
<h1>Book Information</h1>
<?php
$xml = simplexml_load_file("books.xml");
if ($xml === false) {
    echo "<p style='text-align:center;color:red;'>Unable to read XML file.</p>";
} else {
?>
<table>
    <tr>
        <th>ID</th>
        <th>Book Title</th>
        <th>Author</th>
        <th>Price</th>
        <th>Category</th>
    </tr>
<?php
foreach ($xml->book as $book) {
?>
    <tr>
        <td><?php echo $book->id; ?></td>
        <td><?php echo $book->title; ?></td>
        <td><?php echo $book->author; ?></td>
        <td>₹<?php echo $book->price; ?></td>
        <td><?php echo $book->category; ?></td>
    </tr>
<?php
}
?>
</table>
<?php
}
</body>
</html>

