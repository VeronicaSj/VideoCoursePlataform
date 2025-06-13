package videocurseapp.demo.Controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import videocurseapp.demo.Model.Image;
import videocurseapp.demo.Model.User;
import videocurseapp.demo.Utilities.NavLink;
import videocurseapp.demo.Utilities.NavLinkGetter;

@Controller
public class ControllerStaticParent {
    public static final NavLinkGetter NAV_LINK_GETTER= new NavLinkGetter();
    public String lang="";

    public Model basicModelGenerator(User user, Model model, String title) {
        ArrayList<NavLink> linklist = NAV_LINK_GETTER.getInAppNavLinkList();
        if(user.isProfessor()){
            linklist.add(new NavLink("/courses", getString(MSG_NAV_COURSES)));
        }
        model.addAttribute("navLinkList", linklist);
        
        
        if(user!=null){
            model.addAttribute("avatar", user.getAvatar());
            lang=user.getLang();
        }
        
        model.addAttribute("title", title);
        model.addAttribute("defaultImg", Image.DEFAULT_IMG);
        model.addAttribute("subtitle", title + " | AcadeMice");
        return model;
    }

    //Translation id variables will be constructed as: "MSG_TranslationEndpointCode_modelIdentifier"  
    public static int id = 0;

    public static final int MSG_NAV_LOG_IN = getId();
    public static final int MSG_NAV_SIGN_UP = getId();
    public static final int MSG_NAV_ABOUT = getId();
    public static final int MSG_NAV_HOME = getId();
    public static final int MSG_NAV_ACCOUNT = getId();
    public static final int MSG_NAV_HELP = getId();
    public static final int MSG_NAV_COURSES = getId();

    public static final int MSG_HALL_INDEX_TITLE = getId();
    public static final int MSG_HALL_INDEX_SUBTITLE = getId();
    public static final int MSG_HALL_INDEX_DESCRIPTION = getId();    
    public static final int MSG_HALL_INDEX_START_BTN = getId();

    public static final int MSG_account1_title = getId();

    public static final int MSG_account1_sidebarEdit = getId();
    public static final int MSG_account1_sidebarPw = getId();
    public static final int MSG_account1_sidebarLogOut = getId();
    public static final int MSG_account1_sidebarDelete = getId();
    public static final int MSG_account1_sidebarSwitch = getId();
    public static final int MSG_account1_sidebarBank = getId();

    public static final int MSG_account1_isProfessorMsg = getId();
    public static final int MSG_account1_uploadCurseMsg = getId();

    public static final int MSG_account2_title = getId();
    public static final int MSG_account2_langLbl = getId();
    public static final int MSG_account2_avatarLbl = getId();
    public static final int MSG_account2_emailLbl = getId();
    public static final int MSG_account2_editBtn = getId();

    public static final int MSG_account3_title = getId();
    public static final int MSG_account3_msg1 = getId();
    public static final int MSG_account3_msg2 = getId();
    public static final int MSG_account3_msg3 = getId();
    public static final int MSG_account3_msg4 = getId();

    public static final int MSG_account4_title = getId();
    public static final int MSG_account4_oldPwLbl = getId();
    public static final int MSG_account4_newPwLbl = getId();
    public static final int MSG_account4_mtchPwLbl = getId();
    public static final int MSG_account4_submitBtn = getId();

    public static final int MSG_account5_title = getId();
    public static final int MSG_account5_msg1 = getId();
    public static final int MSG_account5_msg2 = getId();
    public static final int MSG_account5_msg3 = getId();

    public static final int MSG_account6_title = getId();
    public static final int MSG_account6_subtitle = getId();
    public static final int MSG_account6_btnmsg = getId();
    public static final int MSG_account6_titularLbl = getId();
    public static final int MSG_account6_IBANLbl = getId();

    public static final int MSG_account7_title = getId();
    public static final int MSG_account7_subtitle = getId();
    public static final int MSG_account7_btnmsg = getId();
    public static final int MSG_account7_msg1 = getId();
    public static final int MSG_account7_msg2 = getId();

    public static final int MSG_account8_title = getId();
    public static final int MSG_account8_subtitle = getId();
    public static final int MSG_account8_btnmsg = getId();
    public static final int MSG_account8_titularLbl = getId();
    public static final int MSG_account8_IBANLbl = getId();

    public static final int MSG_account9_title = getId();
    public static final int MSG_account9_subtitle = getId();
    public static final int MSG_account9_btnmsg = getId();
    public static final int MSG_account9_msg1 = getId();
    public static final int MSG_account9_msg2 = getId();

    public static final int MSG_account10_title = getId();
    public static final int MSG_account10_deleteAccountWarningMsg = getId();
    public static final int MSG_account10_deleteBtn = getId();
    public static final int MSG_account10_cancelBtn = getId();

    public static final int MSG_account11_msg = getId();

    public static final int MSG_course1_title = getId();
    public static final int MSG_course1_uploadLink = getId();
    public static final int MSG_course1_noCourseMsg = getId();

    public static final int MSG_course2_title = getId();
    public static final int MSG_course2_WatchBtn = getId();
    public static final int MSG_course2_UpdateBtn = getId();
    public static final int MSG_course2_DeleteBtn = getId();
    public static final int MSG_course2_courseTitleLbl = getId();
    public static final int MSG_course2_DescriptionLbl = getId();
    public static final int MSG_course2_PriceLbl = getId();
    public static final int MSG_course2_coinLbl = getId();
    public static final int MSG_course2_publicLbl = getId();
    public static final int MSG_course2_VideoListLbl = getId();

    public static final int MSG_course3_title = getId();
    public static final int MSG_course3_msg = getId();
    public static final int MSG_course3_deleteBtn = getId();
    public static final int MSG_course3_cancelBtn = getId();

    public static final int MSG_course6_title = getId();
    public static final int MSG_course6_sidebartitle = getId();
    public static final int MSG_course6_sidebarNoVideo = getId();
    public static final int MSG_course6_noVideo = getId();

    public static final int MSG_course7_title = getId();
    public static final int MSG_course7_nameLbl = getId();
    public static final int MSG_course7_descriptionLbl = getId();
    public static final int MSG_course7_priceLbl = getId();
    public static final int MSG_course7_coinLbl = getId();
    public static final int MSG_course7_isPublicLbl = getId();
    public static final int MSG_course7_nameErrorMsg = getId();
    public static final int MSG_course7_descriptionErrorMsg = getId();
    public static final int MSG_course7_priceErrorMsg = getId();
    public static final int MSG_course7_coinErrorMsg = getId();
    public static final int MSG_course7_isPublicErrorMsg = getId();
    public static final int MSG_course7_btnNextMsg = getId();

    public static final int MSG_course8_title = getId();
    public static final int MSG_course8_nameLbl = getId();
    public static final int MSG_course8_descriptionLbl = getId();
    public static final int MSG_course8_priceLbl = getId();
    public static final int MSG_course8_coinLbl = getId();
    public static final int MSG_course8_isPublicLbl = getId();
    public static final int MSG_course8_nameErrorMsg = getId();
    public static final int MSG_course8_descriptionErrorMsg = getId();
    public static final int MSG_course8_priceErrorMsg = getId();
    public static final int MSG_course8_coinErrorMsg = getId();
    public static final int MSG_course8_isPublicErrorMsg = getId();
    public static final int MSG_course8_btnNextMsg = getId();

    public static final int MSG_course9_title = getId();
    public static final int MSG_course9_nameLbl = getId();
    public static final int MSG_course9_descriptionLbl = getId();
    public static final int MSG_course9_priceLbl = getId();
    public static final int MSG_course9_coinLbl = getId();
    public static final int MSG_course9_isPublicLbl = getId();
    public static final int MSG_course9_nameErrorMsg = getId();
    public static final int MSG_course9_descriptionErrorMsg = getId();
    public static final int MSG_course9_priceErrorMsg = getId();
    public static final int MSG_course9_coinErrorMsg = getId();
    public static final int MSG_course9_isPublicErrorMsg = getId();
    public static final int MSG_course9_btnNextMsg = getId();
    
    public static final int MSG_course11_title = getId();

    public static final int MSG_course12_title = getId();
    public static final int MSG_course12_btnAddNewMsg = getId();
    public static final int MSG_course12_btnNextMsg = getId();
    public static final int MSG_course12_videonameLbl = getId();
    public static final int MSG_course12_addBtn = getId();
    public static final int MSG_course12_nextBtn = getId();

    public static final int MSG_course13_title = getId();
    public static final int MSG_course13_btnUploadMsg = getId();
    public static final int MSG_course13_VideotitleLbl = getId();
    public static final int MSG_course13_orderWarning = getId();

    public static final int MSG_course14_title = getId();
    public static final int MSG_course14_msg1 = getId();
    public static final int MSG_course14_titleErrorMsg = getId();
    public static final int MSG_course14_msg2 = getId();
    public static final int MSG_course14_msg3 = getId();
    public static final int MSG_course14_btnUploadMsg = getId();
    public static final int MSG_course14_VideotitleLbl = getId();
    public static final int MSG_course14_orderWarning = getId();

    public static final int MSG_course15_title = getId();
    public static final int MSG_course15_courseTitleLbl = getId();
    public static final int MSG_course15_DescriptionLbl = getId();
    public static final int MSG_course15_PriceLbl = getId();
    public static final int MSG_course15_coinLbl = getId();
    public static final int MSG_course15_publicLbl = getId();
    public static final int MSG_course15_VideoListLbl = getId();
    public static final int MSG_course15_btnNextMsg = getId();
    
    public static final int MSG_course16_title = getId();
    public static final int MSG_course16_msg = getId();

    public static final int MSG_hall1_title = getId();
    public static final int MSG_hall1_subtitle = getId();
    public static final int MSG_hall1_description = getId();
    public static final int MSG_hall1_errorMsg = getId();
    public static final int MSG_hall1_userLbl = getId();
    public static final int MSG_hall1_pwLbl = getId();
    public static final int MSG_hall1_submitBtn = getId();

    public static final int MSG_hall3_title = getId();
    public static final int MSG_hall3_subtitle = getId();
    public static final int MSG_hall3_description = getId();
    public static final int MSG_hall3_langLbl = getId();
    public static final int MSG_hall3_Userlbl = getId();
    public static final int MSG_hall3_Emaillbl = getId();
    public static final int MSG_hall3_PassWordlbl = getId();
    public static final int MSG_hall3_Matchinglbl = getId();
    public static final int MSG_hall3_submitbtn = getId();

    public static final int MSG_hall4_usernameInUseErrorMsg = getId();
    public static final int MSG_hall4_errorEmailMsg = getId();

    public static final int MSG_hall6_title = getId();
    public static final int MSG_hall6_subtitle = getId();
    public static final int MSG_hall6_description = getId();
    public static final int MSG_hall6_msg1 = getId();
    public static final int MSG_hall6_msg2 = getId();
    public static final int MSG_hall6_msg3 = getId();
    public static final int MSG_hall6_msg4 = getId();
    public static final int MSG_hall6_startBtn = getId();

    public static final int MSG_help_title = getId();
    public static final int MSG_help_noCourse = getId();
    
    public static final int MSG_home1_title = getId();
    public static final int MSG_home1_noCourseMsg = getId();
    public static final int MSG_home1_searchLbl = getId();
    public static final int MSG_home1_searchBtn = getId();

    public static final int MSG_home2_title = getId();
    public static final int MSG_home2_noCourseMsg = getId();
    public static final int MSG_home2_searchLbl = getId();
    public static final int MSG_home2_searchBtn = getId();

    public static final int MSG_home3_title1 = getId();
    public static final int MSG_home3_title2 = getId();
    public static final int MSG_home3_msg = getId();
    public static final int MSG_home3_btnbuyMsg1 = getId();
    public static final int MSG_home3_btnbuyMsg2 = getId();
    public static final int MSG_home3_courseTitleLbl = getId();
    public static final int MSG_home3_DescriptionLbl = getId();
    public static final int MSG_home3_PriceLbl = getId();
    public static final int MSG_home3_coinLbl = getId();
    public static final int MSG_home3_publicLbl = getId();
    public static final int MSG_home3_videoListLbl = getId();

    public static final int MSG_home4_title1 = getId();
    public static final int MSG_home4_title2 = getId();
    public static final int MSG_home4_btnbuyMsg = getId();
    public static final int MSG_home4_subtitle = getId();
    public static final int MSG_home4_aceptedCardsLbl = getId();
    public static final int MSG_home4_totalLbl = getId();
    public static final int MSG_home4_currencyLbl = getId();
    public static final int MSG_home4_methodLbl = getId();
    public static final int MSG_home4_intentLbl = getId();
    public static final int MSG_home4_descriptionLbl = getId();

    public static final int MSG_image1_title1 = getId();
    public static final int MSG_image1_btnUploadMsg = getId();
    public static final int MSG_image1_btnNextMsg = getId();

    public static final int MSG_image2_title = getId();
    public static final int MSG_image2_msg1 = getId();
    public static final int MSG_image2_msg2 = getId();
    public static final int MSG_image2_msg3 = getId();
    public static final int MSG_image2_btnUploadMsg = getId();
    public static final int MSG_image2_btnNextMsg = getId();

    public static final int MSG_video1_title1 = getId();

    public static final int MSG_ = getId();

    public String getString(int id){
        String res = "Error getting text";
        if (lang.equals("Español")) {
            res = getSpanishString(id);
        } else {//english is the default languaje
            res = getEnglishString(id);
        }
        return res;
    }

    public String getEnglishString(int id){
        String res = "Error de traduccion";
        if (id == MSG_HALL_INDEX_TITLE) {
            res = "AcadeMice";
        } else if (id == MSG_HALL_INDEX_SUBTITLE) {
            res = "Best teaching and learning plataform";
        } else if (id == MSG_HALL_INDEX_DESCRIPTION) {
            res = "The best online course platform for people passionate about education";
        } else if (id == MSG_HALL_INDEX_START_BTN) {
            res = "Start";
        } 
        
        else if (id == MSG_NAV_LOG_IN) {
            res = "LogIn";
        } else if (id == MSG_NAV_SIGN_UP) {
            res = "SignUp";
        } else if (id == MSG_NAV_ABOUT) {
            res = "About";
        } else if (id == MSG_NAV_HOME) {
            res = "Home";
        } else if (id == MSG_NAV_ACCOUNT) {
            res = "My Acount";
        } else if (id == MSG_NAV_HELP) {
            res = "Help";
        } else if (id == MSG_NAV_COURSES) {
            res = "My Courses";
        } 
        
            else if (id == MSG_account1_title) {
            res = "My Account";
        } else if (id == MSG_account1_sidebarEdit) {
            res = "Edit Account";
        } else if (id == MSG_account1_sidebarPw) {
            res = "Change Password";
        } else if (id == MSG_account1_sidebarDelete) {
            res = "Delete Account";
        } else if (id == MSG_account1_sidebarLogOut) {
            res = "Log Out";
        } else if (id == MSG_account1_sidebarSwitch) {
            res = "Permanentelly switch to Professor";
        } else if (id == MSG_account1_sidebarBank) {
            res = "Update Bank Data";
        } else if (id == MSG_account1_isProfessorMsg) {
            res = "Professor account";
        } else if (id == MSG_account1_uploadCurseMsg) {
            res = "Upload a curse";
        }

         else if (id == MSG_account2_title) {
            res = "Edit Account";
        } else if (id == MSG_account2_langLbl) {
            res = "Language:";
        } else if (id == MSG_account2_avatarLbl) {
            res = "Avatar Image: ";
        } else if (id == MSG_account2_emailLbl) {
            res = "Email: ";
        } else if (id == MSG_account2_editBtn) {
            res = "Edit";
        } 
        
        else if (id == MSG_account3_title) {
            res =  "Edit Account";
        } else if (id == MSG_account3_msg1) {
            res = "Your acount could not be updated";
        } else if (id == MSG_account3_msg2) {
            res = ". Your new mail was not valid";
        } else if (id == MSG_account3_msg3) {
            res = "Could not upload the image: ";
        } else if (id == MSG_account3_msg4) {
            res = "Your account was succesfully changed";
        }
        
        else if (id == MSG_account4_title) {
            res = "Change Account Password";
        } else if (id == MSG_account4_oldPwLbl) {
            res = "Old PassWord: ";
        } else if (id == MSG_account4_newPwLbl) {
            res = "New PassWord: ";
        } else if (id == MSG_account4_mtchPwLbl) {
            res = "Matching New PassWord: ";
        } else if (id == MSG_account4_submitBtn) {
            res = "Change PassWord";
        } 
        
        else if (id == MSG_account5_title) {
            res = "Change Account Password";
        } else if (id == MSG_account5_msg1) {
            res = "Your pasword could not be changed";
        } else if (id == MSG_account5_msg2) {
            res = "Your pasword was succesfully changed";
        } else if (id == MSG_account5_msg3) {
            res = ", your new password didn't match the retype";
        }
        
         else if (id == MSG_account6_title) {
            res = "Update Professor Account";
        } else if (id == MSG_account6_subtitle) {
            res = "Bank Data";
        } else if (id == MSG_account6_btnmsg) {
            res = "Update";
        } else if (id == MSG_account6_titularLbl) {
            res = "Titular: ";
        } else if (id == MSG_account6_IBANLbl) {
            res = "IBAN: ";
        } 
        
        else if (id == MSG_account7_title) {
            res = "Update Professor Account";
        } else if (id == MSG_account7_subtitle) {
            res = "Bank Data";
        } else if (id == MSG_account7_btnmsg) {
            res = "Update";
        } else if (id == MSG_account7_msg1) {
            res = "Your acount could not be updated";
        } else if (id == MSG_account7_msg2) {
            res = "Banck data updated";
        } 

        else if (id == MSG_account8_title) {
            res = "Update Professor Account";
        } else if (id == MSG_account8_subtitle) {
            res = "Bank Data";
        } else if (id == MSG_account8_btnmsg) {
            res = "Switch";
        } else if (id == MSG_account8_titularLbl) {
            res = "Titular: ";
        } else if (id == MSG_account8_IBANLbl) {
            res = "IBAN: ";
        } 

        else if (id == MSG_account9_title) {
            res = "Switch to Professor Account";
        } else if (id == MSG_account9_subtitle) {
            res = "Bank Data";
        } else if (id == MSG_account9_btnmsg) {
            res = "Switch";
        } else if (id == MSG_account9_msg1) {
            res = "Your acount could not be updated";
        } else if (id == MSG_account9_msg2) {
            res = "You have changed your profile from Student to Professor";
        } 

        else if (id == MSG_account10_title) {
            res = "Account Delete";
        } else if (id == MSG_account10_deleteAccountWarningMsg) {
            res = "If you delete your account you " +
                    "will lose all the related data. Are you sure you want to delete all your info?";
        } else if (id == MSG_account10_deleteBtn) {
            res = "Delete";
        } else if (id == MSG_account10_cancelBtn) {
            res = "Cancel";
        }
        
        else if (id == MSG_account11_msg) {
            res = "Your user could not be deleted";
        } 
        
        else if (id == MSG_course1_title) {
            res = "My Courses";
        } else if (id == MSG_course1_uploadLink) {
            res = "Upload Course";
        } else if (id == MSG_course1_noCourseMsg) {
            res = "There is no course yet!";
        } 
        
        else if (id == MSG_course2_title) {
            res = "Course not foud!";
        } else if (id == MSG_course2_WatchBtn) {
            res = "Watch";
        } else if (id == MSG_course2_UpdateBtn) {
            res = "Update";
        } else if (id == MSG_course2_DeleteBtn) {
            res = "Delete";
        } else if (id == MSG_course2_courseTitleLbl) {
            res = "Course Title: ";
        } else if (id == MSG_course2_DescriptionLbl) {
            res = "Description: ";
        } else if (id == MSG_course2_PriceLbl) {
            res = "Price: ";
        } else if (id == MSG_course2_coinLbl) {
            res = "Price coin: ";
        } else if (id == MSG_course2_publicLbl) {
            res = "Is public: ";
        } else if (id == MSG_course2_VideoListLbl) {
            res = "Video List";
        } 

        else if (id == MSG_course3_title) {
            res = "Delete Course";
        } else if (id == MSG_course3_msg) {
            res = "Are you sure you want to delete this course?";
        } else if (id == MSG_course3_deleteBtn) {
            res = "delete";
        } else if (id == MSG_course3_cancelBtn) {
            res = "cancel";
        } 
        
        else if (id == MSG_course6_title) {
            res = "Course not foud!";
        } else if (id == MSG_course6_sidebartitle) {
            res = "Videos";
        } else if (id == MSG_course6_sidebarNoVideo) {
            res = "No video disponible";
        } else if (id == MSG_course6_noVideo) {
            res = "There is no video yet!";
        } 

        else if (id == MSG_course7_title) {
            res = "Upload Course";
        } else if (id == MSG_course7_nameLbl) {
            res = "Title";
        } else if (id == MSG_course7_descriptionLbl) {
            res = "Description";
        } else if (id == MSG_course7_priceLbl) {
            res = "Price";
        } else if (id == MSG_course7_coinLbl) {
            res = "Coin";
        } else if (id == MSG_course7_isPublicLbl) {
            res = "Visibility";
        } else if (id == MSG_course7_nameErrorMsg) {
            res = "Invalid name";
        } else if (id == MSG_course7_descriptionErrorMsg) {
            res = "Invalid description";
        } else if (id == MSG_course7_priceErrorMsg) {
            res = "Invalid price";
        } else if (id == MSG_course7_coinErrorMsg) {
            res = "Invalid coin";
        } else if (id == MSG_course7_isPublicErrorMsg) {
            res = "Invalid visibility";
        } else if (id == MSG_course7_btnNextMsg) {
            res = "Next";
        } 
        
        else if (id == MSG_course8_title) {
            res = "Upload Course";
        } else if (id == MSG_course8_nameLbl) {
            res = "Title";
        } else if (id == MSG_course8_descriptionLbl) {
            res = "Description";
        } else if (id == MSG_course8_priceLbl) {
            res = "Price";
        } else if (id == MSG_course8_coinLbl) {
            res = "Coin";
        } else if (id == MSG_course8_isPublicLbl) {
            res = "Visibility";
        } else if (id == MSG_course8_nameErrorMsg) {
            res = "Invalid name";
        } else if (id == MSG_course8_descriptionErrorMsg) {
            res = "Invalid description";
        } else if (id == MSG_course8_priceErrorMsg) {
            res = "Invalid price";
        } else if (id == MSG_course8_coinErrorMsg) {
            res = "Invalid coin";
        } else if (id == MSG_course8_isPublicErrorMsg) {
            res = "Invalid visibility";
        } else if (id == MSG_course8_btnNextMsg) {
            res = "Next";
        }
        
        else if (id == MSG_course9_title) {
            res = "Update Course: ";
        } else if (id == MSG_course9_nameLbl) {
            res = "Title";
        } else if (id == MSG_course9_descriptionLbl) {
            res = "Description";
        } else if (id == MSG_course9_priceLbl) {
            res = "Price";
        } else if (id == MSG_course9_coinLbl) {
            res = "Coin";
        } else if (id == MSG_course9_isPublicLbl) {
            res = "Visibility";
        } else if (id == MSG_course9_nameErrorMsg) {
            res = "Invalid name";
        } else if (id == MSG_course9_descriptionErrorMsg) {
            res = "Invalid description";
        } else if (id == MSG_course9_priceErrorMsg) {
            res = "Invalid price";
        } else if (id == MSG_course9_coinErrorMsg) {
            res = "Invalid coin";
        } else if (id == MSG_course9_isPublicErrorMsg) {
            res = "Invalid visibility";
        } else if (id == MSG_course9_btnNextMsg) {
            res = "Next";
        } 

        else if (id == MSG_course11_title) {
            res = "Upload Course - Manage Videos";
        } 

        else if (id == MSG_course12_title) {
            res = "Upload Course - Manage Videos";
        } else if (id == MSG_course12_btnAddNewMsg) {
            res = "Add New Video";
        } else if (id == MSG_course12_btnNextMsg) {
            res = "Next";
        } else if (id == MSG_course12_videonameLbl) {
            res = "Video Name";
        } else if (id == MSG_course12_addBtn) {
            res = "Add New Video";
        } else if (id == MSG_course12_nextBtn) {
            res = "Next";
        } 

        else if (id == MSG_course13_title) {
            res = "Upload Video";
        } else if (id == MSG_course13_btnUploadMsg) {
            res = "Upload Video";
        } else if (id == MSG_course13_VideotitleLbl) {
            res = "Video title: ";
        } else if (id == MSG_course13_orderWarning) {
            res = "Video list will be sorted by the title";
        } 
        
        else if (id == MSG_course14_title) {
            res = "Upload Video";
        } else if (id == MSG_course14_msg1) {
            res = "You have to choose a video to upload";
        } else if (id == MSG_course14_titleErrorMsg) {
            res = "Invalid Title";
        } else if (id == MSG_course14_msg2) {
            res = "Uploaded the video successfully: ";
        } else if (id == MSG_course14_msg3) {
            res = "Could not upload the video: ";
        } else if (id == MSG_course14_btnUploadMsg) {
            res = "Upload video";
        } else if (id == MSG_course14_VideotitleLbl) {
            res = "Video title: ";
        } else if (id == MSG_course14_orderWarning) {
            res = "Video list will be sorted by the title";
        }

        else if (id == MSG_course15_title) {
            res = "Course - Sumary";
        } else if (id == MSG_course15_courseTitleLbl) {
            res = "Course Title: ";
        } else if (id == MSG_course15_DescriptionLbl) {
            res = "Description: ";
        } else if (id == MSG_course15_PriceLbl) {
            res = "Price: ";
        } else if (id == MSG_course15_coinLbl) {
            res = "Price coin: ";
        } else if (id == MSG_course15_publicLbl) {
            res = "Is public: ";
        } else if (id == MSG_course15_VideoListLbl) {
            res = "Video List";
        } else if (id == MSG_course15_btnNextMsg) {
            res = "Confirm course";
        } 
        
        else if (id == MSG_course16_title) {
            res = "Course - Confirmation";
        } else if (id == MSG_course16_msg) {
            res = "You will see your changes in My Courses page";
        } 
        
        else if (id == MSG_hall1_title) {
            res = "Log In";
        } else if (id == MSG_hall1_subtitle) {
            res = "Log In | AcadeMice";
        } else if (id == MSG_hall1_description) {
            res = "The best platform for online courses for people passionate about education";
        } else if (id == MSG_hall1_errorMsg) {
            res = "Invalid username and password. Try again";
        } else if (id == MSG_hall1_userLbl) {
            res = "User Name: ";
        } else if (id == MSG_hall1_pwLbl) {
            res = "PassWord: ";
        } else if (id == MSG_hall1_submitBtn) {
            res = "Log In";
        } 
        
        else if (id == MSG_hall3_title) {
            res = "Sign Up" ;
        } else if (id == MSG_hall3_subtitle) {
            res = "Sing Up | AcadeMice";
        } else if (id == MSG_hall3_description) {
            res = "The best platform for online courses for people passionate about education";
        } else if (id == MSG_hall3_langLbl) {
            res = "Language:";
        } else if (id == MSG_hall3_Userlbl) {
            res = "User Name: ";
        } else if (id == MSG_hall3_Emaillbl) {
            res = "Email: ";
        } else if (id == MSG_hall3_PassWordlbl) {
            res = "PassWord: ";
        } else if (id == MSG_hall3_Matchinglbl) {
            res = "Matching PassWord: ";
        } else if (id == MSG_hall3_submitbtn) {
            res = "Sign Up";
        } 
        
        else if (id == MSG_hall4_usernameInUseErrorMsg) {
            res = "ERROR!: Username already in use";
        } else if (id == MSG_hall4_errorEmailMsg) {
            res = "ERROR!: Invalid mail";
        } 
        
        else if (id == MSG_hall6_title) {
            res = "About" ;
        } else if (id == MSG_hall6_subtitle) {
            res = "About | AcadeMice";
        } else if (id == MSG_hall6_description) {
            res = "The best platform for online courses for people passionate about education";
        } else if (id == MSG_hall6_msg1) {
            res = "AcadeMice is an online learning platform oriented towards professional adults. We offer courses created by online creators of this same platform. We provide tools to create, publish, and sell courses.";
        } else if (id == MSG_hall6_msg2) {
            res = "None of the courses from AcadeMice are equivalent to official studies. Students use the courses simply to improve their skills. At AcadeMice, you can study anything: sports, art, computer science, science... We are open to all kinds of knowledge.";
        } else if (id == MSG_hall6_msg3) {
            res = "Teachers and students can interact through the Q&A and comments sections of the courses. AcadeMice offers paid and free courses, depending on the teacher's decision.";
        } else if (id == MSG_hall6_msg4) {
            res = "This platform does not support the dissemination of harmful or false information or piracy.";
        } else if (id == MSG_hall6_startBtn) {
            res = "Start";
        } 
        
        else if (id == MSG_help_title) {
            res = "Help";
        } else if (id == MSG_help_noCourse) {
            res = "There is no course yet!";
        } 

        else if (id == MSG_home1_title) {
            res = "My Bought Courses";
        } else if (id == MSG_home1_noCourseMsg) {
            res = "There is no course yet!";
        } else if (id == MSG_home1_searchLbl) {
            res = "Search course";
        } else if (id == MSG_home1_searchBtn) {
            res = "Search";
        } 

        else if (id == MSG_home2_title) {
            res = "Search results for: ";
        } else if (id == MSG_home2_noCourseMsg) {
            res = "There is no course yet!";
        } else if (id == MSG_home2_searchLbl) {
            res = "Search course";
        } else if (id == MSG_home2_searchBtn) {
            res = "Search";
        } 

        else if (id == MSG_home3_title1) {
            res = "Buy course" ;
        } else if (id == MSG_home3_title2) {
            res = "Buy course: ";
        } else if (id == MSG_home3_msg) {
            res = "Are you sure you want to delete this course?";
        } else if (id == MSG_home3_btnbuyMsg1) {
            res = "buy";
        } else if (id == MSG_home3_btnbuyMsg2) {
            res = "Already Bought";
        } else if (id == MSG_home3_courseTitleLbl) {
            res = "Course Title: ";
        } else if (id == MSG_home3_DescriptionLbl) {
            res = "Description: ";
        } else if (id == MSG_home3_PriceLbl) {
            res = "Price: ";
        } else if (id == MSG_home3_coinLbl) {
            res = "Price coin: ";
        } else if (id == MSG_home3_publicLbl) {
            res = "Is public: ";
        } else if (id == MSG_home3_videoListLbl) {
            res = "Video List";
        } 
        
        else if (id == MSG_home4_title1) {
            res = "Buy course";
        } else if (id == MSG_home4_title2) {
            res = "Buy course: ";
        } else if (id == MSG_home4_btnbuyMsg) {
            res = "buy";
        } else if (id == MSG_home4_subtitle) {
            res = "Payment";
        } else if (id == MSG_home4_aceptedCardsLbl) {
            res = "Accepted Cards";
        } else if (id == MSG_home4_totalLbl) {
            res = "Total";
        } else if (id == MSG_home4_currencyLbl) {
            res = "Currency";
        } else if (id == MSG_home4_methodLbl) {
            res = "Payment Method";
        } else if (id == MSG_home4_intentLbl) {
            res = "Intent";
        } else if (id == MSG_home4_descriptionLbl) {
            res = "Payment Description";
        }
        
        else if (id == MSG_image1_title1) {
            res = "Upload Image";
        } else if (id == MSG_image1_btnUploadMsg) {
            res = "Upload Image";
        } else if (id == MSG_image1_btnNextMsg) {
            res = "Next";
        } 

        else if (id == MSG_image2_title) {
            res = "Upload Image";
        } else if (id == MSG_image2_msg1) {
            res = "You have to choose an image to upload";
        } else if (id == MSG_image2_msg2) {
            res = "Uploaded the image successfully: ";
        } else if (id == MSG_image2_msg3) {
            res = "Could not upload the image: " ;
        } else if (id == MSG_image2_btnUploadMsg) {
            res = "Upload Image";
        } else if (id == MSG_image2_btnNextMsg) {
            res = "Next";
        } 
        
        else if (id == MSG_video1_title1) {
            res = "Watch Video";
        }
        
        else if (id == MSG_) {
            res = "ERROR_MSG";
        }

        return res;
    }

    public String getSpanishString(int id){
        String res = "Error getting text";
        if (id == MSG_HALL_INDEX_TITLE) {
            res = "AcadeMice";
        } else if (id == MSG_HALL_INDEX_SUBTITLE) {
            res = "La mejor plataforma de aprendizaje";
        } else if (id == MSG_HALL_INDEX_DESCRIPTION) {
            res = "La mejor plataforma de cursos online para gente apasionada por la educación";
        } else if (id == MSG_HALL_INDEX_START_BTN) {
            res = "Comenzar";
        } 
        
        else if (id == MSG_NAV_LOG_IN) {
            res = "Iniciar Sesion";
        } else if (id == MSG_NAV_SIGN_UP) {
            res = "Registro";
        } else if (id == MSG_NAV_ABOUT) {
            res = "Acerca De";
        } else if (id == MSG_NAV_HOME) {
            res = "Inicio";
        } else if (id == MSG_NAV_ACCOUNT) {
            res = "Mi Cuenta";
        } else if (id == MSG_NAV_HELP) {
            res = "Ayuda";
        } else if (id == MSG_NAV_COURSES) {
            res = "Mis Cursos";
        }

        else if (id == MSG_account1_title) {
            res = "Mi Cuenta";
        } else if (id == MSG_account1_sidebarEdit) {
            res = "Editar Cuenta";
        } else if (id == MSG_account1_sidebarPw) {
            res = "Cambiar contraseña";
        } else if (id == MSG_account1_sidebarDelete) {
            res = "Borrar Cuenta";
        } else if (id == MSG_account1_sidebarLogOut) {
            res = "Cerrar Sesion";
        } else if (id == MSG_account1_sidebarSwitch) {
            res = "Cambiar permanentemente a profesor";
        } else if (id == MSG_account1_sidebarBank) {
            res = "Actualizar datos bancarios";
        } else if (id == MSG_account1_isProfessorMsg) {
            res = "Cuenta de Profesor";
        } else if (id == MSG_account1_uploadCurseMsg) {
            res = "Subir curso";
        }

         else if (id == MSG_account2_title) {
            res = "Editar cuenta";
        } else if (id == MSG_account2_langLbl) {
            res = "Idioma:";
        } else if (id == MSG_account2_avatarLbl) {
            res = "Avatar : ";
        } else if (id == MSG_account2_emailLbl) {
            res = "Email: ";
        } else if (id == MSG_account2_editBtn) {
            res = "Editar";
        } 
        
        else if (id == MSG_account3_title) {
            res =  "Editar cuenta";
        } else if (id == MSG_account3_msg1) {
            res = "No se pudo actualizar la cuenta";
        } else if (id == MSG_account3_msg2) {
            res = ". Tu nuevo email no era valido";
        } else if (id == MSG_account3_msg3) {
            res = "No se pudo subir la imagen: ";
        } else if (id == MSG_account3_msg4) {
            res = "Su cuenta se actualizó correctamente";
        }
        
        else if (id == MSG_account4_title) {
            res = "Cambiar contraseña";
        } else if (id == MSG_account4_oldPwLbl) {
            res = "Antigua contraseña: ";
        } else if (id == MSG_account4_newPwLbl) {
            res = "Nueva contraseña: ";
        } else if (id == MSG_account4_mtchPwLbl) {
            res = "Repetir contraseña: ";
        } else if (id == MSG_account4_submitBtn) {
            res = "Cambiar contraseña";
        } 
        
        else if (id == MSG_account5_title) {
            res = "Cambiar contraseña";
        } else if (id == MSG_account5_msg1) {
            res = "No se pudo actualizar contraseña";
        } else if (id == MSG_account5_msg2) {
            res = "Contraseña actualizada";
        } else if (id == MSG_account5_msg3) {
            res = ", las contraseñas no coinciden";
        }
        
         else if (id == MSG_account6_title) {
            res = "Actualizar cuenta de profesor";
        } else if (id == MSG_account6_subtitle) {
            res = "Datos bancarios";
        } else if (id == MSG_account6_btnmsg) {
            res = "Actualizar";
        } else if (id == MSG_account6_titularLbl) {
            res = "Titular: ";
        } else if (id == MSG_account6_IBANLbl) {
            res = "IBAN: ";
        } 
        
        else if (id == MSG_account7_title) {
            res = "Actualizar cuenta de profesor";
        } else if (id == MSG_account7_subtitle) {
            res = "Datos bancarios";
        } else if (id == MSG_account7_btnmsg) {
            res = "Actualizar";
        } else if (id == MSG_account7_msg1) {
            res = "Tus datos no se actualizaron";
        } else if (id == MSG_account7_msg2) {
            res = "Datos bancarios actualizados";
        } 

        else if (id == MSG_account8_title) {
            res = "Actualizar cuenta de profesor";
        } else if (id == MSG_account8_subtitle) {
            res = "Datos bancarios";
        } else if (id == MSG_account8_btnmsg) {
            res = "Cambiar";
        } else if (id == MSG_account8_titularLbl) {
            res = "Titular: ";
        } else if (id == MSG_account8_IBANLbl) {
            res = "IBAN: ";
        } 

        else if (id == MSG_account9_title) {
            res = "Cambiar a cuenta de profesor";
        } else if (id == MSG_account9_subtitle) {
            res = "Datos bancarios";
        } else if (id == MSG_account9_btnmsg) {
            res = "Cambiar";
        } else if (id == MSG_account9_msg1) {
            res = "Tus datos no se actualizaron";
        } else if (id == MSG_account9_msg2) {
            res = "As cambiado a cuenta de profesor";
        } 

        else if (id == MSG_account10_title) {
            res = "Borrar cuenta";
        } else if (id == MSG_account10_deleteAccountWarningMsg) {
            res = "Si borras tu cuenta borraras todos los datos asociados. ¿Estas seguro?";
        } else if (id == MSG_account10_deleteBtn) {
            res = "Borrar";
        } else if (id == MSG_account10_cancelBtn) {
            res = "Cancelar";
        }
        
        else if (id == MSG_account11_msg) {
            res = "Tu usuario no pudo ser borrado";
        } 
        
        else if (id == MSG_course1_title) {
            res = "Mis cursos";
        } else if (id == MSG_course1_uploadLink) {
            res = "Subir curso";
        } else if (id == MSG_course1_noCourseMsg) {
            res = "No hay cursos todavía!";
        } 
        
        else if (id == MSG_course2_title) {
            res = "Curso no encontrado!";
        } else if (id == MSG_course2_WatchBtn) {
            res = "Ver";
        } else if (id == MSG_course2_UpdateBtn) {
            res = "Actualizar";
        } else if (id == MSG_course2_DeleteBtn) {
            res = "Borrar";
        } else if (id == MSG_course2_courseTitleLbl) {
            res = "Titulo";
        } else if (id == MSG_course2_DescriptionLbl) {
            res = "Descripcion: ";
        } else if (id == MSG_course2_PriceLbl) {
            res = "Precio: ";
        } else if (id == MSG_course2_coinLbl) {
            res = "Moneda: ";
        } else if (id == MSG_course2_publicLbl) {
            res = "Visibilidad: ";
        } else if (id == MSG_course2_VideoListLbl) {
            res = "Lista de videos";
        } 

        else if (id == MSG_course3_title) {
            res = "Borrar curso";
        } else if (id == MSG_course3_msg) {
            res = "¿Estás seguro?";
        } else if (id == MSG_course3_deleteBtn) {
            res = "Borrar";
        } else if (id == MSG_course3_cancelBtn) {
            res = "Cancelar";
        } 
        
        else if (id == MSG_course6_title) {
            res = "Curso no encontrado!";
        } else if (id == MSG_course6_sidebartitle) {
            res = "Videos";
        } else if (id == MSG_course6_sidebarNoVideo) {
            res = "No hay videos";
        } else if (id == MSG_course6_noVideo) {
            res = "No hay videos!";
        } 

        else if (id == MSG_course7_title) {
            res = "Subir curso";
        } else if (id == MSG_course7_nameLbl) {
            res = "Titulo";
        } else if (id == MSG_course7_descriptionLbl) {
            res = "Descripcion";
        } else if (id == MSG_course7_priceLbl) {
            res = "Precio";
        } else if (id == MSG_course7_coinLbl) {
            res = "Moneda";
        } else if (id == MSG_course7_isPublicLbl) {
            res = "Visibilidad";
        } else if (id == MSG_course7_nameErrorMsg) {
            res = "Nombre Invalido";
        } else if (id == MSG_course7_descriptionErrorMsg) {
            res = "Descripcion Invalida";
        } else if (id == MSG_course7_priceErrorMsg) {
            res = "Precio Invalido";
        } else if (id == MSG_course7_coinErrorMsg) {
            res = "Moneda Invalida";
        } else if (id == MSG_course7_isPublicErrorMsg) {
            res = "Visibilidad Invalida";
        } else if (id == MSG_course7_btnNextMsg) {
            res = "Siguiente";
        } 
        
        else if (id == MSG_course8_title) {
            res = "Subir curso";
        } else if (id == MSG_course8_nameLbl) {
            res = "Titulo";
        } else if (id == MSG_course8_descriptionLbl) {
            res = "Descripcion";
        } else if (id == MSG_course8_priceLbl) {
            res = "Precio";
        } else if (id == MSG_course8_coinLbl) {
            res = "Moneda";
        } else if (id == MSG_course8_isPublicLbl) {
            res = "Visibilidad";
        } else if (id == MSG_course8_nameErrorMsg) {
            res = "Nombre Invalido";
        } else if (id == MSG_course8_descriptionErrorMsg) {
            res = "Descripcion Invalida";
        } else if (id == MSG_course8_priceErrorMsg) {
            res = "Precio Invalido";
        } else if (id == MSG_course8_coinErrorMsg) {
            res = "Moneda Invalida";
        } else if (id == MSG_course8_isPublicErrorMsg) {
            res = "Visibilidad Invalida";
        } else if (id == MSG_course8_btnNextMsg) {
            res = "Siguiente";
        }
        
        else if (id == MSG_course9_title) {
            res = "Actualizar curso: ";
        } else if (id == MSG_course9_nameLbl) {
            res = "Titulo";
        } else if (id == MSG_course9_descriptionLbl) {
            res = "Descripcion";
        } else if (id == MSG_course9_priceLbl) {
            res = "Precio";
        } else if (id == MSG_course9_coinLbl) {
            res = "Moneda";
        } else if (id == MSG_course9_isPublicLbl) {
            res = "Visibilidad";
        } else if (id == MSG_course9_nameErrorMsg) {
            res =  "Nombre Invalido";
        } else if (id == MSG_course9_descriptionErrorMsg) {
            res = "Descripcion Invalida";
        } else if (id == MSG_course9_priceErrorMsg) {
            res = "Precio Invalido";
        } else if (id == MSG_course9_coinErrorMsg) {
            res = "Moneda Invalida";
        } else if (id == MSG_course9_isPublicErrorMsg) {
            res = "Visibilidad Invalida";
        } else if (id == MSG_course9_btnNextMsg) {
            res = "Siguiente";
        } 

        else if (id == MSG_course11_title) {
            res = "Subir curso - Administrar Videos";
        } 

        else if (id == MSG_course12_title) {
            res = "Subir curso - Administrar Videos";
        } else if (id == MSG_course12_btnAddNewMsg) {
            res = "Añadir Video";
        } else if (id == MSG_course12_btnNextMsg) {
            res = "Siguiente";
        } else if (id == MSG_course12_videonameLbl) {
            res = "Nombre de video";
        } else if (id == MSG_course12_addBtn) {
            res = "Añadir Video";
        } else if (id == MSG_course12_nextBtn) {
            res = "Siguiente";
        } 

        else if (id == MSG_course13_title) {
            res = "Subir Video";
        } else if (id == MSG_course13_btnUploadMsg) {
            res = "Subir Video";
        } else if (id == MSG_course13_VideotitleLbl) {
            res = "Titulo: ";
        } else if (id == MSG_course13_orderWarning) {
            res = "Los videos se ordenaran por el titulo";
        } 
        
        else if (id == MSG_course14_title) {
            res = "Subir Video";
        } else if (id == MSG_course14_msg1) {
            res = "Tienes que elegir un video para subirlo";
        } else if (id == MSG_course14_titleErrorMsg) {
            res = "Titulo invalido";
        } else if (id == MSG_course14_msg2) {
            res = "Video correctamente subido: ";
        } else if (id == MSG_course14_msg3) {
            res = "No se pudo subir el video: ";
        } else if (id == MSG_course14_btnUploadMsg) {
            res = "Subir video";
        } else if (id == MSG_course14_VideotitleLbl) {
            res = "Titulo: ";
        } else if (id == MSG_course14_orderWarning) {
            res = "Los videos se ordenaran por el titulo";
        }

        else if (id == MSG_course15_title) {
            res = "Curso - Resumen";
        } else if (id == MSG_course15_courseTitleLbl) {
            res = "Titulo: ";
        } else if (id == MSG_course15_DescriptionLbl) {
            res = "Descripcion: ";
        } else if (id == MSG_course15_PriceLbl) {
            res = "Precio: ";
        } else if (id == MSG_course15_coinLbl) {
            res = "Moneda: ";
        } else if (id == MSG_course15_publicLbl) {
            res = "Es publico: ";
        } else if (id == MSG_course15_VideoListLbl) {
            res = "Lista de videos";
        } else if (id == MSG_course15_btnNextMsg) {
            res = "Confirmar";
        } 
        
        else if (id == MSG_course16_title) {
            res = "Curso - Confirmacion";
        } else if (id == MSG_course16_msg) {
            res = "Veras tus cambios en la pagina Mis cursos";
        } 
        
        else if (id == MSG_hall1_title) {
            res = "Iniciar sesion";
        } else if (id == MSG_hall1_subtitle) {
            res = "Iniciar sesion | AcadeMice";
        } else if (id == MSG_hall1_description) {
            res = "La mejor plataforma de cursos online";
        } else if (id == MSG_hall1_errorMsg) {
            res = "Datos invalidos. Intentalo de nuevo";
        } else if (id == MSG_hall1_userLbl) {
            res = "Usuario: ";
        } else if (id == MSG_hall1_pwLbl) {
            res = "Contraseña: ";
        } else if (id == MSG_hall1_submitBtn) {
            res = "Iniciar sesion";
        } 
        
        else if (id == MSG_hall3_title) {
            res = "Registro" ;
        } else if (id == MSG_hall3_subtitle) {
            res = "Registro | AcadeMice";
        } else if (id == MSG_hall3_description) {
            res = "La mejor plataforma de cursos online";
        } else if (id == MSG_hall3_langLbl) {
            res = "Idioma:";
        } else if (id == MSG_hall3_Userlbl) {
            res = "Usuario: ";
        } else if (id == MSG_hall3_Emaillbl) {
            res = "Email: ";
        } else if (id == MSG_hall3_PassWordlbl) {
            res = "Contraseña: ";
        } else if (id == MSG_hall3_Matchinglbl) {
            res = "Repetir Contraseña: ";
        } else if (id == MSG_hall3_submitbtn) {
            res = "Registro";
        } 
        
        else if (id == MSG_hall4_usernameInUseErrorMsg) {
            res = "ERROR!: El usuario ya existe";
        } else if (id == MSG_hall4_errorEmailMsg) {
            res = "ERROR!: mail invalido";
        } 
        
        else if (id == MSG_hall6_title) {
            res = "Acerca de " ;
        } else if (id == MSG_hall6_subtitle) {
            res = "Acerca de  | AcadeMice";
        } else if (id == MSG_hall6_description) {
            res ="La mejor plataforma de cursos online para gente apasionada por la educacion";
        } else if (id == MSG_hall6_msg1) {
            res = "AcadeMice es una plataforma online de aprendizaje orientada a adultos. Ofrecemos cursos creados por creadores de esta misma plataforma. Proveemos de erramientas para la creacion, publicacion y venta de cursos.";
        } else if (id == MSG_hall6_msg2) {
            res = "Ninguno de los cursos de AcadeMice es equivalente a unos estudios reglados.Cuando los estudiantes acceden a un curso, lo que realmente buscan es mejorar sus habilidades. En AcadeMice, puedes estudiar cualquier cosa: deporte, arte, IT, ciencias... Estamos interesados en cualquier tipo de conocimiento.";
        } else if (id == MSG_hall6_msg3) {
            res = "AcadeMice ofrece cursos de pago y gratuitos, dependiendo de la decision del profesor";
        } else if (id == MSG_hall6_msg4) {
            res = "Esta plataforma no acepta la divulgacion de contenido dañino, ilegal o informacion falsa.";
        } else if (id == MSG_hall6_startBtn) {
            res = "Comenzar";
        } 
        
        else if (id == MSG_help_title) {
            res = "Ayuda";
        } else if (id == MSG_help_noCourse) {
            res = "No hay cursos aun!";
        } 

        else if (id == MSG_home1_title) {
            res = "Mis cursos comprados";
        } else if (id == MSG_home1_noCourseMsg) {
            res = "No hay cursos aun!";
        } else if (id == MSG_home1_searchLbl) {
            res = "Buscar curso";
        } else if (id == MSG_home1_searchBtn) {
            res = "Buscar";
        } 

        else if (id == MSG_home2_title) {
            res = "Resultados de busqueda para: ";
        } else if (id == MSG_home2_noCourseMsg) {
            res = "No hay cursos aun!";
        } else if (id == MSG_home2_searchLbl) {
            res = "Buscar curso";
        } else if (id == MSG_home2_searchBtn) {
            res = "Buscar";
        } 

        else if (id == MSG_home3_title1) {
            res = "Buy course" ;
        } else if (id == MSG_home3_title2) {
            res = "Buy course: ";
        } else if (id == MSG_home3_msg) {
            res = "Estas seguro de que quieres borrar este curso?";
        } else if (id == MSG_home3_btnbuyMsg1) {
            res = "Comprar";
        } else if (id == MSG_home3_btnbuyMsg2) {
            res = "Ya lo has comprado";
        } else if (id == MSG_home3_courseTitleLbl) {
            res = "Titulo: ";
        } else if (id == MSG_home3_DescriptionLbl) {
            res = "Descripcion: ";
        } else if (id == MSG_home3_PriceLbl) {
            res = "Precio: ";
        } else if (id == MSG_home3_coinLbl) {
            res = "Moneda: ";
        } else if (id == MSG_home3_publicLbl) {
            res = "Es publico: ";
        } else if (id == MSG_home3_videoListLbl) {
            res = "Lista de videos";
        } 
        
        else if (id == MSG_home4_title1) {
            res = "Comprar curso";
        } else if (id == MSG_home4_title2) {
            res = "Comprar curso: ";
        } else if (id == MSG_home4_btnbuyMsg) {
            res = "Comprar";
        } else if (id == MSG_home4_subtitle) {
            res = "Pago";
        } else if (id == MSG_home4_aceptedCardsLbl) {
            res = "Targetas aceptadas";
        } else if (id == MSG_home4_totalLbl) {
            res = "Total";
        } else if (id == MSG_home4_currencyLbl) {
            res = "Currency";
        } else if (id == MSG_home4_methodLbl) {
            res = "Metodo de pago";
        } else if (id == MSG_home4_intentLbl) {
            res = "Intent";
        } else if (id == MSG_home4_descriptionLbl) {
            res = "Descripcion";
        }
        
        else if (id == MSG_image1_title1) {
            res = "Subir Imagen";
        } else if (id == MSG_image1_btnUploadMsg) {
            res = "Subir Imagen";
        } else if (id == MSG_image1_btnNextMsg) {
            res = "Siguiente";
        } 

        else if (id == MSG_image2_title) {
            res = "Subir Imagen";
        } else if (id == MSG_image2_msg1) {
            res = "Debe elegir una imagen";
        } else if (id == MSG_image2_msg2) {
            res = "Imagen correctamente subida: ";
        } else if (id == MSG_image2_msg3) {
            res = "No se pudo subir la imagen: " ;
        } else if (id == MSG_image2_btnUploadMsg) {
            res = "Subir Imagen";
        } else if (id == MSG_image2_btnNextMsg) {
            res = "Siguiente";
        } 
        
        else if (id == MSG_video1_title1) {
            res = "Ver Video";
        }
        
        else if (id == MSG_) {
            res = "ERROR_MSG";
        }
        return res;
    }

    public static int getId(){
        id+=1;
        return id;
    }
}
