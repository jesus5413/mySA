const firebase = require("firebase");
var middlewareObj = {};

middlewareObj.login = (req, res, next) => {
    console.log("MIDDLEWARE LOGIN");
    let email = req.body.username;
    let password = req.body.password;

    authenticate(email, password, next);
};

// only let user on other pages if user is authenticated
middlewareObj.isUserAuthenticated = (req, res, next) => {
    let user = firebase.auth().currentUser;
    console.log("running isUserAuth");
    if(user !== null){
        req.user = user;
        console.log("logged in from auth.js");
        next();
    }else{
        res.redirect("/");
    }
};

middlewareObj.createUser = (first, last, email, password, confirm) =>{
    if(password !== confirm){
        console.log("Passwords don't match");
        return;
    }

    firebase.auth().createUserWithEmailAndPassword(email, password).catch(((err) => {
        let errorCode = err.code;
        let errorMessage = err.message;

        console.log(errorMessage);
        console.log("can't create user"); 
    }));

    return {
        FirstName: first,
        LastName: last,
        Email: email,
        Password: password,
        role: "admin"
    }
}

middlewareObj.checkIfNewUser = () => {
    let creatTime = firebase.auth().currentUser.metadata.creationTime
    let signInTime = firebase.auth().currentUser.metadata.lastSignInTime

    return creatTime === signInTime;
}

function authenticate(email, password, next){
    firebase.auth().signInWithEmailAndPassword(email, password)
        .then((user) => {
            next();
        })
        .catch((err) => {
            let errorCode = err.code;
            let errorMessage = err.message;
        
            console.log(errorMessage);
            console.log("can't sign in");
        });
};

module.exports = middlewareObj;