package ru.appline.Servlets;

import ru.appline.Utils.Utils;
import ru.appline.logic.Model;
import ru.appline.logic.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/pages/delete"})
public class ServletDelete extends HttpServlet {

    Model model = Model.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        PrintWriter pw = response.getWriter();
        Integer id = Utils.parseId(request, pw);
        if (id == null)
            return;

        User user = model.getModel().get(id);
        if (user == null) {
            Utils.printNullUser(pw, id);
        } else {
            model.getModel().remove(id);
            Utils.printUserWithId(pw, user, id);
        }

    }
}
