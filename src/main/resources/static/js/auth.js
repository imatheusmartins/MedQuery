import { apiPost } from './api.js';
import { setRole, clearRole } from './roles.js';

const signInBtn = document.getElementById("signIn");
const signUpBtn = document.getElementById("signUp");
const container = document.querySelector(".container");

signInBtn.addEventListener("click", () => {
    container.classList.remove("right-panel-active");
});

signUpBtn.addEventListener("click", () => {
    container.classList.add("right-panel-active");
});

export function handleLogin() {
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    apiPost('auth/index', { email, password })
        .then(response => {
            if(response.ok) {
                return response.json();
            } else {
                throw new Error('Login failed');
            }
        })
        .then(data => {
            if (data.token) {
                localStorage.setItem('token', data.token); // Store token in localStorage
                setRole(data.role); // Store the user role
                // Send the token as Authorization header in future requests
                // You can also use this token for subsequent API requests
                window.location.href = '/home/home'; // Redirect to dashboard
            } else {
                throw new Error('Invalid or expired token');
            }
        })
        .catch(error => {
            console.error('Error logging in:', error);
            alert('Error logging in: ' + error.message);
        });
}

function handleRegister(event) {
    event.preventDefault();
    const email = document.getElementById('register-email').value;
    const password = document.getElementById('register-password').value;
<<<<<<< HEAD
    const role = "ADMIN";

    apiPost('auth/register', { email, password, userRole: role })
=======
    const role = document.getElementById('register-role').value;
    //const role = "ADMIN";
    //const role = document.getElementById('register-role').value;
    
        apiPost('auth/register', { email, password, userRole: role })
>>>>>>> 468ab7635a32a008af5d70ef45f4b9404e5dc48b
        .then(response => {
            if (response.ok) {
                alert('Registration successful!');
                return response.json();
            } else {
                throw new Error('Registration failed');
            }
        })
        .then(data => {
            if (data.message === "Registration successful") {
                window.location.href = '/auth/index'; // Redirect to login page
            } else {
                throw new Error('Registration failed. Email may already be in use.');
            }
        })
        .catch(error => {
            console.error('Error registering:', error);
            alert('Error registering: ' + error.message);
        });
}

window.handleRegister = handleRegister;
window.handleLogin = handleLogin;

export function handleLogout() {
    localStorage.removeItem('token'); // Remove token from localStorage
    clearRole(); // Clear the user role
    window.location.href = '/index'; // Redirect to login page
}


//document.getElementById('logoutButton').addEventListener('click', handleLogout);



