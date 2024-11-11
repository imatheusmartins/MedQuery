import { apiPost } from './api.js';
import { setRole, clearRole } from './roles.js';

const signInBtn = document.getElementById("signIn");
const signUpBtn = document.getElementById("signUp");
//const fistForm = document.getElementById("form1");
//const secondForm = document.getElementById("form2");
const container = document.querySelector(".container");

signInBtn.addEventListener("click", () => {
	container.classList.remove("right-panel-active");
});

signUpBtn.addEventListener("click", () => {
	container.classList.add("right-panel-active");
});

//fistForm.addEventListener("submit", (e) => e.preventDefault());
//secondForm.addEventListener("submit", (e) => e.preventDefault());


export function handleLogin() {
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    apiPost('auth/login', { email, password })
        .then(response => {
            if(response.ok){
                return response.json();
            }else{
                throw new Error('Login failed');  
            }
        })
        .then(data => {
            if (data.token) {
                localStorage.setItem('token', data.token);
                setRole(data.role); // Set the user role
                window.location.href = '/homePatient'; // Redirect to dashboard
            } else {
                throw new Error('Invalid or expired token'); 
            }
        })
        .catch(error => {
            console.error('Error logging:', error); 
            alert('Error logging: ' + error.message);
        });
}

function handleRegister(event) {
    event.preventDefault();
    const email = document.getElementById('register-email').value;
    const password = document.getElementById('register-password').value;
    const role = document.getElementById('register-role').value;
    //const role = "ADMIN";
    //const role = document.getElementById('register-role').value;
    
        apiPost('auth/register', { email, password, userRole: role })
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
                window.location.href = '/auth/login'; // Redirect to login page 
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
    localStorage.removeItem('token');
    clearRole(); // Clear the user role
    window.location.href = '/login'; // Redirect to login page
}


//document.getElementById('logoutButton').addEventListener('click', handleLogout);



