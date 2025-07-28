package next.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(RequestMapping.class);
    private final Map<String, Controller> controllers = new HashMap<String, Controller>();

    public void initMapping() {
        controllers.put("/", new HomeController());
        controllers.put("/users/form", new ForwardController("/user/form.jsp"));
        controllers.put("/users/loginForm", new ForwardController("/user/login.jsp"));
        controllers.put("/users", new ListUserController());
        controllers.put("/users/login", new LoginController());
        controllers.put("/users/profile", new ProfileController());
        controllers.put("/users/logout", new LogoutController());
        controllers.put("/users/create", new CreateUserController());
        controllers.put("/users/update", new UpdateUserController());
        controllers.put("/users/updateForm", new UpdateFormUserController());
    }

    public Controller getController(String name){
        Controller controller = controllers.get(name);
        logger.debug("chosen controller: {}", controller);
        return controller;
    }

    void put(String url, Controller controller){
        controllers.put(url, controller);
    }
}
