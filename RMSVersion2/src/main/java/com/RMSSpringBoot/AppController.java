package com.RMSSpringBoot;
import org.springframework.stereotype.Controller;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.validation.BindingResult;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Duration;

import java.io.IOException;
import java.util.List;

import org.springframework.util.*;

@Controller
public class AppController implements ErrorController {
	
	@Autowired
	private PalsRepository repo;
	
	@Autowired
	private NotRepository repoN;
	
	@Autowired
	private DelRepository repoD;
	
	@Autowired
	private ActRepository repoA;
	
	
	LocalDate localDateToDay = LocalDate.now();
	
	//Declare models here
	Notifications notifications = new Notifications();
	Pals pals = new Pals();
	
	@GetMapping("")
	public String viewHomePage()
	{
		return "login";
		//was returning the index page before now its the login page
	} 
	
	@GetMapping("/addaPal")
	public String showAddPalForm(Model model)
	{
		model.addAttribute("pals",new Pals());
		
		//model.AddAttribute("notifications",new Notifications());
		
		return "addpal_form";
		
		
		
	}
	
	@PostMapping("/process_addaPal")
//	public RedirectView  processPals(Pals pals, 
		//	@RequestParam("image") MultipartFile multipartFile) throws IOException)
	
	
//	 public String processPals(Pals pals) {
//	          
//	       repo.save(pals);
//		
//		return "AddPal_success";
//		
//	}
	
	 public String processPals(Pals pals,
	            @RequestParam("image") MultipartFile multipartFile) throws IOException {
		 	String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
	        pals.setPicture(fileName);
	        
	        //get days last contacted before
	        Date myp_lstcontacted = pals.getLastContacted();
	        LocalDate dateDB = myp_lstcontacted.toLocalDate();
			long diffDays = Duration.between(dateDB.atStartOfDay(), localDateToDay.atStartOfDay()).toDays();
	        pals.setNumberofDaysSinceLastContact(diffDays);
			
	        Pals savedPals = repo.save(pals);
	        String uploadDir = "pals-picture/" + savedPals.getMyp_id();
	        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
	        
	    /*  //All variables needed for class notifications fill up
	        Notifications firstNotes = new Notifications();
	        Long myp_id = savedPals.getMyp_id();
	        String myp_name = savedPals.getName();
	        Date myp_lstcontacted = savedPals.getLastContacted();
	        //Calculating last contacted  days.
	        LocalDate dateDB = myp_lstcontacted.toLocalDate();
			long diffDays = Duration.between(dateDB.atStartOfDay(), localDateToDay.atStartOfDay()).toDays();
			
			//setting the values for not class
			firstNotes.setMyp_id(myp_id);
			firstNotes.setMyp_name(myp_name);
			firstNotes.setMyp_lstcontacted(myp_lstcontacted);
			firstNotes.setNumOfDaysSinceLastContact(diffDays);
			repoN.save(firstNotes);*/
			
	        return "redirect:/home";
		
	}
	
	@PostMapping("/process_activities")
	public String saveActivities(Activities activities)
	{
		//get the id of name
		//use that id to find the name in pals
		//get the name from pals
		//set the or save activities details
		Long newMypId = activities.getValholder();
		Pals pals = repo.findById(newMypId)
			      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + newMypId));
		String newMypName = pals.getName();
		activities.setMyp_id(newMypId);
		activities.setActPartner(newMypName);
		
		repoA.save(activities);
		
			    
		 return "redirect:/home";	
		
	}
	
	
	
	
	@GetMapping("/list_users")
	
	public String viewUsersList()
	{
		return "users";
	}
	
	@GetMapping("/list_pals")
	public String viewPalsList(Model model)
	{
		List<Pals> listAllPals = repo.findAll();
		model.addAttribute("listAllPals",listAllPals);
		return "pals";
	}
	
	@GetMapping("/delFriendsList")
	public String viewDeletedPalList(Model model)
	{
		List<DeletedFriends> listAllDelPals = repoD.findAll();
		model.addAttribute("listAllDelPals",listAllDelPals);
		
		return "delFriendsList";
		
	}
	
	@GetMapping("/home")
	public String welcomePage(Model model)
	{
		/*Add notifications and activity notices here*/
		List<Pals> listAllPals = repo.findAll();
		model.addAttribute("listAllPals",listAllPals);
		
		//System.out.println(listAllPals);
		
		return "home";
	}
	
	//To populate select box with data
	@GetMapping("/addActivityForm")
	public String showPalListActivitiesSelect(Model model) 
	{
		List<Pals> valholder = repo.findAll();
		model.addAttribute("valholder",valholder);
		
		model.addAttribute("activities",new Activities());
		
		return "addActivityForm";
		
	}
	
	@GetMapping("/activityList")
	public String showActivityList(Model model)
	{
		List<Activities> activityList = repoA.findAll();
		model.addAttribute("activityList",activityList);
		
		return "activityList";
		
	}
	
	
	@GetMapping("/login")
	public String showLoginPage()
	{
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken)
		{
			return "login";
		}
		return "redirect:/";
		
	}
	

	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
	    Pals pals = repo.findById(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	    
	    model.addAttribute("pals", pals);
	    return "update_pal";
	}
	
	@GetMapping("/delete/{id}")
	public String deletePal(@PathVariable("id") long id, Model model)
	{
		Pals pals = repo.findById(id)
		.orElseThrow(()->new IllegalArgumentException("Invalid user Id:" + id));
		//before deleting anything get the id and name of the person.
		DeletedFriends deletedFriends = new DeletedFriends();
		
		Long mypId = pals.getMyp_id();		
		String name = pals.getName();
		
		deletedFriends.setMyp_id(mypId);
		deletedFriends.setName(name);
		
		repo.delete(pals);
		
		 model.addAttribute("deletedFriends", deletedFriends);
		return "deletedPalForm";
		
		
	}
	
	@GetMapping("/remove/{id}")
	public String removeActivity(@PathVariable("id") long id,Model model)
	{
		Activities activities = repoA.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid user Id:" + id));
		repoA.delete(activities);
		
		return "redirect:/activityList";
		
	}
	
	@GetMapping("/notifications")
	public String showNotifications(Model model)
	{
		List<Notifications> listAllNotifications = repoN.findAll();
		model.addAttribute("listAllNotifications",listAllNotifications);
	
		return "notifications";
		
	}
	
	@GetMapping("/deletedPalForm")
	public String showFeedbackDeleteForm(Model model)
	{
		
		model.addAttribute("deletedFriends",new DeletedFriends());
		
		return "deletedPalForm";
		
	}
	
/*	@PostMapping("/update/{id}")
	public String updatePal(@PathVariable("id") long id, Pals pals,
		BindingResult result, Model model) {
		    if (result.hasErrors()) {
		        pals.setMyp_id(id);
		        return "update_pal";
		    }
		        
		   repo.save(pals);
		    return "redirect:/home";
		}*/
		    
	@PostMapping("/process_deletePal")
	public String procesDeleteForm(DeletedFriends deletedFriends)
	{
		repoD.save(deletedFriends);
		
		return "redirect:/home";
		
	}
	
	@GetMapping("/error")
	public String handleError(HttpServletRequest request)
	{
		String errorPage = "error";
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		
		if(status != null) 
		{
			Integer statusCode = Integer.valueOf(status.toString());
			if(statusCode == HttpStatus.NOT_FOUND.value()) 
			{
				 // handle HTTP 404 Not Found error
				errorPage = "error/404";
			}
			else if(statusCode == HttpStatus.FORBIDDEN.value()) 
			{
				 // handle HTTP 403 Forbidden error
				errorPage = "error/403";
			}
			else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) 
			{
				// handle HTTP 500 Internal Server error
				errorPage = "error/500";
			}
			
		}
		return errorPage;
	}

@Override
public String getErrorPath() {
	return "/error";
}

	
	
}
