package core.mvc.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class JsonView implements View{
    private static final Logger log = LoggerFactory.getLogger(JsonView.class);

    @Override
    public void render(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Map<String, Object> model = createModel(req);
        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(mapper.writeValueAsString(model));
        log.debug("Model: {}", mapper.writeValueAsString(model));
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
