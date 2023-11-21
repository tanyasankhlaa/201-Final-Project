package com.example.CSCI1.project.controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.CSCI1.project.Song;
import com.example.CSCI1.project.AccessToken;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RestController
public class SearchController {
    private final String clientId = "10835c20da7c478c9be264d88f55e597"; // Better to store in application.properties or environment variable
    private final String clientSecret = "52385718aa9b4ad5a0ca05697ea79578";

    // char client_id = '10835c20da7c478c9be264d88f55e597'; 
    // const client_secret = '52385718aa9b4ad5a0ca05697ea79578';
    int number_of_hits = 10; /* TODO: change this to desired value of outputs*/
   
    //GET is the default method
    //the /home inside the brackets is the path 'localhost:8080/home'
    // @GetMapping("/search")
    // public ModelAndView home() {
    //     ModelAndView modelAndView = new ModelAndView();
    //     modelAndView.setViewName("home.html");
    //     return modelAndView;
    // }

    @PostMapping("/spotifySearch")
    public List<Song> spotifySearch(@RequestBody String query) {
        System.out.println("HELOOOO");
        query = "golden"; 

        
        // CITATION: "Using HttpRequest for access token requests" prompt, (6 lines), ChatGPT, accessed Nov 20, 2023, chat.openai.com
        String requestCredentials = "grant_type=client_credentials&client_id=" + clientId + "&client_secret=" + clientSecret;
        HttpRequest accessrequest = HttpRequest.newBuilder()
                .uri(URI.create("https://accounts.spotify.com/api/token"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(requestCredentials.toString()))
                .build();
        
        HttpResponse<String> response = null;
		try {
			response = HttpClient.newHttpClient().send(accessrequest, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		AccessToken access_token = gson.fromJson(response.body(), AccessToken.class);
		System.out.println("access_token=" + access_token.getAccessToken());
		
		// GET SEARCH RESULTS FROM QUERY
		
		int number_of_hits = 10;
		
		HttpRequest queryrequest = HttpRequest.newBuilder()
    		    .uri(URI.create("https://api.spotify.com/v1/search?q=" + query + "&type=track&market=US&limit=" + number_of_hits + "&offset=0&include_external=audio"))
    		    .header("accept", "application/json")
    		    .header("Authorization", "Bearer " + access_token.getAccessToken())
    		    .method("GET", HttpRequest.BodyPublishers.noBody())
    		    .build();
		HttpResponse<String> queryresponse = null;
		try {
			queryresponse = HttpClient.newHttpClient().send(queryrequest, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("query results for " + query + "=" + queryresponse.body());
		
		
		// TODO: turn the json outputted by queryresponse.body() into song objects and append to songs
		
        List<Song> songs = new ArrayList<>();
        // Example: songs.add(new Song("Song Name", "Preview URL", "Album Name", List.of("Artist1", "Artist2")));
        // CITATION GPT "How can I store the returned JSON object to use GSON to create song objects" Next 3 lines
        // Define the type of the data structure you are expecting
        Type type = new TypeToken<Map<String, Object>>(){}.getType();
        // Parse the JSON response
        Map<String, Object> responseMap = gson.fromJson(queryresponse.body(), type);
        List<Map<String, Object>> searchResults = (List<Map<String, Object>>) ((Map<String, Object>) responseMap.get("tracks")).get("items");

        
        for (Map<String, Object> searchResult : searchResults) {
            String name = (String) searchResult.get("name");
            String previewUrl = (String) searchResult.get("preview_url");
            Map<String, String> albumMap = (Map<String, String>) searchResult.get("album");
            String album = albumMap.get("name");
            List<Map<String, String>> artistsMap = (List<Map<String, String>>) searchResult.get("artists");

            List<String> artists = new ArrayList<>();
            for (Map<String, String> artist : artistsMap) {
                artists.add(artist.get("name"));
            }

            // instantiate a new Song and add it to list
            songs.add(new Song(name, previewUrl, album, artists));
        }
        
        // printing out vals for debug
        // for(int i=0; i<songs.size(); i++) {
        // 	System.out.println(songs.get(i).getName());
        // 	System.out.println(songs.get(i).getAlbum());
        // 	System.out.println(songs.get(i).getPreviewUrl());
        // 	System.out.println(songs.get(i).getArtists());

        // }

        return songs;
    }

    // @PostMapping("/topTen") 
    // public void topTen() {
    //     // GET TOP HITS PLAYLIST SONGS
    //     String tophitsPlaylistId = "37i9dQZEVXbMDoHDwVN2tF";
    //     HttpRequest tophitsrequest = HttpRequest.newBuilder()
    //     .uri(URI.create("https://api.spotify.com/v1/playlists/"+ tophitsPlaylistId + "/tracks?market=US&limit=10&offset=0"))
    //     .header("accept", "application/json")
    //     .header("Authorization", "Bearer " + access_token.getAccessToken())
    //     .method("GET", HttpRequest.BodyPublishers.noBody())
    //     .build();
    //     HttpResponse<String> tophitsresponse = null;
    //     try {
    //         tophitsresponse = HttpClient.newHttpClient().send(tophitsrequest, HttpResponse.BodyHandlers.ofString());
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     } catch (InterruptedException e) {
    //         e.printStackTrace();
    //     }
    //     System.out.println("query results for top hits playlist" + tophitsPlaylistId + "=" + tophitsresponse.body());
    // }


}
