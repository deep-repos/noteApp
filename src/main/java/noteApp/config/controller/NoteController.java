package noteApp.config.controller;

import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import noteApp.config.bean.Message;
import noteApp.config.bean.Note;
import noteApp.config.service.NoteService;
import noteApp.config.service.ValidateService;

@Controller
public class NoteController {
	
	@Autowired
	NoteService noteService;
	
	@Autowired
	ValidateService validateService;
	
	@PostMapping("/note")
	@ResponseBody
	public Message createNote(@RequestBody Note n) {
		System.out.println(n);
		return noteService.createNote(n);
		
	}

	@GetMapping("/note/{username}")
	@ResponseBody
	public List<Note> getAllNoteByUsername(@PathVariable("username") String username) {
		System.out.println(username);
		return noteService.getAllNotesOfUser(username);
		
	}
	
	@GetMapping("/note")
	@ResponseBody
	public Note getNoteContentByUserNameAndNoteId(@RequestParam("username") String username,@RequestParam("note_id") String note_id) {
		System.out.println(username+">>"+note_id);
		return noteService.getNoteByNoteId(username, note_id);
		
	}
	
	@PutMapping("/note")
	@ResponseBody
	public Message editNote(@RequestBody Note n) {
				System.out.println(n);
				return noteService.editNoteById(n);				
		}
	
	
	
	@DeleteMapping("/note")
	@ResponseBody
	public Message deleteNote(@RequestParam("username") String username ,@RequestParam("note_id") String note_id) {
				System.out.println(username+">>"+note_id);
				return noteService.deleteNoteById(username, note_id);				
		}
	
	
	
	@GetMapping("/note/{username}/{note_name}")
	@ResponseBody
	public List<Note> searchNoteByNoteName(@PathVariable("username") String username ,@PathVariable("note_name") String note_name) {
		System.out.println(username+">>"+note_name);
		return noteService.getNoteByName(username, note_name);
						
}
	
	/*public boolean editNoteById(Note n) {
		return noteDao.editNoteById(n);
		
	}
	public boolean deleteNoteById(String username,String note_id){
		return noteDao.deleteNoteById(username, note_id);
	}
	public Note getNoteByName(String username,String note_name) {
		return noteDao.getNoteByName(username, note_name);
	}
	
	public List<Note> getNoteByDate(String username,String date,int no_of_notes){
		return getNoteByDate(username, date, no_of_notes);
	}
	*/
	
}
