<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	
	<script>
				
		
		function showCreateNote(){
			document.getElementById("note_create_div").style.display="block";
		}
		function hideCreateNote(){
			document.getElementById("note_create_div").style.display="none";
		}
	
		function showViewNote(){
			document.getElementById("note_view_div").style.display="block";
		}
		function hideViewNote(){
			document.getElementById("note_view_div").style.display="none";
		}
		
		function createNote(){
			hideViewNote();
			resetContentOfCreateNote();
			document.getElementById("note_create_div").style.display="block";		
		}
		
		function resetContentOfCreateNote(){
			document.getElementById("create_note_name").value="";
			document.getElementById("create_note_content").value="";
			document.getElementById("create_note_submit_button").disabled = false;
			document.getElementById("create_note_name").disabled = false;
			document.getElementById("create_note_content").disabled = false;
			document.getElementById("create_note_submit_button").setAttribute("name","create");
			
		}
		
		function resetViewNoteTable(){
			var parent = document.getElementById("table_div");
			
			while (parent.lastChild) {
		        parent.removeChild(parent.lastChild);
		    }
			
		}
		
		
		
		
		function createNoteSubmit(){
			
			
			if(document.getElementById("create_note_submit_button").getAttribute("name")=="edit"){
				if(document.getElementById("create_note_submit_button").disabled==true) return;		
				
				var note_name = document.getElementById("create_note_name").value;
				var note_content = document.getElementById("create_note_content").value;
				var username = document.getElementById("username_div").innerText;
				var note_id = document.getElementById("create_note_id_div").innerText;
				
				var dataString ={
				          "username": username,
				          "note_name": note_name,
				          "note_content":note_content,
				          "note_id":note_id
				        };
				dataString = JSON.stringify(dataString);
				
			$.ajax({
			      type: 'put',
			        url: '/noteApp/note',
			        async:false,
			        contentType: "application/json",
			        data:dataString,
			        success: function(result) {
			        	console.log(result);
			        	if(result.valid){
			        		alert(result.message);
			        		hideCreateNote();
							resetContentOfCreateNote();
			        	}
			        	else{
			        		alert(result.message);
			        		
			        	}
			        	
			        },
			        error: function() {
			        	alert("Please try again later");
			        	hideCreateNote();
						resetContentOfCreateNote();
			        }
			    });
				
				return;
			}
			
			
			
			
			if(document.getElementById("create_note_submit_button").disabled==true) return;
			
			
			var note_name = document.getElementById("create_note_name").value;
			var note_content = document.getElementById("create_note_content").value;
			var username = document.getElementById("username_div").innerText;
			var dataString ={
			          "username": username,
			          "note_name": note_name,
			          "note_content":note_content
			        };
			dataString = JSON.stringify(dataString);
			
		$.ajax({
		      type: 'post',
		        url: '/noteApp/note',
		        async:false,
		        contentType: "application/json",
		        data:dataString,
		        success: function(result) {
		        	console.log(result);
		        	if(result.valid){
		        		alert(result.message);
		        		hideCreateNote();
						resetContentOfCreateNote();
		        	}
		        	else{
		        		alert(result.message);
		        		
		        	}
		        	
		        },
		        error: function() {
		        	alert("Please try again later");
		        	hideCreateNote();
					resetContentOfCreateNote();
		        }
		    });
	
		
			
		
		
		}
		
		
		function viewNote(){
			hideCreateNote();
			resetContentOfCreateNote();
			resetViewNoteTable();
			showViewNote();
			
			var list_of_notes=null;
			
			var username = document.getElementById("username_div").innerText;
			var url_ = '/noteApp/note/'+username;
			
			

			$.ajax({
			      type: 'get',
			        url: url_,
			        async:false,
			        success: function(result) {
			        	console.log(result);
			        	list_of_notes = result;
			        },
			        error: function() {
			         
			        }
			    });
			
			createTable(list_of_notes);
		}
		
		
		function createTable(list_of_notes) {
		    var a, b, tableElem, rowElem, colElem;

		    a = 3 ;//row
		    b = 6 ;//column

		    if (a == "" || b == "") {
		        alert("Please enter some numeric value");
		    } else {
		        tableElem = document.createElement('table');

		        for (var i = 0; i < list_of_notes.length; i++) {
		            
				var note =  list_of_notes[i];
				console.log(note);

				rowElem = document.createElement('tr');

		 

		                colElem = document.createElement('td');
		                colElem.appendChild(document.createTextNode("")); //to print cell number
		                rowElem.appendChild(colElem);


		                colElem = document.createElement('td');
		                colElem.appendChild(document.createTextNode("")); //to print cell number
		                rowElem.appendChild(colElem);


				   colElem = document.createElement('td');
		                colElem.appendChild(document.createTextNode("")); //to print cell number
		                rowElem.appendChild(colElem);


		                colElem = document.createElement('td');
		                colElem.appendChild(document.createTextNode("")); //to print cell number
		                rowElem.appendChild(colElem);

			   colElem = document.createElement('td');
		                colElem.appendChild(document.createTextNode("")); //to print cell number
		                rowElem.appendChild(colElem);


		                colElem = document.createElement('td');
		                colElem.appendChild(document.createTextNode("")); //to print cell number
		                rowElem.appendChild(colElem);


		                colElem = document.createElement('td');
		                colElem.appendChild(document.createTextNode(note.note_name)); //to print cell number
		                rowElem.appendChild(colElem);
		           
		
				colElem = document.createElement('td');
				divElem = document.createElement("div");
				
				buttonElem = document.createElement("button");
				buttonElem.innerHTML="edit";
				buttonElem.id=""+i+"#"+0+""+"#"+note.note_id;
				buttonElem.setAttribute("onclick", "editNote(this)");
				buttonElem.setAttribute("style", "color: black;background-color: white;border-radius: 12px;font-family: 'Sansita Swashed', 'cursive';");
				divElem.appendChild(buttonElem);



				buttonElem = document.createElement("button");
				buttonElem.innerHTML="view";
				buttonElem.id=""+i+"#"+1+""+"#"+note.note_id;
				buttonElem.setAttribute("onclick", "viewNoteContent(this)");
				buttonElem.setAttribute("style", "color: black;background-color: white;border-radius: 12px;font-family: 'Sansita Swashed', 'cursive';");
				divElem.appendChild(buttonElem);



				buttonElem = document.createElement("button");
				buttonElem.innerHTML="delete";
				buttonElem.id=""+i+"#"+2+""+"#"+note.note_id;
				buttonElem.setAttribute("onclick", "deleteNote(this)");
				buttonElem.setAttribute("style", "color: black;background-color: white;border-radius: 12px;font-family: 'Sansita Swashed', 'cursive';");
				divElem.appendChild(buttonElem);


		                colElem.appendChild(divElem); //to print cell number
			  	rowElem.appendChild(colElem);





		

		            tableElem.appendChild(rowElem);
		        }

			document.getElementById("table_div").appendChild(tableElem);
		


		    }
		}

		
	function editNote(elem){
		var id = elem.id;
		note_id = id.split("#")[2];
		note = getnote_contentById(note_id);
		if(note!=null){
			hideViewNote();
			resetContentOfCreateNote();
			showCreateNote();
			document.getElementById("create_note_name").value=note.note_name;
			document.getElementById("create_note_content").value=note.note_content;
			document.getElementById("create_note_submit_button").setAttribute("name","edit");
			document.getElementById("create_note_id_div").innerText=note.note_id;
			
		}else{
			alert("Please try again later");
		}
	
		
		
	}
	
	
	function getnote_contentById(note_id){
		
		var username = document.getElementById("username_div").innerText;
		var url_='/noteApp/note?username='+username+'&note_id='+note_id;
		var note =null;
		$.ajax({
		      type: 'get',
		        url: url_,
		        async:false,
		        success: function(result) {
		        	console.log(result);
		        	note = result;
		        },
		        error: function() {
		         note= null;
		        }
		    });	
		
		return note;
	}
	
	function viewNoteContent(elem){
		var id = elem.id;
		note_id = id.split("#")[2];
		
		note = getnote_contentById(note_id);
		if(note!=null){
			hideViewNote();
			resetContentOfCreateNote();
			showCreateNote();
			document.getElementById("create_note_name").value=note.note_name;
			document.getElementById("create_note_content").value=note.note_content;
			document.getElementById("create_note_submit_button").disabled=true;
			document.getElementById("create_note_name").disabled=true;
			document.getElementById("create_note_content").disabled=true;
		} 
		else{
			alert("Please try again later");
		}
		
	}
	
	function deleteNote(elem){
		var id = elem.id;
		note_id = id.split("#")[2];
		var username = document.getElementById("username_div").innerText;
		var url_='/noteApp/note?username='+username+'&note_id='+note_id;
		var note =null;
		$.ajax({
		      type: 'delete',
		        url: url_,
		        async:false,
		        success: function(result) {
		        	console.log(result);
		        	if(result.valid){
		        		alert(result.message);
		        		viewNote();
		        	}else{
		        		alert(result.message);
		        	}
		        },
		        error: function() {
		         alert("Please try again later");
		        }
		    });	
		
		return note;
	}
	
	function searchNote(){
		var username = document.getElementById("username_div").innerText;
		var searchKey = document.getElementById("search_note_text").value;
		if(!searchKey) {
			alert("Please enter text in search box");
			return;
		}
		
		
		var url_='/noteApp/note/'+username+'/'+searchKey;
		var note =null;
		list_of_note=null;
		
		
		$.ajax({
		      type: 'get',
		        url: url_,
		        async:false,
		        success: function(result) {
		        	console.log(result);
		        	list_of_note=result;
		        	
		        },
		        error: function() {
		         alert("Please try again later");
		        }
		    });	
		
		if(list_of_note!=null){
			hideCreateNote();
			resetContentOfCreateNote();
			resetViewNoteTable();
			showViewNote();
			createTable(list_of_note);	
		}
		
		
	}
	</script>
	
	<style>
	@import url("https://fonts.googleapis.com/css2?family=Sansita+Swashed:wght@600&display=swap");
table {
  border-collapse: collapse;
  width: 100%;
}

th, td {
  padding: 8px;
  text-align: left;
  border-bottom: 1px solid #ddd;
  font-family: "Sansita Swashed", cursive;
}

ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: hidden;
  background-color: #333;
  font-family: "Sansita Swashed", cursive;
}

li {
  float: left;
  font-family: "Sansita Swashed", cursive;
}

li a {
  display: block;
  color: white;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-family: "Sansita Swashed", cursive;
}

li a:hover:not(.active) {
  background-color: #111;
}

.active {
  background-color: #04AA6D;
}
.inputField{
padding:10px;
  border-radius:10px;
  box-shadow:0 0 15px 4px rgba(0,0,0,0.06);
  font-family: "Sansita Swashed", cursive;
  }
  
  
  .myButton {
  background-color: white; /* Green */
 
  color: black;
  padding: 20px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
  border-radius: 12px;
  font-family: "Sansita Swashed", cursive;
}
</style>
</head>

<body>
<div id="username_div" style="display:none" >${username}</div>
	
<ul>
  <li><a href="#create_note"  onclick="createNote()">Create Note</a></li>
  <li><a href="#view_note" onclick="viewNote()">View Note</a></li>
  <li><a href="/noteApp/logout" >logout</a></li>
  <li style="float:right"><a  href="#username">${username}</a></li>
</ul>
	
	
	<div id="note_create_div" style="display:none"><br/>
		<input type="text" id="create_note_name" placeholder="Enter Note name" class="inputField" maxlength="50"/>
		<br/>
		<br/>
		<br/>
		<div id="note_content_div">
		<textarea id="create_note_content"  rows="38" cols="170" placeholder="Type here" style="resize:none" class="inputField" maxlength="6460">
 			Please enter text here
 		 </textarea>
		</div>
		<div id="create_note_id_div" style="display:none"></div>
		<button onclick="createNoteSubmit()" id="create_note_submit_button" name="create" class="myButton">Submit</button>
	</div>
	
	<div id="note_view_div" style="display:none">
	<br/>
	<br/>
	<br/>
		 <input type=text id="search_note_text" class="inputField" placeholder="Enter note_name"><button onclick="searchNote();" style="color: black;background-color: white;border-radius: 12px">Search</button>
		<br/>
	<br/>
	<br/>
		<div id="table_div">
		</div>
	</div>
	
	
	

</body>
	
</html>