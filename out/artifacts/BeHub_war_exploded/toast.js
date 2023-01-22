function lanciaToastGreen() {
    let timer1, timer2;
    var toast = document.querySelector(".toast");
    toast.classList.add("active");

    var closeIcon = document.querySelector(".close");
    var progress = document.querySelector(".progress");
    progress.classList.add("active");

    timer1 = setTimeout(() => {
        toast.classList.remove("active");
    }, 5000);

    timer2 = setTimeout(() => {
        progress.classList.remove("active");
    }, 5300);

}

function lanciaToastRed() {
    let timer1, timer2;
    var toasterre = document.querySelector(".toasterre");
    toasterre.classList.add("active");

    var closeIcon = document.querySelector(".close");
    var progresserre = document.querySelector(".progresserre");
    progresserre.classList.add("active");

    timer1 = setTimeout(() => {
        toasterre.classList.remove("active");
    }, 5000);

    timer2 = setTimeout(() => {
        progresserre.classList.remove("active");
    }, 5300);

}




