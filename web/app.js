require("dotenv").config();
const express = require("express");
const firebase = require("firebase");
const bodyParser = require("body-parser");
const path = require("path");
// const databaseModule = require("./public/js/database.js");

// firebase configuration
let config = {
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
    }
});

// Express route configuration
let app = express();

app.set("view engine", "ejs");
app.use(express.static(path.join(__dirname, "/public")));
app.use(bodyParser.urlencoded({ extended: true }));

// GET ROUTES
app.get("/", (req, res) => {
    res.render("index");
});

app.get("/feeds", (req, res) => {
    // retrieve all items from db
    let database = firebase.database();
    let feedRef = database.ref("feed");
    
    feedRef.on("value", (snapshot) => {
        res.render("feeds", { posts: snapshot });
    });
});

app.get("/create", (req, res) => {
    res.render("create");
});

// POST ROUTES
app.post("/", (req, res) => {
    res.render("index");
});

// This will add new entry into the database 
app.post("/feeds", (req, res) => {
    let database = firebase.database(); // gets reference to database service
    let feedRef = database.ref("feed"); // gets reference to the feed field of database
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
    res.render("feeds");
});

// initialize server
app.listen(3000, () => {
    console.log("Server is up");
});