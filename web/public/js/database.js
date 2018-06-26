var middlewareObj = {};

// Function will grab form data from body and use each input as newItem fields to be 
// pushed onto feedsRef of database
middlewareObj.addItem = (body, feedRef) => {
    let newItem = feedRef.push(); // pushes EMPTY record to database

    // this will actually write out all of the required items to that record
    newItem.set({
        title: body.title,
        date: body.date.toString(),
        score: body.score,
        imgUrl: body.image,
        description: body.description
    });
    
    console.log("added to db");
}

module.exports = middlewareObj;