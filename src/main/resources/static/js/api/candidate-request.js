const listCandidates = () => {
    return fetch('/candidates').then(response =>{
        return response.json()
    })
    .then(json => {
        return json;
    });
}