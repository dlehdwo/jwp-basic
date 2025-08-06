package core.mvc.view;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ModelAndView {
    private View view;
    private final Map<String, Object> model = new HashMap<>();

    public ModelAndView(View view) {
        this.view = view;
    }

    public View getView() {
        return view;
    }

    public Map<String, Object> getModel() {
        return Collections.unmodifiableMap(model);
    }

    public ModelAndView addObject(String key, Object value) {
        model.put(key, value);
        return this;
    }
}
