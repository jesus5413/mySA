const firebase = require("firebase");
var middlewareObj = {};

// check if user exists
middlewareObj.doesUserExist = (req, res, next) => {
    let email = req.body.username;
    let password = req.body.password;
    
    authenticate(email, password);
    console.log("Looged in");

    next();
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