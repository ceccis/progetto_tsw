function  validazioneLogin() {
	var username = document.getElementById("username").value;             //è meglio var che ha scope globale
	var password = document.getElementById("password").value;

	var spanutente = document.getElementById("erroresername");
	var spanpassword = document.getElementById("errorepassword");

	spanutente.innerText = "";
	spanpassword.innerText = "";

	if (username.trim() === "") {
		spanUtente.innerText = "Attenzione: inserisci l'username!";
	    spanUtente.style.color = "red";
		event.preventDefault(); 
	    }

	if (password.trim() === "") {
	    spanPass.innerText = "Attenzione: inserisci la password!";
	    spanPass.style.color = "red";
	    event.preventDefault(); 
	    }
}