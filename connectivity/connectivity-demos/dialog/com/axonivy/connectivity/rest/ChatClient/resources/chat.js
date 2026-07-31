function Chat(uri) {
  // current service uri: e.g. "http://localhost:8081/designer/api/chatdemo"
  this.uri = uri;
  this.aborter = new AbortController();

  this.users = async function (callback) {
    const response = await fetch(uri + "/users");
    const users = await response.json();
    callback(users);
    return users;
  }

  this.listen = async function (callback) {
    let abortSignal = this.aborter.signal;
    const response = await fetch(uri, {
      signal: abortSignal
    });
    const messages = await response.json();
    this.listen(callback); // wait for next update
    callback(messages); // update UI
    return;
  }

  this.broadcast = async function (message) {
    let response = await fetch(uri, {
      method: 'POST',
      headers: { 'X-Requested-By': 'ivy' },
      body: message
    });
    if (response.status == 403) {
      alert("Forbidden: You must own the role 'Boss' to send broadcast messages!")
    }
    else if (!response.ok) //check 2XX family
    {
      alert("@All failed with HTTP status: " + response.status);
    }
    return;
  }

  this.send = async function (receiver, message) {
    const response = await fetch(uri + "/" + receiver, {
      method: 'POST',
      headers: { 'X-Requested-By': 'ivy' },
      body: message
    });
    const result = await response.json();
    return result;
  }
}


function View() {
  this.renderUsers = function (users) {
    var display = document.getElementById("users");
    users.forEach(function (user) {
      display.innerHTML += "<option>" + user + "</option>";
    });
  }

  this.renderMessages = function (messages) {
    var text = document.getElementById("form:chat");
    messages.forEach(function (entry) {
      text.innerHTML += "[" + entry.sentDate + "] " + entry.sender + ": " + entry.message + "<br/>";
    });
    text.scrollTop = text.scrollHeight;
  }
}

