document.getElementById("signUp").addEventListener("click", (event) => {
    event.preventDefault();

    var header = new Headers();
    header.append("Content-Type", "application/json");
    header.append("Access-Control-Allow-Origin", "*");

    var raw = JSON.stringify({
        name: document.getElementById("name").value,
        email: document.getElementById("email").value,
        password: document.getElementById("password").value,
    });

    var requestOptions = {
        method: "POST",
        headers: header,
        body: raw,
        redirect: "follow",
    };

    fetch("http://localhost:8888/user/register", requestOptions)
        .then((response) => response.text())
        .then((result) => console.log(result))
        .catch((error) => console.log("error", error));
});
