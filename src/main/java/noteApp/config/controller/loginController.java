package noteApp.config.controller;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import noteApp.config.bean.Note;
import noteApp.config.bean.User;
import noteApp.config.service.UserService;
import noteApp.config.service.ValidateService;



@Controller
public class loginController {
	
	
		@Autowired
		ValidateService  validateService;
		
		@Autowired
		UserService userService;
		
	
		//url="http://localhost:8080/noteApp/login"
		@GetMapping("/login")
		public ModelAndView showLogin() {
			System.out.println("in showLogin");
			ModelAndView mav =new ModelAndView("login");
			mav.addObject("user",new User());
			return mav;
			
		}
		

		@PostMapping("/process_login")
		public ModelAndView processLogin(@ModelAttribute("user") User user ) {
			System.out.println("in processLogin");
			System.out.println(user);
			//authenticating user
			boolean validUser = validateService.validateUsername(user.getUsername());
			boolean validPassword= validateService.validatePassword(user.getPassword());
			System.out.println("validUser>>"+validUser);
			System.out.println("validPassword>>"+validPassword);
			
			if(validUser && validPassword) {
				ModelAndView mav =new ModelAndView("login");
				User user_db = userService.getUserByUsername(user.getUsername());
				System.out.println("user_db>>"+user_db);
				if(user_db==null) {
					
						mav.addObject("username_error","username not exists");
					
				}else {
					if(user_db.getPassword().equals(user.getPassword())) {
						ModelAndView mav1 =new ModelAndView("home");
						mav1.addObject("username",user.getUsername());
						return mav1;
					}else {
						mav.addObject("password_error","wrong password");
					}
				
				}
				
				return mav;
				
			}else {
				ModelAndView mav =new ModelAndView("login");
				
				if(!validUser) {
					mav.addObject("username_error","Please enter proper username");
				}
				if(!validPassword) {
					mav.addObject("password_error","Please enter proper password");
				}
				
				return mav;
			}
			
			
			
			
		}
		
		
		//url="http://localhost:8080/noteApp/signup"
		@GetMapping("/signup")
		public ModelAndView showSignUp() {
			System.out.println("in showSignUp");
			ModelAndView mav =new ModelAndView("signup");
			mav.addObject("user",new User());
			return mav;
			
		}
		
		
		@PostMapping("/process_signup")
		public ModelAndView processSignUp(@ModelAttribute("user") User user) {
			System.out.println(user);
			boolean validUser = validateService.validateUsername(user.getUsername());
			boolean validPassword= validateService.validatePassword(user.getPassword());
			System.out.println("validUser>>"+validUser);
			System.out.println("validPassword>>"+validPassword);
			user.setPassword(user.getPassword().trim());
			user.setUsername(user.getUsername().trim());
			
			
			if(validUser && validPassword) {
				/**
				 *do database lookup
				 *if username present then return error
				 *else proceeed
				 **/
				//user already present with same username
				if(userService.getUserByUsername(user.getUsername())!=null) {
					System.out.println("user already present ");
					ModelAndView mav =new ModelAndView("signup");
					mav.addObject("username_error","User with same username is already present try different username");
					mav.addObject("user",new User());
					return mav;
				}else {
					//create user
					//user created sucessfully
					if(userService.createUser(user)==true) {
						System.out.println("user created ");
						ModelAndView mav =new ModelAndView("signup");
						mav.addObject("user_creation_success_msg","User is created please click on login to use system");
						mav.addObject("user",new User());
						return mav;
					}else {
						//user not created some db issue :(
						System.out.println("db issue while creating user");
						ModelAndView mav =new ModelAndView("signup");
						mav.addObject("system_error","Please sign up after some time");
						mav.addObject("user",new User());
						return mav;
					}
				}
				
				
			}else {
				ModelAndView mav =new ModelAndView("signup");
				if(!validUser) {
					mav.addObject("username_error","Please enter proper username");
				}
				if(!validPassword) {
						mav.addObject("password_error","Password should contain alphabet,digits and special symbols,and length should be atleast 8 characters");
				}
				mav.addObject("user",new User());
				return mav;
			}
			
		}
		
		@GetMapping("/hello_ajax")
		@ResponseBody
		public LinkedList<Note> f1(@RequestParam("username") String name,@RequestParam("password") String pass) {
			LinkedList<Note> ll =new LinkedList<Note>();
			Note n =new Note();
			n.setNote_id("1234");
			n.setNote_name("xyzzza");
			
			ll.add(n);
			
			 n =new Note();
			n.setNote_id("9999");
			n.setNote_name("ttttta");
			
			ll.add(n);
			
			return ll;
		}
		
		@GetMapping("/logout")
		public ModelAndView doLogout() {
			System.out.println("in logout");
			ModelAndView mav =new ModelAndView("login");
			mav.addObject("user",new User());
			return mav;
			
		}
}
