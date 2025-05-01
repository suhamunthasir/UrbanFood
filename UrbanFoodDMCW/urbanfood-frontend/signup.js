// js/signup.js

document.addEventListener("DOMContentLoaded", () => {
    const signupForm = document.getElementById("signup-form");

    if (signupForm) {
        signupForm.addEventListener("submit", (e) => {
            e.preventDefault();
            const name = document.getElementById("name").value;
            const email = document.getElementById("email").value;
            const password = document.getElementById("password").value;

            fetch("http://localhost:8080/api/customers/register", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ name, email, password }),
            })
                .then(res => {
                    if (res.status === 400) {
                        alert("Email already in use");
                    } else {
                        return res.json();
                    }
                })
                .then(data => {
                    alert("Signup successful!");
                    window.location.href = "login.html"; // Redirect to login page
                })
                .catch(err => console.error("Error during signup:", err));
        });
    }
});
