package videocurseapp.demo.Utilities;

import java.util.ArrayList;

public class NavLinkGetter {
    public ArrayList<NavLink> getHallNavLinkList(){
        ArrayList<NavLink> res = new ArrayList<NavLink>();
        res.add(new NavLink("/", ""));

        res.add(new NavLink("/login", "LogIn"));
        res.add(new NavLink("/signup","SignUp"));
        res.add(new NavLink("/about","About"));
        
        return res;
    }

    public ArrayList<NavLink> getInAppNavLinkList(){
        ArrayList<NavLink> res = new ArrayList<NavLink>();
        res.add(new NavLink("/home", "Home"));
        res.add(new NavLink("/account", "My Acount"));
        res.add(new NavLink("/help", "Help"));
        
        return res;
    }
}
