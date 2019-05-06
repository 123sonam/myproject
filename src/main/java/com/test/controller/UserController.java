package com.test.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.entity.FileBucket;
import com.test.entity.Login;
import com.test.entity.User;
import com.test.entity.UserDocument;
import com.test.repository.UserRepository;


@Controller
public class UserController {
	
	

	@Autowired 
	private UserRepository repo;
	
	
	@RequestMapping(value="/homePage")
	public String home() {
		return "HomePage";
	}
	
	@RequestMapping(value="/loginPage")
	public String loginPage(Model model) {
		model.addAttribute("userLogin",new Login());
		return "LoginPage";
	}
	
	@RequestMapping(value="loginDetail",method=RequestMethod.POST)
	public String login(
			HttpServletRequest servletRequest,RedirectAttributes redirectAttributes) {
		String email=servletRequest.getParameter("email");
		String password=servletRequest.getParameter("password");
		
		  Integer id=repo.loginUser(email,password);
		  System.err.println(id);
		if(id!= 0) {
		HttpSession httpSession= servletRequest.getSession(true);
		httpSession.setAttribute("userId", id);
		return "WelcomePage";
		}
		redirectAttributes.addFlashAttribute("message", "Invalid User");
		return "redirect:/homePage";
	}
	
	
	@RequestMapping(value="/registerPage")
	public String registerPage(Model model) {
		model.addAttribute("userDto", new User());
		return "RegistrationPage";
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String registration(@ModelAttribute("userDto") @RequestBody User user,RedirectAttributes attributes) {
		if(user.getId()==null) {
			System.err.println(user.getEmail());
		  repo.addUser(user);
		  attributes.addFlashAttribute("message", "User Register Successfully");
		  return "redirect:/loginPage";
		}
		else {
			System.err.println("kkk");
			repo.updateUser(user);
			return "WelcomePage";
		}
	}
	
	@RequestMapping(value="/allUser", method = RequestMethod.GET)
	public String allUserList(ModelMap map) {
		List<User> user=repo.list();
		
			System.err.println(user);
			map.addAttribute("users", user);
		return "UserList";
	}
	
	@RequestMapping(value="/userFind/{id}")
	public String findById(@PathVariable int id,Model model) {
		User user=repo.findUserId(id);
	/*	if(!user1.equals(null)) {*/
		System.err.println(user);
			model.addAttribute("userDto", user);
		/*	ModelAndView view=new ModelAndView("RegistrationPage");*/
			return "RegistrationPage";
	/*	}
		else {
			List<User> user=repo.list();
			ModelAndView view=new ModelAndView("UserList", "user", user);
			return view;
		}*/
	}
	 @RequestMapping(value = { "/add-document/{userId}" }, method = RequestMethod.GET)
	    public String addDocuments(@PathVariable int userId, ModelMap model) {
	        User user = repo.findUserId(userId);
	        model.addAttribute("user", user);
	 
	        FileBucket fileModel = new FileBucket();
	        model.addAttribute("fileBucket", fileModel);
	 
	 /*       List<UserDocument> documents = repo.findAllByUserId(userId);
	        model.addAttribute("documents", documents);
	         */
	        return "managedocuments";
	    }
	 
	 @RequestMapping(value = { "/add-document/{userId}" }, method = RequestMethod.POST)
	    public String uploadDocument(FileBucket fileBucket, ModelMap model, @PathVariable int userId) throws IOException{
	  
	            System.out.println("Fetching file");
	             
	            User user = repo.findUserId(userId);
	            model.addAttribute("user", user);
	 
	            saveDocument(fileBucket, user);
	 
	            return "redirect:/add-document-"+userId;
	        }
	    
	     
	    private void saveDocument(FileBucket fileBucket, User user) throws IOException{
	         
	        UserDocument document = new UserDocument();
	         
	        MultipartFile multipartFile = fileBucket.getFile();
	         
	        document.setName(multipartFile.getOriginalFilename());
	        document.setDescription(fileBucket.getDescription());
	        document.setType(multipartFile.getContentType());
	        document.setContent(multipartFile.getBytes());
	        document.setUser(user);
	        repo.saveDocument(document);
	    }
	@RequestMapping(value="logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		session.invalidate();
		return "HomePage";
	}
}
