const modelAuth = document.querySelector("#model-auth");
const modelLogin = document.querySelector("#model-login");
const modelRegister = document.querySelector("#model-register");
// const cancel_ = document.querySelector(".modal__overlay");
console.log(modelAuth)
console.log(modelLogin)
console.log(modelRegister)

function dangKyFnc(){
    modelAuth.classList.remove("auth_none")
    modelRegister.classList.remove("auth_none")
}
function dangNhapFnc(){
    modelLogin.classList.remove("auth_none")
    modelAuth.classList.remove("auth_none")
}
function cancel(){
    modelAuth.classList.add("auth_none")
    modelRegister.classList.add("auth_none")
    modelLogin.classList.add("auth_none")
}