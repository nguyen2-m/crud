const modelAuth = document.querySelector("#model-auth");
const modelLogin = document.querySelector("#model-login");
const modelRegister = document.querySelector("#model-register");
const cancel_ = document.querySelector(".modal__overlay");
console.log(modelAuth)
console.log(modelLogin)
console.log(modelRegister)

function dangKyFnc(){
    modelAuth.classList.remove("auth-hidden")
    modelRegister.classList.remove("auth-hidden")
}
function dangNhapFnc(){
    modelLogin.classList.remove("auth-hidden")
    modelAuth.classList.remove("auth-hidden")
}
function cancel(){
    modelAuth.classList.add("auth-hidden")
    modelRegister.classList.add("auth-hidden")
    modelLogin.classList.add("auth-hidden")
}