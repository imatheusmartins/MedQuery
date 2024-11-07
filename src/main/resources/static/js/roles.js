export function setRole(role) {
    localStorage.setItem('role', role);
}

export function getRole() {
    return localStorage.getItem('role');
}

export function clearRole() {
    localStorage.removeItem('role');
}

export function checkRole(role) {
    return getRole() === role;
}

export function restrictAccess(requiredRole) {
    if (!checkRole(requiredRole)) {
        window.location.href = '/unauthorized'; // Redirect to unauthorized page
    }
}
