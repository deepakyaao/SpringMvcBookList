document.querySelector("form")
.addEventListener("submit",function(e){

    let email =
    document.querySelector(
    "input[type='email']").value;

    let password =
    document.querySelector(
    "input[type='password']").value;

    if(email==="" || password===""){
        alert("Please fill all fields");
        e.preventDefault();
    }
});

function togglePassword() {

    let passwordField =
        document.getElementById("password");

    if (passwordField.type === "password") {
        passwordField.type = "text";
    } else {
        passwordField.type = "password";
    }
}