document.getElementById("login-form").addEventListener("submit", function(e) {
    e.preventDefault();

    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value;

    // Clear any previous session data first
    localStorage.removeItem("loggedInCustomer");
    localStorage.removeItem("customerEmail");
    localStorage.removeItem("customerId");

    // Admin Login
    if (email === "urban@gmail.com" && password === "urban") {
        window.location.href = "admin.html";
    } else {
        fetch("http://localhost:8080/api/customers")
            .then(response => {
                if (!response.ok) throw new Error("Failed to fetch customers");
                return response.json();
            })
            .then(customers => {
                const matchedCustomer = customers.find(
                    customer => customer.email === email && customer.password === password
                );

                if (matchedCustomer) {
                    // Set new session values
                    localStorage.setItem("loggedInCustomer", matchedCustomer.name);
                    localStorage.setItem("customerEmail", matchedCustomer.email);
                    localStorage.setItem("customerId", matchedCustomer.customerId);

                    window.location.href = "index.html";
                } else {
                    alert("Invalid email or password.");
                }
            })
            .catch(error => {
                console.error("Login error:", error);
                alert("Login failed. Please try again later.");
            });
    }
});
