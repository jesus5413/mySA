const firebase = require("firebase");
var middlewareObj = {};

middlewareObj.login = (req, res, next) => {
    let email = req.body.username;
    let password = req.body.password;

    authenticate(email, password, next);
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

middlewareObj.logout = () => {
    firebase.auth().signOut().then(() => {
        console.log("logged out");
    }).catch((err) => {
        console.log(err);
    });
}

middlewareObj.checkForAdmin = (usersRef, res) => {
    usersRef.once("value").then((snapshot) => {
        snapshot.forEach((user) => {
            if(user.val().Email === firebase.auth().currentUser.email){
                if(user.val().role === "admin"){
                    console.log("Is admin");
                }else{
                   return  res.redirect("logout");
                }
            }
        });
    });
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
    
    console.log("authenticate");
};

module.exports = middlewareObj;