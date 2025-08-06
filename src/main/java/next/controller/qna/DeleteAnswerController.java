package next.controller.qna;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import core.mvc.AbstractController;
import core.mvc.Controller;
import core.mvc.view.JsonView;
import core.mvc.view.ModelAndView;
import core.mvc.view.View;
import next.dao.AnswerDao;
import next.model.Result;

public class DeleteAnswerController extends AbstractController {
    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Long answerId = Long.parseLong(req.getParameter("answerId"));
        AnswerDao answerDao = new AnswerDao();

        answerDao.delete(answerId);

        ModelAndView mv = jsonView();
        return mv.addObject("result", Result.ok());
    }
}
