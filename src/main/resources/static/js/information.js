var model = document.querySelector("#model-auth")

console.log(model)


function controlsBack() {
    model.classList.add("auth-hidden")
}

function saveinformation() {
    model.classList.remove("auth-hidden")
}
function backcancel() {
    model.classList.add("auth-hidden")
}

const inpFile = document.getElementById("inpFile");
const previewContainer = document.getElementById("imagePreview");
const previewImage = previewContainer.querySelector(".image-preview__image");
const previewDefaultText = previewContainer.querySelector(".image-preview__default-text");
var modelimage = document.querySelector("#model-image")

inpFile.addEventListener("change", function () {
    const file = this.files[0];
    console.log(file);
    if (file) {
        modelimage.classList.remove("auth-hidden")
        const reader = new FileReader();
        previewDefaultText.style.display = "none";
        previewImage.style.display = "block";
        reader.addEventListener("load", function () {
            previewImage.setAttribute("src", this.result);
        });
        reader.readAsDataURL(file);

    }
});

function imagecancel() {
    modelimage.classList.add("auth-hidden");
}