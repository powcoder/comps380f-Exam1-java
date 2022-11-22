https://powcoder.com
代写代考加微信 powcoder
Assignment Project Exam Help
Add WeChat powcoder
package hkmu.comps380f;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        boolean newGame = false;

        if (session.getAttribute("gameNum") == null) {
            newGame = true;
            session.setAttribute("gameNum", 0);
        }

        if (request.getParameter("restart") != null) {
            newGame = true;
        }

        if (newGame) {
            Integer gameNum = (Integer) session.getAttribute("gameNum");
            session.setAttribute("gameNum", gameNum + 1);
            session.setAttribute("trial", 0);
            session.setAttribute("ans", (int) (Math.random() * 50 + 1));
            request.setAttribute("result", "This is a new game.");
        }
        request.getRequestDispatcher("/WEB-INF/jsp/game_form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(); 

        if (request.getParameter("guess") == null) {
            response.sendRedirect("guess");
            return;
        }

        Integer gameNum = (Integer) session.getAttribute("gameNum");
        Integer trial = (Integer) session.getAttribute("trial");
        trial += 1;
        Integer ans = (Integer) session.getAttribute("ans");

        int guess = Integer.parseInt(request.getParameter("guess"));

        if (guess == ans) {
            request.setAttribute("result", "Correct! You have correctly guessed the answer "
                    + ans + " in " + trial + " times. <br /> This is a new game.");
            session.setAttribute("gameNum", gameNum + 1);
            session.setAttribute("trial", 0);
            session.setAttribute("ans", (int) (Math.random() * 50 + 1));
        } else {
            request.setAttribute("result",
                    "Incorrect! The answer is " + (ans < guess ? "smaller" : "larger")
                    + " than " + guess + ". You have tried " + trial + " times.");
        }
        request.getRequestDispatcher("/WEB-INF/jsp/game_form.jsp").forward(request, response);
    }
    
    @Override
    public void destroy() {
        System.out.println("Servlet " + this.getServletName() + " has stopped.");
    }
}
