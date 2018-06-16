const express = require("express");
const firebase = require("firebase");
const path = require("path");

// firebase configuration
let config = {
    apiKey: "AIzaSyDfSRGfcX-Xq-ynQENEeZtO2IjU9MN3WZM",
    authDomain: "mysa-734b1.firebaseapp.com",
    databaseURL: "https://mysa-734b1.firebaseio.com/",
    storageBucket: "mysa-734b1.appspot.com"
};

firebase.initializeApp(config);
let database = firebase.database(); // gets reference to database service

//dummy log in for now
authenticate("testmail2@gmail.com", "Password");

function authenticate(email, password){
    firebase.auth().signInWithEmailAndPassword(email, password).catch((err) => {
        // error
        let errorCode = err.code;
        let errorMessage = err.message;
    
        alert("Error");
    });
};

firebase.auth().onAuthStateChanged((user) => {
    if(user){
        console.log("Signed in");
    }
});

// Express route configuration
let app = express();

app.set("view engine", "ejs");
app.use(express.static(path.join(__dirname, "/public")));

// GET ROUTES
app.get("/", (req, res) => {
    res.render("index");
});

app.get("/feeds", (req, res) => {
    res.render("feeds");
});

app.get("/create", (req, res) => {
    res.render("create");
});

// POST ROUTES
app.post("/", (req, res) => {
    res.render("index");
});

app.post("/feeds", (req, res) => {
    res.render("feeds");
});

// initialize server
app.listen(3000, () => {
    console.log("Server is up");
});