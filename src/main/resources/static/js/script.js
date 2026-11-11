const url = "http://localhost:8080/";

document.addEventListener("DOMContentLoaded", function () {
  const fullName = document.querySelector("#registerName");
  const registerEmail = document.querySelector("#registerEmail");
  const registerPassword = document.querySelector("#registerPassword");
  const registerPasswordConfirm = document.querySelector(
    "#registerPasswordConfirm",
  );
  const registerSubmitBtn = document.querySelector("#registerSubmitBtn");
  const registerErrors = document.querySelector("#registerErrors");
  const registerForm = document.querySelector("#registerForm");

  const loginEmail = document.querySelector("#loginEmail");
  const loginPassword = document.querySelector("#loginPassword");
  const loginSubmitBtn = document.querySelector("#loginSubmitBtn");
  const loginErrors = document.querySelector("#loginErrors");
  const loginForm = document.querySelector("#loginForm");

  const toRegister = document.querySelector(".toRegister");
  const toLogin = document.querySelector(".toLogin");

  const loginTogglePassword = document.querySelector("#loginTogglePassword");
  const registerTogglePassword = document.querySelector(
    "#registerTogglePassword",
  );

  loginErrors.hidden = true;
  registerErrors.hidden = true;
  registerForm.hidden = true;
  loginForm.hidden = false;

  toRegister.addEventListener("click", showRegister);
  toLogin.addEventListener("click", showLogin);
  loginSubmitBtn.addEventListener("click", validateLoginInput);
  registerSubmitBtn.addEventListener("click", validateRegisterInput);
  loginTogglePassword.addEventListener("click", showLoginPassword);
  registerTogglePassword.addEventListener("click", showRegisterPassword);

  //show register form
  function showRegister() {
    loginForm.hidden = true;
    registerForm.hidden = false;
  }

  //show login form
  function showLogin() {
    loginForm.hidden = false;
    registerForm.hidden = true;
  }

  //toggle show or hide password
  function showLoginPassword() {
    if (loginTogglePassword.innerText === "show") {
      loginPassword.type = "text";
      loginTogglePassword.innerText = "hide";
    } else {
      loginPassword.type = "password";
      loginTogglePassword.innerText = "show";
    }
  }

  //toggle show or hide password
  function showRegisterPassword() {
    if (registerTogglePassword.innerText === "show") {
      registerPassword.type = "text";
      registerTogglePassword.innerText = "hide";
    } else {
      registerPassword.type = "password";
      registerTogglePassword.innerText = "show";
    }
  }

  //validate login inputs
  function validateLoginInput() {
    loginErrors.innerHTML = "";

    let errorList = document.createElement("ul");
    let currentErrors = [];

    loginEmail.classList.remove("error");
    loginPassword.classList.remove("error");

    //email
    if (!loginEmail.value.includes("@")) {
      currentErrors.push("Invalid email");
      loginEmail.classList.add("error");
    }

    //password
    if (loginPassword.value.length < 3) {
      currentErrors.push("Please enter password");
      loginPassword.classList.add("error");
    }

    if (currentErrors.length > 0) {
      loginErrors.hidden = false;
      for (const e of currentErrors) {
        let item = document.createElement("li");
        item.innerText = e;
        errorList.appendChild(item);
      }
      loginErrors.appendChild(errorList);
    } else {
      authenticateUser();
    }
  }

  //validate register inputs
  function validateRegisterInput() {
    registerErrors.innerHTML = "";

    let errorList = document.createElement("ul");
    let currentErrors = [];

    fullName.classList.remove("error");
    registerEmail.classList.remove("error");
    registerPassword.classList.remove("error");
    registerPasswordConfirm.classList.remove("error");

    // name
    if (fullName.value.length < 3) {
      currentErrors.push("Please enter full name");
      fullName.classList.add("error");
    }

    // email
    if (registerEmail.value.length < 3) {
      currentErrors.push("Invalid email");
      registerEmail.classList.add("error");
    }

    // password
    if (
      registerPassword.value.length < 12 ||
      registerPassword.value.length > 20
    ) {
      currentErrors.push("Password must be between 12 and 20");
      registerPassword.classList.add("error");
    }

    // password
    if (registerPasswordConfirm.value.length < 1) {
      currentErrors.push("Please retype password");
      registerPasswordConfirm.classList.add("error");
    } else if (!(registerPassword.value === registerPasswordConfirm.value)) {
      currentErrors.push("Passwords do not match");
      registerPasswordConfirm.classList.add("error");
    }

    if (currentErrors.length > 0) {
      registerErrors.hidden = false;

      for (const e of currentErrors) {
        let item = document.createElement("li");
        item.innerText = e;
        errorList.appendChild(item);
      }

      registerErrors.appendChild(errorList);
    } else {
      // fullName = fullName.value;
      // registerEmail = registerEmail.value;
      // registerPassword = registerPassword.value;
      // registerPasswordConfirm = registerPasswordConfirm.value;
    }
  }

  //================================================================================================================
  // const url = "http://localhost:8080";

  // document.addEventListener("DOMContentLoaded", function () {
  // fetch(url).then((response) => {
  //   console.log(response.status + " " + response.json());
  //   return response.json();
  // });

  fetch(url)
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
    });

  async function authenticateUser() {
    const loginData = {
      email: loginEmail.value,
      password: loginPassword.value,
    };

    try {
      const response = await fetch(url, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(loginData),
      });

      const data = await response.json();

      if (response.ok) {
        console.log(data);
      } else {
        alert(`${response.status}\n${data.message}`);
      }
    } catch (error) {
      console.error("Network or parsing error:", error);
      alert("Something went wrong. Please try again.");
    }
  }
});
