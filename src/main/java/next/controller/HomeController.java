package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import core.mvc.Controller;
import core.mvc.view.JspView;
import core.mvc.view.ModelAndView;
import core.mvc.view.View;
import next.dao.QuestionDao;

public class HomeController extends AbstractController {
    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        QuestionDao questionDao = new QuestionDao();
        ModelAndView mv = jspView("home.jsp");
        return mv.addObject("questions", questionDao.findAll());
    }
}
