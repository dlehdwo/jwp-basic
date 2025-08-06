package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import core.mvc.Controller;
import core.mvc.view.JspView;
import core.mvc.view.ModelAndView;
import core.mvc.view.View;
import next.dao.AnswerDao;
import next.dao.QuestionDao;

public class ShowController extends AbstractController {
    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Long questionId = Long.parseLong(req.getParameter("questionId"));
        QuestionDao questionDao = new QuestionDao();
        AnswerDao answerDao = new AnswerDao();

        ModelAndView mv = jspView("/qna/show.jsp");
        mv.addObject("question", questionDao.findById(questionId));

        return mv.addObject("answers", answerDao.findAllByQuestionId(questionId));
    }
}
