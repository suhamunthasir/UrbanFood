document.addEventListener("DOMContentLoaded", () => {
    fetch("http://localhost:8080/api/products")
        .then(response => response.json())
        .then(products => {
            const tableBody = document.querySelector("#products-table tbody");
            tableBody.innerHTML = "";
 console.log("hello");
            products.forEach(product => {
                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${product.productId}</td>
                    <td>${product.name}</td>
                    <td>Rs. ${product.price.toFixed(2)}</td>
                    <td>${product.supplier.name}</td>
                    <td><img src="/images/${product.imageUrl}" width="60"/>

</td>
                    <td><button onclick="deleteProduct(${product.productId})">Delete</button></td>
                `;
                tableBody.appendChild(row);
            });
        })
        .catch(error => console.error("Error fetching products:", error));
});

function deleteProduct(id) {
    fetch(`http://localhost:8080/api/products/${id}`, {
        method: "DELETE"
    })
    .then(() => {
        alert("Product deleted successfully");
        location.reload();
    })
    .catch(error => {
        console.error("Error deleting product:", error);
        alert("Failed to delete product.");
    });
}
