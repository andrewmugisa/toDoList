const decrease = document.getElementById("decrease");
const increase = document.getElementById("increase");
const reset = document.getElementById("reset");
const numb = document.getElementById("numb");
const checker = document.getElementById("getPaid");
const reponse = document.getElementById("response");

console.log(`Hello, world!`);

// let fullName = "Tom Samuels";
// let isStudent = true;
// let age = 22;

//((message.textContent = { name }), { age }, { isStudent });
let count = Math.floor(Math.random() * 6);

numb.textContent = count;

decrease.onclick = function () {
  console.log("decrease clicked");
  count--;
  numb.textContent = count;
  getPaid();
};

increase.onclick = function () {
  count++;
  numb.textContent = count;
  getPaid();
};

// reset.onclick = function () {
//   count = 0;
//   numb.textContent = count;
// };

reset.addEventListener("click", () => {
  count = 0;
  numb.textContent = count;
});

checker.addEventListener("click", () => {
  reponse.textContent = "You are now at " + count + "  " + checkGetPaid();
});

function checkGetPaid() {
  if (count > 10) {
    return "you get paid";
  } else {
    return "Sorry no pay";
  }
}

function getPaid() {
  if (count > 10) {
    console.log("you get paid");
  } else {
    console.log("Sorry no pay");
  }
}

////////////////////////====form=====/////////////////////////

const name = document.getElementById("name");
const age = document.getElementById("age");
const maleCheckBox = document.getElementById("maleCheckBox");
const femaleCheckBox = document.getElementById("femaleCheckBox");
const otherGender = document.getElementById("otherCheckBox");
const submitForm = document.getElementById("submit");
const confirmation = document.getElementById("confirmation");
const otherGenderField = document.getElementById("otherCheckBoxField");

// let userName;
// let userAge;
// let userGender;

//get values
//checkand alert for errors
//update and print confirmation
function message(infor) {
  confirmation.style.display = "inline-block";
  confirmation.style.color = "rgb(12, 126, 2)";
  confirmation.textContent = infor;
}
function error(infor) {
  confirmation.style.display = "inline-block";
  confirmation.style.color = "rgb(158, 1, 1)";
  confirmation.textContent = infor;
}

function getName() {
  if (name.value == "") {
    // message("Please Enter Name");
    return null;
  }

  return name.value;
}

function getAge() {
  const invalidFields = age.value == "" || Number(age.value) <= 0;
  if (invalidFields) {
    // message("Please enter valid age");
    return null;
  }
  return Number(age.value);
}

function getGender() {
  const noGender =
    !maleCheckBox.checked && !femaleCheckBox.checked && !otherGender.checked;

  const otherIsEmpty =
    otherGender.checked && otherGenderField.value.trim() == "";

  if (noGender) {
    // message("Please enter select a gender");
    return null;
  } else if (otherIsEmpty && !noGender) {
    // message("Please specify your gender");
    return null;
  } else {
    if (maleCheckBox.checked) {
      return "male";
    } else if (femaleCheckBox.checked) {
      return "female";
    } else {
      return otherGenderField.value;
    }
  }
}

// otherGender.addEventListener("change", () => {
//   otherGenderField.style.display = "inline-block";
// });

function toggleOtherField() {
  if (otherGender.checked) {
    otherGenderField.style.display = "inline-block";
  } else {
    otherGenderField.style.display = "none";
    otherGenderField.value = "";
  }
}

maleCheckBox.addEventListener("change", toggleOtherField);
femaleCheckBox.addEventListener("change", toggleOtherField);
otherGender.addEventListener("change", toggleOtherField);

submitForm.addEventListener("click", () => {
  const userName = getName();
  const userAge = getAge();
  const userGender = getGender();

  if (!userName) {
    error("Please enter your name");
    return;
  }

  if (!userAge) {
    error("Please enter a valid age");
    return;
  }

  if (!userGender) {
    error("Please select or specify your gender");
    return;
  }

  message(`Name: ${userName}, Age: ${userAge}, Gender: ${userGender}`);
  name.value = "";
  age.value = "";
  otherGenderField.value = "";
  maleCheckBox.checked = false;
  femaleCheckBox.checked = false;
  otherGender.checked = false;
  toggleOtherField();
});

const person1 = {
  name: "Tom",
  age: 34,
  sayHello: () => message("Hey am Tom"),
};

const person2 = {
  name: "Ronn",
  age: 31,
  sayHello: () => message("Hi am Ronn"),
};

const people = { person1, person2 };

person1.sayHello;
// console.log(people.person1);
// console.log(people);
// console.log(person1.sayHello);

message();
//person1.sayHello();
people.person1.sayHello();

const numbers = [1, 5, 6, 2, 3, 8, 9, 10, 2, 3, 6];
//numbers.sort();
console.log(numbers);

for (let i = 0; i < numbers.length; i++) {
  console.log(numbers[i]);
}
numbers.sort((a, b) => a - b);
console.log(numbers);
const initial = Number(numbers);
//initial.sort();
console.log(typeof initial);
console.log(initial);

////////////////////////====Weather=====/////////////////////////
//5b9540d991d2c80c86d3536209ecebba

const weatherCard = document.getElementById("weatherCard");
const cityInput = document.querySelector(".cityInput");
const getWeather = document.querySelector(".getWeather");

cityInput.addEventListener("keydown", (event) => {
  if (event.key === "Enter") {
    fetchWeather();
  }
});

getWeather.addEventListener("click", () => fetchWeather);

async function fetchWeather() {
  const city = cityInput.value;
  if (city) {
    try {
      const weatherData = await getWeatherData(city);
      displayWeatherInfor(weatherData);
    } catch (Error) {
      displayError(Error);
    }
  } else {
    displayError("Please enter a City");
  }
}

async function getWeatherData(city) {
  const API_KEY = "5b9540d991d2c80c86d3536209ecebba";
  const apiUrl = `https://api.openweathermap.org/data/2.5/weather?q=${city}&appid=${API_KEY}`;
  const apiResponse = await fetch(apiUrl);
  console.log(apiResponse);
  if (!apiResponse.ok) {
    throw new Error("invalid city name. Try again");
  }
  return await apiResponse.json();
}

function displayWeatherInfor(data) {
  console.log(data);
  const {
    name: city,
    main: { temp: temperature, humidity },
    weather: [{ description, id }],
  } = data;

  const cityDisplay = document.createElement("h1");
  const tempDisplay = document.createElement("p");
  const humidityDisplay = document.createElement("p");
  const descDisplay = document.createElement("p");
  const emojiDisplay = document.createElement("p");
  //const cityDisplay = document.createElement("h1");
  //cityDisplay.classList.add("weatherCard h1")

  cityDisplay.textContent = city;
  tempDisplay.textContent = `${(temperature - 273.15).toFixed(1)}°C`;
  humidityDisplay.textContent = `${humidity}%`;
  descDisplay.textContent = description;
  emojiDisplay.textContent = getWeatherEmoji(id);

  weatherCard.textContent = "";
  weatherCard.style.display = "flex";

  weatherCard.appendChild(cityDisplay);
  weatherCard.appendChild(tempDisplay);
  weatherCard.appendChild(humidityDisplay);
  weatherCard.appendChild(descDisplay);
  weatherCard.appendChild(emojiDisplay);

  console.log(data);
}

function getWeatherEmoji(weatheId) {
  const id = weatheId;
  switch (true) {
    case id >= 200 && id < 300:
      return "⛈️";
      break;
    case id >= 300 && id < 400:
      return "🌦️";
      break;
    case id >= 500 && id < 600:
      return "🌧️";
      break;
    case id >= 600 && id < 700:
      return "❄️";
      break;
    case id >= 700 && id < 800:
      return "😶‍🌫️";
      break;
    case id === 800:
      return "🌞";
      break;
    case id >= 801 && id < 850:
      return "☁️";
      break;
    default:
      return "🚩";
  }
}
function displayError(message) {
  const errorDisplay = document.createElement("p");

  errorDisplay.textContent = message;
  errorDisplay.classList.add("errorDisplay");

  weatherCard.textContent = "";
  weatherCard.style.display = "block";

  weatherCard.appendChild(errorDisplay);
  //console.error(message);
}
