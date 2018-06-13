const express = require("express");

let app = express();

app.set("view engine", "ejs");

// routes
app.get("/", (req, res) => {
    res.render("index");
});

// initialize server
app.listen(3000, () => {
    console.log("Server is up");
});