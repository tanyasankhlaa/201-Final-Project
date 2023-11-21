package com.example.CSCI1.project;

import java.util.List;

public class Song {

	private String name;
	private String previewUrl;
	private String album;
    private List<String> artists;

	
	//constructor
    public Song(String name, String previewUrl, String album, List<String> artists) {
    	this.name = name;
        this.previewUrl = previewUrl;
        this.album = album;
        this.artists = artists;
    }
	
	// getters
	public String getName() {
		return name;
	}

	public String getPreviewUrl() {
		return previewUrl;
	}
	
	public String getAlbum() {
		return album;
	}
	
	public List<String> getArtists() {
		return artists;
	}
	
	// setters
	public void setName(String name) {
		this.name = name;
	}

	public void setPreviewUrl(String previewUrl) {
		this.previewUrl = previewUrl;
	}
	
	public void setAlbum(String album) {
		this.album = album;
	}
	
	public void setArtists(List<String> artists) {
		this.artists = artists;
	}
}