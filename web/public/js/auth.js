const firebase = require("firebase");
var middlewareObj = {};

// check if user exists
middlewareObj.doesUserExist = (req, res, next) => {
    let email = req.body.username;
    let password = req.body.password;

    authenticate(email, password);
};

// only let user on other pages if user is authenticated
middlewareObj.isUserAuthenticated = (req, res, next) => {
    let user = firebase.auth().currentUser;

    if(user !== null){
        req.user = user;
        next();
    }else{
        res.redirect("/");
    }
};

function authenticate(email, password){
    firebase.auth().signInWithEmailAndPassword(email, password).catch((err) => {
        let errorCode = err.code;
        let errorMessage = err.message;
    
        console.log(errorMessage);
        console.log("can't sign in");
    });
};

module.exports = middlewareObj;