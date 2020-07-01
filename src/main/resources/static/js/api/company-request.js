const listCompanies = () => {
    return fetch('/company').then(response =>{
        return response.json()
    })
    .then(json => {
        return json;
    });
}

const createCompany = (address,cnpj,email,name,password,telephone) => {
    const json = JSON.stringify({
        address : address,
        cnpj : cnpj,
        email : email,
        name : name,
        password : password,
        telephone : telephone
    });

    return fetch('/company',{
        method: 'POST',
        headers: {
            'Content-type': 'application/json'
        },
        body: json
    })
    .then( resp => {
        return resp.body;
    })
}

const deleteCompany = id =>{
    return fetch(`/company/${id}`,{
        method: 'DELETE',
    });
}