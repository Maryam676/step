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

/* Fetches message from the /data page */
function getMessage() {
  let limit = document.getElementById("limitUserComments").value; //get the limit

  fetch(`/data?limit=${limit}`).then(response => response.json()).then((msgs) => {
    const statsListElement = document.getElementById('msg-container');
    statsListElement.innerHTML = '';
    
    // Use HTML to display each message
    for (var comment in msgs) {
      statsListElement.appendChild(
        createListElement(msgs[comment]));
    }
  });
}

/* Clear comment section and show refreshed number of comments */
function breakComments() {
  document.getElementById("msg-container").innerHTML=""; //clear out current comments
  getMessage(); //refetch comments
}

/* Deletes all current messages */
function deleteComments() {
  let request = new Request('/delete-data', {method: 'POST'});
  fetch(request);
}

/* Creates a <li> element for every item in json */
function createListElement(text) {
  const liElement = document.createElement('li');
  liElement.innerText = text;
  return liElement;
}

/* Creates a map and adds it to the page. */
function createMap() {
  const map = new google.maps.Map(
      document.getElementById('map'),
      {center: {lat: 41.994973, lng: -88.195170}, zoom: 7});

  /* Places I've been to */
  const sixFlagsMarker = new google.maps.Marker({
    position: {lat: 42.370377, lng: -87.936004},
    map: map,
    title: 'Six Flags Great America'
  });

  const milleniumMarker = new google.maps.Marker({
    position: {lat: 41.882643, lng: -87.623303},
    map: map,
    title: 'Cloud Gate'
  });

  const lakeMarker = new google.maps.Marker({
    position: {lat: 44.169149, lng: -78.854033},
    map: map,
    title: 'Lake Scugog'
  });

  const simcoeMarker = new google.maps.Marker({
    position: {lat: 44.427053, lng: -79.375320},
    map: map,
    title: 'Lake Simcoe'
  });

  const bridgeMarker = new google.maps.Marker({
    position: {lat: 42.998945, lng: -82.423804},
    map: map,
    title: 'Blue Water International Bridge'
  });

  const lansingMarker = new google.maps.Marker({
    position: {lat: 42.738749, lng: -84.510426},
    map: map,
    title: 'Lansing, MI'
  });

  const bufMarker = new google.maps.Marker({
    position: {lat: 42.886408, lng: -78.887447},
    map: map,
    title: 'Buffalo, NY'
  });

  const cleMarker = new google.maps.Marker({
    position: {lat: 41.476519, lng: -81.680194},
    map: map,
    title: 'Cleveland, OH'
  });

  const lakeONMarker = new google.maps.Marker({
    position: {lat: 43.809140, lng: -79.050311},
    map: map,
    title: 'Lake Ontario'
  });

  const torMarker = new google.maps.Marker({
    position: {lat: 43.649671, lng: -79.379451},
    map: map,
    title: 'Toronto, ON'
  });

  const misMarker = new google.maps.Marker({
    position: {lat: 43.586309, lng: -79.644368},
    map: map,
    title: 'Mississauga, ON'
  });

  const niagaraMarker = new google.maps.Marker({
    position: {lat: 43.089766, lng: -79.069428},
    map: map,
    title: 'Niagara Falls'
  });

  const abuDhabiMarker = new google.maps.Marker({
    position: {lat: 24.441498, lng: 54.649505},
    map: map,
    title: 'Abu Dhabi'
  });

  const towerMarker = new google.maps.Marker({
    position: {lat: 17.361540, lng: 78.474691},
    map: map,
    title: 'Charminar, Old City'
  });

  const museumMarker = new google.maps.Marker({
    position: {lat: 41.866133, lng: -87.616981},
    map: map,
    title: 'Field Museum'
  });

  const zooMarker = new google.maps.Marker({
    position: {lat: 41.921022, lng: -87.633581},
    map: map,
    title: 'Lincoln Park Zoo'
  });

  const ksMarker = new google.maps.Marker({
    position: {lat: 44.251054, lng: -76.433400},
    map: map,
    title: 'Kingston, ON'
  });

  const njMarker = new google.maps.Marker({
    position: {lat: 40.402989, lng: -74.038380},
    map: map,
    title: 'New Jersey'
  });

  const bangaloreMarker = new google.maps.Marker({
    position: {lat: 12.951283, lng: 77.585610},
    map: map,
    title: 'Bangalore'
  });
}

google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);

/* Add a chart to webpage */
function drawChart() {
  const data = new google.visualization.DataTable();
  data.addColumn('string', 'Genre');
  data.addColumn('number', 'Count');
    data.addRows([
      ['High Fantasy', 17],
      ['Science Fiction', 11],
      ['Adventure', 3],
      ['Realistic Fiction', 6],
      ['Nonfiction', 2],
      ['Urban Fantasy', 4],
      ['Horror', 5]
    ]);

  const options = {
    'width':400,
    'height':400
  };

  const chart = new google.visualization.PieChart(
      document.getElementById('chart-container'));
  chart.draw(data, options);
}