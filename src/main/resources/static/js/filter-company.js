var filtro = document.querySelector("#filtrar-tabela");

filtro.addEventListener("input",function(){

    var pacientes = document.querySelectorAll("#empresa");

    console.log(pacientes)

    if (this.value.length>0) {
      
        for (let index = 0; index < pacientes.length; index++) {

            var paciente = pacientes[index];
            var tdNome = paciente.querySelector(".info-nome");
            var nome = tdNome.textContent;
            var regex = new RegExp(this.value,"i");
    
             if (!regex.test(nome)) {
                 
                paciente.classList.add("invisivel");
    
             } else {
                 
                paciente.classList.remove("invisivel");
             }
            
        }
    }else{

        for (let index = 0; index < pacientes.length; index++) {
            
            var paciente = pacientes[index];
            paciente.classList.remove("invisivel");
            
        }
    }

    

});