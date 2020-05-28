// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/**
 * Adds a random greeting to the page.
 */
function addRandomGreeting() {
  const greetings =
      ['Hello world!', '¡Hola Mundo!', '你好，世界！', 'Bonjour le monde!'];

  // Pick a random greeting.
  const greeting = greetings[Math.floor(Math.random() * greetings.length)];

  // Add it to the page.
  const greetingContainer = document.getElementById('greeting-container');
  greetingContainer.innerText = greeting;
}

/*User decides which ice cream they like better*/
function clickIceCream() {
  let flavor = prompt("Chocolate or vanilla?", "");
  if (flavor == "chocolate") {
    alert("Nothing beats chocolate ice cream!");
  }
  else if (flavor == "vanilla") {
    alert("I have to disagree :(");
  }
  else {
    alert("Sorry, not an option!");
  }
}

/*User decides which ice cream they like better*/
function clickComfFood() {
  let food = prompt("Do you prefer pasta or pizza?", "");
  if (food == "pizza") {
    alert("My favorite is veggie. But pasta wins over pizza.");
  }
  else if (food == "pasta") {
    alert("You can never have enough pasta");
  }
  else {
    alert("Sorry, not an option!");
  }
}

/*User decides which leisure activity they like better*/
function clickLeisure() {
  let activity = prompt("Canoeing or hiking?", "");
  if (activity == "hiking") {
    alert("Hiking is obviously better");
  }
  else if (activity == "canoeing") {
    alert("We differ on this one, I have a phobia of open water");
  }
  else {
    alert("Sorry, not an option!");
  }
}

/*User decides which vacation destination they like better*/
function clickVacation() {
  let place = prompt("Would you prefer to visit Venice or Dubai for a vacation?", "");
  if (place == "dubai") {
    alert("Let's go see the tallest skyscraper in the world!");
  }
  else if (place == "venice") {
    alert("Hmm, maybe 2nd place?");
  }
  else {
    alert("Sorry, not an option!");
  }
}

/**
 * Fetches a random quote from the server and adds it to the DOM.
 */
function getRandomQuote() {
  console.log('Fetching the request');

  // The fetch() function returns a Promise because the request is asynchronous.
  const responsePromise = fetch('/data');

  // When the request is complete, pass the response into handleResponse().
  responsePromise.then(handleResponse);
}

/**
 * Handles response by converting it to text and passing the result to
 * addQuoteToDom().
 */
function handleResponse(response) {
  console.log('Handling the response.');

  // response.text() returns a Promise, because the response is a stream of
  // content and not a simple variable.
  const textPromise = response.text();

  // When the response is converted to text, pass the result into the
  // addQuoteToDom() function.
  textPromise.then(addQuoteToDom);
}

/** Adds a random quote to the DOM. */
function addQuoteToDom(quote) {
  console.log('Adding quote to dom: ' + quote);

  /*The quote container displays the quote*/
  const quoteContainer = document.getElementById('quote-container');
  quoteContainer.innerText = quote;
}
