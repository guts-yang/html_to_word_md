const API_URL = '/api';
let authHeader = localStorage.getItem('authHeader');
let currentUser = localStorage.getItem('currentUser');

// DOM Elements
const authSection = document.getElementById('authSection');
const dashboardSection = document.getElementById('dashboardSection');
const userInfo = document.getElementById('userInfo');
const logoutBtn = document.getElementById('logoutBtn');
const loginForm = document.getElementById('loginForm');
const registerForm = document.getElementById('registerForm');
const uploadForm = document.getElementById('uploadForm');
const tasksTableBody = document.getElementById('tasksTableBody');
const refreshBtn = document.getElementById('refreshBtn');

// Init
if (authHeader) {
    checkAuth();
} else {
    showAuth();
}

// Event Listeners
loginForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    const username = e.target.username.value;
    const password = e.target.password.value;
    const creds = btoa(username + ':' + password);
    const header = 'Basic ' + creds;

    try {
        const res = await fetch(API_URL + '/auth/me', {
            headers: { 'Authorization': header }
        });
        if (res.ok) {
            authHeader = header;
            currentUser = username;
            localStorage.setItem('authHeader', authHeader);
            localStorage.setItem('currentUser', currentUser);
            showDashboard();
        } else {
            alert('Invalid credentials');
        }
    } catch (err) {
        alert('Login failed');
    }
});

registerForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    const username = e.target.username.value;
    const password = e.target.password.value;

    try {
        const res = await fetch(API_URL + '/auth/register', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ username, password })
        });
        const data = await res.json();
        if (res.ok) {
            alert('Registration successful! Please login.');
            // Switch to login tab
            document.querySelector('#login-tab').click();
        } else {
            alert(data.error || 'Registration failed');
        }
    } catch (err) {
        alert('Registration error');
    }
});

logoutBtn.addEventListener('click', () => {
    authHeader = null;
    currentUser = null;
    localStorage.removeItem('authHeader');
    localStorage.removeItem('currentUser');
    showAuth();
});

uploadForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    
    try {
        const res = await fetch(API_URL + '/convert', {
            method: 'POST',
            headers: { 'Authorization': authHeader },
            body: formData
        });
        if (res.ok) {
            e.target.reset();
            loadTasks();
            alert('Conversion started!');
        } else {
            alert('Upload failed');
        }
    } catch (err) {
        alert('Error uploading file');
    }
});

refreshBtn.addEventListener('click', loadTasks);

// Functions
function showAuth() {
    authSection.style.display = 'flex';
    dashboardSection.style.display = 'none';
    userInfo.style.display = 'none';
    logoutBtn.style.display = 'none';
}

function showDashboard() {
    authSection.style.display = 'none';
    dashboardSection.style.display = 'block';
    userInfo.textContent = `Welcome, ${currentUser}`;
    userInfo.style.display = 'inline';
    logoutBtn.style.display = 'inline';
    loadTasks();
    // Start polling
    if (!window.pollInterval) {
        window.pollInterval = setInterval(loadTasks, 5000);
    }
}

async function checkAuth() {
    try {
        const res = await fetch(API_URL + '/auth/me', {
            headers: { 'Authorization': authHeader }
        });
        if (res.ok) {
            showDashboard();
        } else {
            logoutBtn.click();
        }
    } catch (err) {
        logoutBtn.click();
    }
}

async function loadTasks() {
    if (!authHeader) return;
    try {
        const res = await fetch(API_URL + '/tasks', {
            headers: { 'Authorization': authHeader }
        });
        if (res.ok) {
            const tasks = await res.json();
            renderTasks(tasks);
        }
    } catch (err) {
        console.error('Failed to load tasks', err);
    }
}

function renderTasks(tasks) {
    tasksTableBody.innerHTML = tasks.map(task => {
        let statusBadge = '';
        switch(task.status) {
            case 'COMPLETED': statusBadge = '<span class="badge bg-success">Completed</span>'; break;
            case 'FAILED': statusBadge = '<span class="badge bg-danger">Failed</span>'; break;
            case 'PENDING': statusBadge = '<span class="badge bg-warning text-dark">Pending</span>'; break;
            case 'PROCESSING': statusBadge = '<span class="badge bg-info text-dark">Processing</span>'; break;
        }

        let actionBtn = '';
        if (task.status === 'COMPLETED' && task.resultPath) {
            // Fix path separator for URL if needed, but task.resultPath should be just filename from Service
            // Use encodeURI to preserve slashes (e.g. "123/file.pdf") but encode spaces/special chars
            const downloadUrl = `${API_URL}/download/${encodeURI(task.resultPath)}`;
            actionBtn = `<a href="${downloadUrl}" class="btn btn-sm btn-primary" target="_blank">Download</a>`;
        } else if (task.status === 'FAILED') {
            actionBtn = `<small class="text-danger">${task.errorMessage || 'Error'}</small>`;
        }

        return `
            <tr>
                <td>${task.id}</td>
                <td>${task.fileName}</td>
                <td>${task.targetFormat}</td>
                <td>${statusBadge}</td>
                <td>${actionBtn}</td>
            </tr>
        `;
    }).join('');
}
