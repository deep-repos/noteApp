package noteApp.config.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import noteApp.config.bean.Message;
import noteApp.config.bean.Note;
import noteApp.config.repository.NoteDao;


@Service
public class NoteService {

		@Autowired
		NoteDao noteDao;
		
		@Autowired
		ValidateService validateService;
		

		public Message createNote(Note n) {
					Message m = new Message();
					
					if(n==null) {
						m.setMessage("System error");
						m.setValid(false);
						return m;
					} 
					
					if(n.getNote_name()==null || n.getNote_name().trim().length()==0 ||n.getNote_name().trim().length()>50) {
						m.setMessage("Note name is mandatory and it should be  less than  51 characters long");
						m.setValid(false);
						return m;
					}
						
					
				
					if(n.getNote_content()==null || n.getNote_content().trim().length()==0 ||n.getNote_content().trim().length()>6460)
						{
						m.setMessage("Note content is mandatory and it should be  less than  6460 characters long");
						m.setValid(false);
						return m;
						
						}
					
					if(n.getUsername()==null || n.getUsername().trim().length()==0) {
						m.setMessage("System error");
						m.setValid(false);
						return m;
					}
						
					
					
					int note_id = noteDao.getNoteId();
					System.out.println("note_id>>"+note_id);
					if(note_id!=-1) {
						n.setNote_id(Integer.toString(note_id));
						String date = validateService.getCurrentDate("yyyyMMdd");
						
						if(date!=null) {
							n.setDate_of_creation(date);
							System.out.println(n);
							n.setNote_name(n.getNote_name().trim());
							n.setNote_content(n.getNote_content().trim());
							n.setUsername(n.getUsername().trim());
							if (noteDao.createNote(n) ==true) {
								m.setMessage("Note created! click on view to see your note");
								m.setValid(true);
								return m;
							}else {
								m.setMessage("System error");
								m.setValid(false);
								return m;
							}
						}else {
							//if exeception while fetching current date
							m.setMessage("System error");
							m.setValid(false);
							return m;
						}
					}else {
						//if exeception while getting note id
						m.setMessage("System error");
						m.setValid(false);
						return m;
					}
			
					
		}
		public Message editNoteById(Note n) {
			Message m = new Message();
			System.out.println("in editNoteById>>"+n);
			if(n==null) {
				m.setMessage("System error");
				m.setValid(false);
				return m;
			} 
			
			if(n.getNote_name()==null || n.getNote_name().trim().length()==0 ||n.getNote_name().trim().length()>50) {
				m.setMessage("Note name is mandatory and it should be  less than  51 characters long");
				m.setValid(false);
				return m;
			}
				
			
		
			if(n.getNote_content()==null || n.getNote_content().trim().length()==0 ||n.getNote_content().trim().length()>6460)
				{
				m.setMessage("Note content is mandatory and it should be  less than  6460 characters long");
				m.setValid(false);
				return m;
				
				}
			
			if(n.getUsername()==null || n.getUsername().trim().length()==0) {
				m.setMessage("System error");
				m.setValid(false);
				return m;
			}
			
			if(n.getNote_id()==null || n.getNote_id().trim().length()==0) {
				m.setMessage("System error");
				m.setValid(false);
				return m;
			}
			
			if( noteDao.editNoteById(n)==true) {
				m.setMessage("Note updated! click on view to see your note");
				m.setValid(true);
				return m;
			}else {
				m.setMessage("System error");
				m.setValid(false);
				return m;
			}
			
		}
		public Message deleteNoteById(String username,String note_id){
			Message m =new Message();
			if(username==null || username.trim().length()==0 || note_id==null || note_id.trim().length()==0) {
				m.setValid(false);
				m.setMessage("System error");
				return m;
				
			}
			if( noteDao.deleteNoteById(username, note_id) ) {
				m.setMessage("Note deleted!");
				m.setValid(true);
				return m;
			}else {
				m.setMessage("System error");
				m.setValid(false);
				return m;
			}
			
			
		}
		public List<Note> getNoteByName(String username,String note_name) {
			if(username==null || username.trim().length()==0 || note_name==null || note_name.trim().length()==0 ) {
				return null;
			}
			 return noteDao.getNoteByName(username, note_name);
			
		}
		
		public List<Note> getAllNotesOfUser(String username) {
			return noteDao.getAllNotesOfUser(username);
		}
		
		public Note getNoteByNoteId(String username, String note_id) {
			return noteDao.getNoteByNoteId(username, note_id);
		}
		

}
		

