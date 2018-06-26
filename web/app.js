// require statements
require("dotenv").config();
const express = require("express");
const firebase = require("firebase");
const bodyParser = require("body-parser");
const path = require("path");
const databaseModule = require("./public/js/database.js");
const authModule = require("./public/js/auth.js");

// routes
const indexRoutes = require("./routes/index.js");
// const feedsRoutes = require("./routes/feeds.js");

// globabl variables
var database;
var feedRef;
var newUserInfo;

// firebase configuration
var config = {
    apiKey: process.env.API_KEY,
    authDomain: process.env.AUTH_DOMAIN,
    databaseURL: process.env.DATABASE_URL,
    storageBucket: process.env.STORAGE_BUCKET
};

firebase.initializeApp(config);

firebase.auth().onAuthStateChanged((user) => {
    if(user){
        database = firebase.database();
        feedRef = database.ref("feed");
        let usersRef = database.ref("users");

        if(authModule.checkIfNewUser()){
            let newUser = usersRef.push(); // pushes EMPTY record to database
        
            // this will actually write out all of the required items to that record
            newUser.set({
                FirstName: newUserInfo.FirstName,
                LastName: newUserInfo.LastName,
                Email: newUserInfo.Email,
                Password: newUserInfo.Password,
                role: newUserInfo.role
            });

            console.log("Added new user to db");
        }

        // check for admin role
        // let isAdmin = false;

        // usersRef.once("value").then((snapshot) => {
        //     snapshot.forEach((user) => {
        //         if(user.val().Email === firebase.auth().currentUser.email){
        //             if(user.val().role === "admin"){
        //                 console.log("Is admin");
        //             }
        //         }
        //     });
        // });
        console.log("logged in");
    }else{
        console.log("not logged in");
    }
});

// Express route configuration
let app = express();

app.set("view engine", "ejs");
app.use(express.static(path.join(__dirname, "/public")));
app.use(bodyParser.urlencoded({ extended: true }));
app.use("/", indexRoutes);
// app.use("/feeds", feedsRoutes);

// functions
function getFeedAndRender(res){
    console.log("IN GET FEEDS");
    database = firebase.database();
    feedRef = database.ref("feed");
    
    feedRef.once("value", (snapshot) => {
        res.render("feeds", { posts: snapshot });
    });
}

// GET ROUTES
// Only admin users should be able to access feeds
app.get("/feeds", authModule.isUserAuthenticated, (req, res) => {
    getFeedAndRender(res);
});

// POST ROUTES
// Actually create a new user
app.post("/new_user", (req, res) => {
    newUserInfo = authModule.createUser(
        req.body.first,
        req.body.last,
        req.body.email,
        req.body.password,
        req.body.confirm
    );

    getFeedAndRender(res);
});

// This will add new entry into the database 
app.post("/feeds", authModule.isUserAuthenticated, (req, res) => {
    databaseModule.addItem(req.body, feedRef);
    getFeedAndRender(res);
});

// initialize server
app.listen(3000, () => {
    console.log("Server is up");
});