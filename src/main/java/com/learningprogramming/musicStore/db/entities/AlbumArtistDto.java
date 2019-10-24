package com.learningprogramming.musicStore.db.entities;

public class AlbumArtistDto {

	private Integer albumId;
	private String albumName;
	private Integer artistId;
	private String artistName;
	
	public AlbumArtistDto(String name, String artistName) {
		this.albumName = name;
		this.artistName = artistName;
	}

	public int getAlbumId() {
		return albumId;
	}

	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public int getArtistId() {
		return artistId;
	}

	public void setArtistId(int artistId) {
		this.artistId = artistId;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	@Override
	public String toString() {
		return "AlbumAndArtist [albumId=" + albumId + ", albumName=" + albumName + ", artistId=" + artistId
				+ ", artistName=" + artistName + "]";
	}
	
	
}
