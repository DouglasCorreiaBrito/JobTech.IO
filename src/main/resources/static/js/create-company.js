const form = document.querySelector('#form-company');

form.addEventListener("submit",event =>{
    event.preventDefault();

    const name = event.target.querySelector('#name');
    const cnpj = event.target.querySelector('#cnpj');
    const email = event.target.querySelector('#email');
    const phone = event.target.querySelector('#phone');
    const address = event.target.querySelector('#address');

    createCompany(address.value,cnpj.value,email.value,name.value,123456,phone.value);

    swal("Isso ai!", "Você acaba de criar uma nova empresa!", "success");
});
