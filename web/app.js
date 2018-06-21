// require statements
require("dotenv").config();
const express = require("express");
const firebase = require("firebase");
const bodyParser = require("body-parser");
const path = require("path");
// const databaseModule = require("./public/js/database.js");

// globabl variables
var database;
var feedRef;

// firebase configuration
var config = {
    apiKey: process.env.API_KEY,
    authDomain: process.env.AUTH_DOMAIN,
    databaseURL: process.env.DATABASE_URL,
    storageBucket: process.env.STORAGE_BUCKET
};

firebase.initializeApp(config);

//dummy log in for now
authenticate("testmail2@gmail.com", "Password");

function authenticate(email, password){
    firebase.auth().signInWithEmailAndPassword(email, password).catch((err) => {
        let errorCode = err.code;
        let errorMessage = err.message;
    
        console.log(errorMessage);
        console.log("can't sign in");
    });
};

firebase.auth().onAuthStateChanged((user) => {
    if(user){
        console.log("Signed in");
        database = firebase.database();
        feedRef = database.ref("feed");
    }
});

// Express route configuration
let app = express();

app.set("view engine", "ejs");
app.use(express.static(path.join(__dirname, "/public")));
app.use(bodyParser.urlencoded({ extended: true }));

// functions
function getFeedAndRender(res){
    feedRef.once("value", (snapshot) => {
        res.render("feeds", { posts: snapshot });
    });
}

// GET ROUTES
app.get("/", (req, res) => {
    res.render("index");
});

app.get("/feeds", (req, res) => {
    getFeedAndRender(res);
});

// POST ROUTES
app.post("/", (req, res) => {
    res.render("index");
});

// This will add new entry into the database 
app.post("/feeds", (req, res) => {
    let newItem = feedRef.push(); // pushes EMPTY record to database

    // this will actually write out all of the required items to that record
    newItem.set({
        title: req.body.title,
        date: req.body.date.toString(),
        score: req.body.score,
        imgUrl: req.body.image,
        description: req.body.description
    });

    console.log("added to db");
    getFeedAndRender(res);
});

// initialize server
app.listen(3000, () => {
    console.log("Server is up");
});