
const API_URL = 'http://localhost:8080';

function saveLogin(token, role, username, name, id=null) {
  localStorage.setItem("token", token);
  localStorage.setItem("role", role);
  localStorage.setItem("username", username);
  localStorage.setItem("name", name);
  if (id) localStorage.setItem("userId", id);
}

function authHeader() {
  const token = localStorage.getItem("token");
  return token ? { "Content-Type":"application/json", "Authorization":"Bearer " + token } : { "Content-Type":"application/json" };
}

async function apiGet(endpoint) {
  const res = await fetch(API_URL + endpoint, { method:"GET", headers: authHeader() });
  return res.json();
}

async function apiPost(endpoint, body) {
  const res = await fetch(API_URL + endpoint, { method:"POST", headers: authHeader(), body: JSON.stringify(body) });
  return res.json();
}

function requireLogin() {
  if (!localStorage.getItem("token")) {
    alert("Please login first.");
    window.location = "index.html";
    return false;
  }
  return true;
}


const USERNAME_ID_MAP = { "teacher1": "1", "student1": "2" };

function getUserId() {
  let id = localStorage.getItem("userId");
  if (id) return id;
  const username = localStorage.getItem("username");
  if (username && USERNAME_ID_MAP[username]) {
    localStorage.setItem("userId", USERNAME_ID_MAP[username]);
    return USERNAME_ID_MAP[username];
  }

  id = prompt("Enter your numeric user id (as in backend DB). Example: 2 for student1:", "");
  if (id) {
    localStorage.setItem("userId", id);
    return id;
  }
  return null;
}

function logout() {
  localStorage.clear();
  window.location = "index.html";
}
