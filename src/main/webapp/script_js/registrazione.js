function checkEmail() {
    var emailInserita = document.getElementById("email").value;
    var spanMessaggio = document.getElementById("rispostaAjax");

    if (emailInserita.trim() === "") {           //controllo se la casella è vuota
        spanMessaggio.innerText = "";
        return;
    }

	var regexEmail = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
	if (!regexEmail.test(emailInserita)){
		spanMessaggio.innerText = "Il formato della mail non è valido";
		spanMessaggio.style.color = "red";
		return;
	}
	
    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var risposta = xhr.responseText;                //risposta dalla servlet

            if (risposta === "true") {
                spanMessaggio.innerText = "Attenzione: email già in uso!";
                spanMessaggio.style.color = "red";
            } else if (risposta === "false") {
                spanMessaggio.innerText = "Email disponibile";
                spanMessaggio.style.color = "green";
            }
        }
    };

    xhr.open("POST", "VerificaEmail", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    
    xhr.send("email=" + encodeURIComponent(emailInserita));
}