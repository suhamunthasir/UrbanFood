// URL to the backend API for products
const productsApiUrl = 'http://localhost:8080/api/products';

// Function to display products dynamically
function displayProducts(products) {
    const productList = document.querySelector('.product-list');
    
    // Clear any previous products
    productList.innerHTML = '';
    
    // Loop through products and create HTML elements
    products.forEach(product => {
        const productDiv = document.createElement('div');
        productDiv.classList.add('product');
        
        productDiv.innerHTML = `
            <img src="path_to_image/${product.imageUrl}" alt="${product.name}">
            <h3>${product.name}</h3>
            <p>${product.description}</p>
            <p><strong>${product.price}</strong></p>
        `;
        
        productList.appendChild(productDiv);
    });
}

// Fetch products from the backend when the page loads
window.onload = function() {
    fetch(productsApiUrl)
        .then(response => response.json())
        .then(products => {
            displayProducts(products);
        })
        .catch(error => {
            console.error('Error fetching products:', error);
        });
};
// URL to the backend API for login
const loginApiUrl = 'http://localhost:8080/api/customers/login';

// Handle login form submission
document.getElementById('login-form').addEventListener('submit', function(event) {
    event.preventDefault();
    
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    
    // Create a JSON object with the email and password
    const loginData = {
        email: email,
        password: password
    };
    
    // Send a POST request to the backend to authenticate the user
    fetch(loginApiUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(loginData)
    })
    .then(response => {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error('Invalid login credentials');
        }
    })
    .then(data => {
        // Handle successful login (e.g., redirect to the home page or dashboard)
        alert('Login successful!');
        window.location.href = 'index.html';  // Redirect to home page after login
    })
    .catch(error => {
        alert('Login failed: ' + error.message);
    });
});
// URL to the backend API for registration
const registerApiUrl = 'http://localhost:8080/api/customers/register';

// Handle registration form submission
document.getElementById('register-form').addEventListener('submit', function(event) {
    event.preventDefault();
    
    const name = document.getElementById('name').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    
    // Create a JSON object with the registration data
    const registerData = {
        name: name,
        email: email,
        password: password
    };
    
    // Send a POST request to the backend to register the user
    fetch(registerApiUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(registerData)
    })
    .then(response => {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error('Registration failed');
        }
    })
    .then(data => {
        // Handle successful registration (e.g., redirect to login page)
        alert('Registration successful! Please log in.');
        window.location.href = 'login.html';  // Redirect to login page after successful registration
    })
    .catch(error => {
        alert('Registration failed: ' + error.message);
    });
});
