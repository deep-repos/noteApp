<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<style>
@import url("https://fonts.googleapis.com/css2?family=Sansita+Swashed:wght@600&display=swap");
body {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background:white;
  font-family: "Sansita Swashed", cursive;
}
.center {
  position: relative;
  padding: 50px 50px;
  background: white;
  border-radius: 10px;
  border-width: thin;
  border-color: coral;
}
.center h1 {
  font-size: 2em;
  border-left: 5px solid dodgerblue;
  padding: 10px;
  color: #000;
  letter-spacing: 5px;
  margin-bottom: 60px;
  font-weight: bold;
  padding-left: 10px;
}
.center .inputbox {
  position: relative;
  width: 300px;
  height: 50px;
  margin-bottom: 50px;
}
.center .inputbox input {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  border: 2px solid #000;
  outline: none;
  background: none;
  padding: 10px;
  border-radius: 10px;
  font-size: 1.2em;
}
.center .inputbox:last-child {
  margin-bottom: 0;
}
.center .inputbox span {
  position: absolute;
  top: 14px;
  left: 20px;
  font-size: 1em;
  transition: 0.6s;
  font-family: sans-serif;
}
.center .inputbox input:focus ~ span,
.center .inputbox input:valid ~ span {
  transform: translateX(-13px) translateY(-35px);
  font-size: 1em;
}
.center .inputbox [type="button"] {
  width: 50%;
  background: dodgerblue;
  color: #fff;
  border: #fff;
}
.center .inputbox:hover [type="button"] {
  background: linear-gradient(45deg, greenyellow, dodgerblue);
}

</style>

</head>

<body>

<!-- <h1>Hi there </h1> -->
<h2>${success_message} </h2>
</body>
	<div id="system_error_id">${system_error}</div>
	<div id="user_creation_success_id">${user_creation_success_msg}</div>
		<div id="username_error_id" style="color:red">${username_error}</div><br/>
	<div id="password_error_id" style="color:red">${password_error}</div><br/>
	<!--<form:form method="post" action="/noteApp/process_signup" modelAttribute="user" >
		Username<form:input type="text" path="username"/><div id="username_error_id">${username_error}</div><br/>
		password<form:input type="password" path="password"/><div id="password_error_id">${password_error}</div><br/>
		<input type="submit" />
	</form:form>
	<a href="/noteApp/login">Already have account?login in</a>
	-->
	
		
	
	<div class="center">
  <h1>Note App</h1>
  <form:form method="post" action="/noteApp/process_signup" modelAttribute="user" >
  
    <div class="inputbox">
      <form:input type="text" path="username" required="required"/>
      <span>UserName</span>
      
    </div>
    
    <div class="inputbox">
     <form:input type="password" path="password" required="required"/>
      <span>Password</span>
      
    </div>
    
    <div class="inputbox">
   		 <input type="submit" value="Sign up" />
    </div>
  </form:form>
  <a href="/noteApp/login">Already have account?login in</a>
 


</div>




</html>