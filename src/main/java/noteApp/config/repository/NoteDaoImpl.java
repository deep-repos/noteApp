package noteApp.config.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import noteApp.config.bean.Note;

@Repository
public class NoteDaoImpl implements NoteDao{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public boolean createNote(Note n) {
		try {
			String sql="insert into note (username,note_name,note_content,note_id,date_of_creation) values (?,?,?,?,?) ";
			jdbcTemplate.update(sql,n.getUsername(),n.getNote_name(),n.getNote_content(),n.getNote_id(),n.getDate_of_creation());
			return true;
		}catch(Exception e ) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean editNoteById(Note n) {
		try {
			String sql="update   note  set note_name=? ,note_content=? where username=? and note_id=? ";
			jdbcTemplate.update(sql,n.getNote_name(),n.getNote_content(),n.getUsername(),n.getNote_id());
			return true;
		}catch(Exception e ) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean deleteNoteById(String username, String note_id) {
		try {
			String sql="delete from note where username=? and note_id=? ";
			jdbcTemplate.update(sql,username,note_id);
			return true;
		}catch(Exception e ) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public List<Note> getNoteByName(String username, String note_name) {
		try {
		
			String sql="select username,note_name,note_content,note_id,date_of_creation from note where username='"+username+"' and note_name like '%"+note_name+"%' order by date_of_creation ";
			System.out.println(sql);
			return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Note>(Note.class));
			
		}catch(Exception e ) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public List<Note> getNoteByDate(String username, String date, int no_of_notes) {
		try {
			String sql="select username,note_name,note_content,note_id,date_of_creation from note where username=? and note_date = ? limit = ? ";
			return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Note>(Note.class),username,date,no_of_notes);
			
		}catch(Exception e ) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public int getNoteId() {
		try {
			String sql=" select max(CAST(note_id AS SIGNED)) from note; ";
			String id =  jdbcTemplate.queryForObject(sql,String.class);
			if(id==null) return 0;
			else return Integer.parseInt(id)+1;
		}catch(Exception e ) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public List<Note> getAllNotesOfUser(String username) {
		try {
			String sql="select username,note_name,note_id,date_of_creation from note where username=?  ";
			return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Note>(Note.class),username);
			
		}catch(Exception e ) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public Note getNoteByNoteId(String username, String note_id) {
		try {
			String sql="select username,note_name,note_content,note_id,date_of_creation from note where username=? and note_id =?";
			return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<Note>(Note.class),username,note_id);
		}catch(Exception e ) {
			e.printStackTrace();
			return null;
		}
		
		
	}

}
