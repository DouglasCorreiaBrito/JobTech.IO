const form = document.querySelector('#form-login');

form.addEventListener("submit",event =>{
    event.preventDefault();

    const email = event.target.querySelector('#email');
    const senha = event.target.querySelector('#pass');
    let userExist = false;

    listCandidates().then(candidates => {
        candidates.forEach(candidate =>{
            if (candidate.email === email.value){
                location.href='./home.html';
            } else if (email.value === 'admin@gmail.com'){
                location.href='./masterdetail.html';
            }else {
                swal("Oops!", "dados inválidos!", "error");
            }
            
        })
    })
});
