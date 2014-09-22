package com.ss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ss.checkValidations.PasswordValidator;
import com.ss.form.entity.ChangePasswordFormBean;
import com.ss.service.OTPService;
import com.ss.service.UserManager;



@Controller
@SessionAttributes
//@RequestMapping(value = "OTP")
public class OTPController {

	@Autowired
	private OTPService otpService;
	
	@Autowired
	private UserManager userManager;

	@RequestMapping(value = "/OTP/newPassword", method = RequestMethod.POST)
	public ModelAndView persistNewPassword(@ModelAttribute("password_confirm") String pass_con, @ModelAttribute("newPassword") String pass , @ModelAttribute("changePasswordFormBean") ChangePasswordFormBean changePasswordFormBean, BindingResult result, ModelMap model) throws Exception {
		
		String newPassword = changePasswordFormBean.getNewPassword();
		String enteredOTP = changePasswordFormBean.getOneTimePassword();
		String userName = changePasswordFormBean.getUserName();
		
		int s1 = newPassword.length();
		PasswordValidator pv = new PasswordValidator();
		boolean y = pv.validate(newPassword);
		
		ModelAndView model10 = new ModelAndView("/otp/errMsg");
		ModelAndView model11 = new ModelAndView("/otp/errMsg1");

		
		if(s1<=7 || s1>33 || !(y))
			return model10;

		if(!(pass_con.equals(newPassword)))
			return model11;
		
		
		boolean hasPasswordBeenChanged = otpService.validateOTPAndPersistNewPwd(userName,newPassword,enteredOTP);

		if(hasPasswordBeenChanged){
			model.addAttribute("otpGenerationSuccessMessage", "Password changed successfully.");
			return new ModelAndView("/login",model);
		}
		else{
			model.addAttribute("changePasswordFormBean",changePasswordFormBean);
			model.addAttribute("otpGenerationErrorMessage", "You entered incorrect OTP. Please try again");
			return new ModelAndView("otp/changePassword",model);
		}
	}
	
	@RequestMapping(value="/OTP/forgotPassword", method = RequestMethod.GET)
	public String login(@ModelAttribute("changePasswordFormBean") ChangePasswordFormBean changePasswordFormBean, BindingResult result, ModelMap model) throws Exception {
		return "otp/oneTimePassword";
	}
	
	@RequestMapping(value = "/OTP/changePassword", method = RequestMethod.POST)
	public String changePassword (@ModelAttribute("changePasswordFormBean") ChangePasswordFormBean changePasswordFormBean, BindingResult result, ModelMap model) throws Exception {
		String emailId = changePasswordFormBean.getEmailId();
		String userName = changePasswordFormBean.getUserName();
		int createdEntries =  otpService.generateAndPersistOTP(userName, emailId);
		if(createdEntries > 0){			
			return "otp/changePassword";
		}
		else{
			model.addAttribute("changePasswordFormBean",changePasswordFormBean);
			model.addAttribute("otpErrorMessage", "Incorrect User Id And/Or EmailId.");
			return "otp/oneTimePassword";
		}		
	}	
}