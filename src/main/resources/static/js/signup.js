document.querySelector("form")
.addEventListener("submit",function(e){

    let password =
    document.getElementById("password").value;

    let confirmPassword =
    document.getElementById("confirmPassword").value;

    if(password !== confirmPassword){
        alert("Passwords do not match");
        e.preventDefault();
    }
});


// =====================================
// Toggle Password Eye Icon
// =====================================
function togglePassword() {

    let password =
        document.getElementById("password");

    let icon =
        document.getElementById("togglePassword");

    if(password.type === "password") {

        password.type = "text";

        icon.classList.remove("bi-eye-slash");
        icon.classList.add("bi-eye");

    } else {

        password.type = "password";

        icon.classList.remove("bi-eye");
        icon.classList.add("bi-eye-slash");
    }
}
// =====================================
// Toggle Confirm Password Eye Icon
// =====================================
function toggleConfirmPassword() {

    let confirmPassword =
        document.getElementById("confirmPassword");

    let icon =
        document.getElementById("toggleConfirmPassword");

    if(confirmPassword.type === "password") {

        confirmPassword.type = "text";

        icon.classList.remove("bi-eye-slash");
        icon.classList.add("bi-eye");

    } else {

        confirmPassword.type = "password";

        icon.classList.remove("bi-eye");
        icon.classList.add("bi-eye-slash");
    }
}