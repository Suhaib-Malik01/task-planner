document.getElementById("Login").addEventListener("click", (event) => {
    event.preventDefault();

  const email = document.getElementById("email").value;
  const password = document.getElementById("password").value;
  const authToken = btoa(`${email}:${password}`);

  fetch("http://localhost:8888/user/signIn", {
    headers: {
      Authorization: `Basic ${authToken}`,
      "Content-Type": "application/json",
    },
  })
    .then((response) => {
      const token = response.headers.get("Authorization");

      localStorage.setItem("token", token);
    })
    .catch((error) => {
      alert(error.message);
    });
});
