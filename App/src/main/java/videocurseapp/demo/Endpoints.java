package videocurseapp.demo;

public class Endpoints {
    //USER ENDPOINTS
    public static final String EP_USER_ROOT = "/user";
    public static final String EP_USER_GET_ID = "/get/{name}";
    public static final String EP_USER_GET_ALL = "/get/all";
    public static final String EP_USER_POST = "/post";
    public static final String EP_USER_PUT = "/put/{name}";
    public static final String EP_USER_DELETE = "/delete/{name}";

    //IMAGE ENDPOINTS
    public static final String EP_IMG_ROOT = "/img";
    public static final String EP_IMG_GET_ALL="/get/all";
    public static final String EP_IMG_GET_ID="/get/{id}";
    public static final String EP_IMG_POST= "/post";
    public static final String EP_IMG_PUT = "/put/{id}";
    public static final String EP_IMG_DELETE = "/delete/{id}";
}
