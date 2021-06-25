/**
 * confirmação de exclusão de contato
 *author Lais Reis
 * idcon
 */
function confirmar(idcon){
	let resposta = confirm("Confirma a exclusão deste contato?")
	if(resposta ===true){
		window.location.href ="delete?idcon="+idcon
	}
}