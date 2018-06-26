var express = require("express");
var router = express.Router();
const authModule = require("../public/js/auth.js");

router.get("/", (req, res) => {
    res.render("index");
});

router.post("/", authModule.login, (req, res) => {
    res.redirect("feeds");
});

router.get("/new_user", (req, res) => {
    res.render("new_user");
});

router.get("/logout", (req, res) => {
    authModule.logout();
    res.render("index");
});

module.exports = router;