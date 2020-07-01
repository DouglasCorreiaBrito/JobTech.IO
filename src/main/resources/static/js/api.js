function getJobs() {
    let keywords = document.getElementById('keywords').value;
    let location = document.getElementById('location').value;
    let url = '/jobs';
    if(keywords != undefined && keywords != ''){
        url += '/filtered/' + keywords;
    } else if(location != undefined && location != ''){
        url += '/filtered/location/' + location;
    }

    document.getElementById('search-content').innerHTML = '';
    
    $.get(
        url,
        { },
        function (data) {
            data.forEach((entry) => {
                
                let toggleCollapse = document.createElement('button');
                toggleCollapse.classList.add('btn');
                toggleCollapse.classList.add('btn-link');
                toggleCollapse.setAttribute('data-toggle', 'collapse');
                toggleCollapse.setAttribute('data-target', '#collapse' + entry.id);
                toggleCollapse.setAttribute('aria-expanded', 'true');
                toggleCollapse.setAttribute('aria-controls', 'collapse' + entry.id);
                toggleCollapse.innerHTML = entry.title;
                toggleCollapse.style.cssText = 'color: white;';
                
                let title = document.createElement('h5');
                title.classList.add('mb-0');
                title.appendChild(toggleCollapse);
                
                let header = document.createElement('div');
                header.classList.add('card-header');
                header.style.cssText = 'background-color: #84e;';
                header.id = 'heading' + entry.id;
                header.appendChild(title);
                
                let salario = document.createElement('div');
                salario.classList.add('col-4');
                salario.innerHTML = '<i class="fas fa-dollar-sign"></i> ' + entry.minimumWage;
                
                let cargo = document.createElement('div');
                cargo.classList.add('col-4');
                cargo.innerHTML = '<i class="fas fa-id-card-alt"></i> ' + entry.seniority;
                
                let contratacao = document.createElement('div');
                contratacao.classList.add('col-4');
                contratacao.innerHTML = '<i class="fas fa-scroll"></i> ' + entry.typeOfContract;
                
                let infoRow = document.createElement('div');
                infoRow.classList.add('row');
                infoRow.appendChild(salario);
                infoRow.appendChild(cargo);
                infoRow.appendChild(contratacao);
                
                let btnSubscribe = document.createElement('button');
                btnSubscribe.classList.add('btn');
                btnSubscribe.classList.add('btn-secondary');
                btnSubscribe.onclick = function() {
                	applyToOpportunity(entry.id);
                }
                btnSubscribe.innerHTML = 'Inscrever-se';
                	
            	let descricao = document.createElement('div');
                descricao.classList.add('col-12');
                descricao.innerHTML = entry.description;
                descricao.appendChild(btnSubscribe);
                
                let descRow = document.createElement('div');
                descRow.classList.add('row');
                descRow.appendChild(descricao);
                
                let body = document.createElement('div');
                body.classList.add('card-body');
                body.style.cssText = 'background-color: #282A2D;color: white;';
                body.appendChild(infoRow);
                body.appendChild(document.createElement('hr'));
                body.appendChild(descRow);
                
                let collapse = document.createElement('div');
                collapse.id = 'collapse' + entry.id;
                collapse.classList.add('collapse');
                collapse.setAttribute('aria-labelledby', 'heading' + entry.id);
                collapse.setAttribute('data-parent', '#search-content');
                collapse.appendChild(body);

                let jobDiv = document.createElement('div');
                jobDiv.classList.add('card');
                jobDiv.appendChild(header)
                jobDiv.appendChild(collapse);

                document.getElementById('search-content').appendChild(jobDiv);
            });
        }
    );
}

function applyToOpportunity(opportunityId) {
	
	let url = '/job-application';
	
	$.ajax({
	  url:url,
	  type:"POST",
	  data: JSON.stringify({ idCandidate: 1, idJobOpportunity: opportunityId, status: "APPLIED" }),
	  contentType:"application/json; charset=utf-8",
	  dataType:"json",
	  success: function(){
		  alert('Aplicação realizada com sucesso');
	  },
	  error: function(error){
		  alert('Error: ' + error);
	  }
	});
}

function cancelAppliance(applicationId, candidateId, opportunityId) {
	
	let url = '/job-application/' + applicationId;
	
	$.ajax({
	  url:url,
	  type:"PUT",
	  data: JSON.stringify({ idCandidate: candidateId, idJobOpportunity: opportunityId, status: "CANCELED" }),
	  contentType:"application/json; charset=utf-8",
	  dataType:"json",
	  success: function(){
		  alert('Cancelamento realizada com sucesso');
	  },
	  error: function(error){
		  alert('Error: ' + error);
	  }
	});
}

function getApplications(candidateId) {
	
	let url = '/job-application/candidate/' + candidateId;
	
	$.get(
        url,
        { },
        function (data) {
            data.forEach((entry) => {
            	if(entry.status == 'APPLIED') {
					let toggleCollapse = document.createElement('button');
					toggleCollapse.classList.add('btn');
					toggleCollapse.classList.add('btn-link');
					toggleCollapse.setAttribute('data-toggle', 'collapse');
					toggleCollapse.setAttribute('data-target', '#collapse' + entry.jobOpportunity.id);
					toggleCollapse.setAttribute('aria-expanded', 'true');
					toggleCollapse.setAttribute('aria-controls', 'collapse' + entry.jobOpportunity.id);
					toggleCollapse.innerHTML = entry.jobOpportunity.title;
					toggleCollapse.style.cssText = 'color: white;';
					
					let title = document.createElement('h5');
					title.classList.add('mb-0');
					title.appendChild(toggleCollapse);
					
					let header = document.createElement('div');
					header.classList.add('card-header');
					header.style.cssText = 'background-color: #84e;';
					header.id = 'heading' + entry.jobOpportunity.id;
					header.appendChild(title);
					
					let salario = document.createElement('div');
					salario.classList.add('col-4');
					salario.innerHTML = '<i class="fas fa-dollar-sign"></i> ' + entry.jobOpportunity.minimumWage;
					
					let cargo = document.createElement('div');
					cargo.classList.add('col-4');
					cargo.innerHTML = '<i class="fas fa-id-card-alt"></i> ' + entry.jobOpportunity.seniority;
					
					let contratacao = document.createElement('div');
					contratacao.classList.add('col-4');
					contratacao.innerHTML = '<i class="fas fa-scroll"></i> ' + entry.jobOpportunity.typeOfContract;
					
					let infoRow = document.createElement('div');
					infoRow.classList.add('row');
					infoRow.appendChild(salario);
					infoRow.appendChild(cargo);
					infoRow.appendChild(contratacao);
					
					let btnSubscribe = document.createElement('button');
					btnSubscribe.classList.add('btn');
					btnSubscribe.classList.add('btn-secondary');
					btnSubscribe.onclick = function() {
						cancelAppliance(entry.id, entry.candidate.id, entry.jobOpportunity.id);
					}
					btnSubscribe.innerHTML = 'Cancelar Inscrição';
						
					let descricao = document.createElement('div');
					descricao.classList.add('col-12');
					descricao.innerHTML = entry.jobOpportunity.description;
					descricao.appendChild(btnSubscribe);
					
					let descRow = document.createElement('div');
					descRow.classList.add('row');
					descRow.appendChild(descricao);
					
					let body = document.createElement('div');
					body.classList.add('card-body');
					body.style.cssText = 'background-color: #282A2D;color: white;';
					body.appendChild(infoRow);
					body.appendChild(document.createElement('hr'));
					body.appendChild(descRow);
					
					let collapse = document.createElement('div');
					collapse.id = 'collapse' + entry.jobOpportunity.id;
					collapse.classList.add('collapse');
					collapse.setAttribute('aria-labelledby', 'heading' + entry.jobOpportunity.id);
					collapse.setAttribute('data-parent', '#search-content');
					collapse.appendChild(body);
					
					let jobDiv = document.createElement('div');
					jobDiv.classList.add('card');
					jobDiv.appendChild(header)
					jobDiv.appendChild(collapse);
					
					document.getElementById('search-content').appendChild(jobDiv);
            	}
            });
        }
	);
	
}
