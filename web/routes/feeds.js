var express = require("express");
var router = express.Router();
const firebase = require("firebase");
const authModule = require("../public/js/auth.js");
const databaseModule = require("../public/js/database.js");

var database = firebase.database();
var feedRef = database.ref("feed");

function getFeedAndRender(res){
    feedRef.once("value", (snapshot) => {
        res.render("feeds", { posts: snapshot });
    });
}

router.get("/feeds", authModule.isUserAuthenticated, (req, res) => {
    getFeedAndRender(res);
});

router.post("/feeds", authModule.isUserAuthenticated, (req, res) => {
    databaseModule.addItem(req.body, feedRef);
    getFeedAndRender(res);
});

module.exports = router;