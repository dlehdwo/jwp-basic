package core.mvc.view;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class JspView implements View {
    private String url;
    private static final String DEFAULT_REDIRECT_PREFIX = "redirect:";

    public JspView(String url) {
        this.url = url;
    }

    @Override
    public void render(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if (url.startsWith(DEFAULT_REDIRECT_PREFIX)) {
            resp.sendRedirect(url.substring(DEFAULT_REDIRECT_PREFIX.length()));
            return;
        }

        RequestDispatcher rd = req.getRequestDispatcher(url);
        rd.forward(req, resp);
    }
}
