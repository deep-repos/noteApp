package noteApp.config.repository;

import java.util.List;

import noteApp.config.bean.Note;



public interface NoteDao {
	public boolean createNote(Note n);
	public boolean editNoteById(Note n);
	public boolean deleteNoteById(String username,String note_id);
	public List<Note> getNoteByName(String username,String note_name);
	public List<Note> getNoteByDate(String username,String date,int no_of_notes);
	public int  getNoteId() ;
	public List<Note> getAllNotesOfUser(String username);
	public Note getNoteByNoteId(String username,String note_id);
	
	
}
