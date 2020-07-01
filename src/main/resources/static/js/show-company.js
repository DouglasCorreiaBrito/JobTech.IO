    const tableBody = document.querySelector('#tabela-empresas');
    const delCompany = id => {
        if(confirm("deseja deletar a empresa? ")){
            deleteCompany(id);
        }
        listCompanies();
    }

    const showCompany = (id,name,telefone,email) => {
    
        const tableHead = document.createElement('tr');
        tableHead.id = 'empresa'
    
        const contentHTML = `
            <td class="info-id">${id}</td>
            <td class="info-nome">${name}</td>
            <td class="info-peso">${telefone}</td>
            <td class="info-altura mt-4">${email}</td>
            <td class="info-gordura"><button type="button" class="btn btn-danger btn-sm" onclick='delCompany(${id})'>deletar</button></td>
            <td class="info-gordura"><button type="button" class="btn btn-primary btn-sm">atualizar</button></td>
     `
        tableHead.innerHTML = contentHTML;
    
        return tableHead;
    }
    
    listCompanies().then( show => {
        show.forEach(company =>{
            tableBody.appendChild(showCompany(company.id,company.name,company.telephone,company.email));
        });
    });
    

