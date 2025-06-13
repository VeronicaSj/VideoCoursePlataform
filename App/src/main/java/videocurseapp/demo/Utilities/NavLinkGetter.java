package videocurseapp.demo.Utilities;

import java.util.ArrayList;

import videocurseapp.demo.Controller.ControllerStaticParent;

public class NavLinkGetter {
    
    ControllerStaticParent parent = new ControllerStaticParent();

    public ArrayList<NavLink> getHallNavLinkList(){
        ArrayList<NavLink> res = new ArrayList<NavLink>();
        res.add(new NavLink("/", ""));

        res.add(new NavLink("/login", parent.getString(parent.MSG_NAV_LOG_IN)));
        res.add(new NavLink("/signup",parent.getString(parent.MSG_NAV_SIGN_UP)));
        res.add(new NavLink("/about",parent.getString(parent.MSG_NAV_ABOUT)));
        
        return res;
    }

    public ArrayList<NavLink> getInAppNavLinkList(){
        ArrayList<NavLink> res = new ArrayList<NavLink>();
        res.add(new NavLink("/home", parent.getString(parent.MSG_NAV_HOME)));
        res.add(new NavLink("/account", parent.getString(parent.MSG_NAV_ACCOUNT)));
        res.add(new NavLink("/help", parent.getString(parent.MSG_NAV_HELP)));
        
        return res;
    }
}
