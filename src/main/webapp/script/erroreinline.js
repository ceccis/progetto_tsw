function  validazioneLogin(event) {
	var username = document.getElementById("username").value;             //è meglio var che ha scope globale
	var password = document.getElementById("password").value;

	var spanUtente = document.getElementById("erroreusername");
	var spanPassword = document.getElementById("errorepassword");

	spanUtente.innerText = "";
	spanPassword.innerText = "";

	if (username.trim() === "") {
		spanUtente.innerText = "Attenzione, inserisci l'username!";
		event.preventDefault(); 
	    }

	if (password.trim() === "") {
	    spanPassword.innerText = "Attenzione, inserisci la password!";
	    event.preventDefault(); 
	    }
}