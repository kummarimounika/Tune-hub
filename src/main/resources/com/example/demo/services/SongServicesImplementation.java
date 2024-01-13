package com.example.demo.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Song;
import com.example.demo.repositories.SongRepository;
@Service
public class SongServicesImplementation implements SongServices{
@Autowired
SongRepository sr;
	@Override
	public void addSong(Song song) {
		sr.save(song);
		
	}
	@Override
	public List<Song> fetchAllSongs() {
		
		return sr.findAll();
	}
	@Override
	public boolean songExits(String name) {
		Song song=sr.findByName(name);
		if(song==null) {
			return false;
		}else {
			return true;
			}
	
	
	
	
}
}