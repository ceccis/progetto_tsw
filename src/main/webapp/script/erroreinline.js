function validazioneLogin(event) {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    var spanUtente = document.getElementById("erroreUsername");
    var spanPass = document.getElementById("errorePassword");

    spanUtente.innerText = "";
	spanUtente.style.display = "none";    //per far scomparire l'errore
    spanPass.innerText = "";
	spanPass.style.display = "none";
	
    if (username.trim() === "") {
        spanUtente.innerText = "Attenzione: inserisci l'username!";
        spanUtente.style.display = "block";
        event.preventDefault();
    }

    if (password.trim() === "") {
        spanPass.innerText = "Attenzione: inserisci la password!";
        spanPass.style.display = "block";
        event.preventDefault();
    }
}