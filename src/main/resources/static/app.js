document.addEventListener("DOMContentLoaded", function() {
    // Attach event listener to the button
    const searchButton = document.getElementById("searchButton");
    if (searchButton) {
        searchButton.addEventListener("click", function() {
            // Get the search query from an input field
            const query = document.getElementById("searchInput").value;
            performSearch(query);
        });
    }
});

function performSearch(query) {
    // console.log(query)
    fetch("http://localhost:8080/spotifySearch",{
        method:"POST",
        headers:{"Content-Type":"application/json", "charset": "utf-8"},
        body:JSON.stringify({
            "query":query,
        })
    }) .then(response => response.json())
        .then(songs => {
            console.log(songs);
                    
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

// Complete this function to make the songs show up on the front end
function displaySongs(songs) {
}
