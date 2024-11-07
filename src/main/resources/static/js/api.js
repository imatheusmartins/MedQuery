export function apiPost(url, data) {
    const token = localStorage.getItem('token');
    const baseUrl = 'http://localhost:8080/';
    const fullUrl = url.startsWith('http') ? url : `${baseUrl}${url}`;
    return fetch(fullUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': token ? `Bearer ${token}` : ''
        },
        body: JSON.stringify(data)
    })
    .then(response => response.json());
}

export function apiGet(url) {
    const token = localStorage.getItem('token');
    const baseUrl = 'http://localhost:8080/';
    const fullUrl = url.startsWith('http') ? url : `${baseUrl}${url}`;

    return fetch(fullUrl, {
        method: 'GET',
        headers: {
            'Authorization': token ? `Bearer ${token}` : ''
        }
    })
    .then(response => response.json());
}