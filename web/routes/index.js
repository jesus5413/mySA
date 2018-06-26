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

module.exports = router;