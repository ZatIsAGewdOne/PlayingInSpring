package com.learningprogramming.musicStore.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learningprogramming.musicStore.db.entities.Album;
import com.learningprogramming.musicStore.services.AlbumService;

@RestController
@RequestMapping("album")
public class AlbumAPI {

	private AlbumService albumService;
	
	public AlbumAPI(AlbumService albumService) {
		this.albumService = albumService;
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity<List<Album>> getAlbums(@PathVariable int id) {
		return ResponseEntity.ok(albumService.fetchAlbumArtistJoin(id));
	}
}
