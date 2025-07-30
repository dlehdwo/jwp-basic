package core.mvc.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import next.model.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class JsonView implements View{

    @Override
    public void render(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Map<String, Object> model = createModel(req);
        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(mapper.writeValueAsString(model));
    }

    private Map<String, Object> createModel(HttpServletRequest req) {
        Enumeration<String> attributeNames = req.getAttributeNames();
        Map<String, Object> model = new HashMap<>();
        while(attributeNames.hasMoreElements()){
            String name = attributeNames.nextElement();
            model.put(name, req.getAttribute(name));
        }
        return model;
    }
}
