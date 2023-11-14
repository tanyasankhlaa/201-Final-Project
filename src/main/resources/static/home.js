function add(event){
    event.preventDefault();
    const titleVar = "title1";
    const contentVar = "content1";
    fetch("http://localhost:8080/demo/add",{
        method:"POST",
        headers:{"Content-Type":"application/json", "charset": "utf-8"},
        body:JSON.stringify({
            "title":titleVar,
            "content":contentVar
        })
    })
    .then(response => console.log(response))
    .catch(e => console.log(e));     
}