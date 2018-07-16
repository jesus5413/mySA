let openB = document.getElementsByClassName("edit-butt");
let modal = document.getElementById("edit-modal");
let close = document.getElementById("close");
let formElements = document.getElementById("edit-item-form");
let postId;

close.onclick = () => {
    modal.style.display = "none";
}

window.onclick = (e) => {
    if(e.target == modal){
        modal.style.display = "none";
    }
}

function showModal(title, image, date, score, id){
    modal.style.display = "block";
    // These will just allow viewer to see previous values
    formElements[0].setAttribute("placeholder", title);
    formElements[1].setAttribute("placeholder", image);
    // These will ensure that any unchanged data will be sent in update
    formElements[0].setAttribute("value", title);
    formElements[1].setAttribute("value", image);
    formElements[2].setAttribute("value", date);
    formElements[3].setAttribute("value", score);
    // formElements[4].setAttribute("placeholder", post[4]);
    setId(id);
    setFormAction();
}

function setId(id){
    postId = id;
}

function getId(){
    console.log(postId);
}

function setFormAction(){
    formElements.action = "/feeds/" + postId + "?_method=PUT";
}

function redirect(){
    console.log("redirecting");
    window.location.href = "/feeds/" + postId + "?_method=PUT";
}

function updateDate(){
    console.log(id);
}