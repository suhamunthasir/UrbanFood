// js/categories.js

document.addEventListener("DOMContentLoaded", () => {
    const categoriesContainer = document.getElementById("categories-container");

    if (categoriesContainer) {
        fetch("http://localhost:8080/api/products") // Replace with your API endpoint
            .then(res => res.json())
            .then(data => {
                data.forEach(product => {
                    const div = document.createElement("div");
                    div.innerHTML = `
                        <h3>${product.name}</h3>
                        <p>${product.description}</p>
                        <p>Price: $${product.price}</p>
                        <button onclick="addToCart(${product.productId})">Add to Cart</button>
                    `;
                    categoriesContainer.appendChild(div);
                });
            })
            .catch(err => console.error("Error loading categories:", err));
    }
});
