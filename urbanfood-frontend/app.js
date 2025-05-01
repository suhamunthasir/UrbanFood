// js/app.js

// Add to Cart functionality (global)
function addToCart(productId) {
    let cart = JSON.parse(localStorage.getItem("cart")) || [];
    cart.push(productId);
    localStorage.setItem("cart", JSON.stringify(cart));
    alert("Product added to cart!");
}

// Load Cart dynamically
function loadCart() {
    const cartContainer = document.getElementById("cart-container");
    if (cartContainer) {
        const cart = JSON.parse(localStorage.getItem("cart")) || [];
        cart.forEach(productId => {
            fetch(`http://localhost:8080/api/products/${productId}`)
                .then(res => res.json())
                .then(product => {
                    const li = document.createElement("li");
                    li.innerHTML = `${product.name} - $${product.price}`;
                    cartContainer.appendChild(li);
                })
                .catch(err => console.error("Error loading cart:", err));
        });
    }
}

// Clear cart
function clearCart() {
    localStorage.removeItem("cart");
    alert("Cart cleared!");
}
