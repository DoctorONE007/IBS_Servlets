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

@WebServlet(urlPatterns = {"/pages/put"})
public class ServletPut extends HttpServlet {
    Model model = Model.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");

        PrintWriter pw = response.getWriter();
        Integer id = Utils.parseId(request, pw);
        if (id == null)
            return;

        User user = model.getModel().get(id);
        if (user == null) {
            Utils.printNullUser(pw, id);
            return;
        }

        user = Utils.parseUser(request, pw);
        if (user == null)
            return;

        model.getModel().get(id).setName(user.getName());
        model.getModel().get(id).setSurname(user.getSurname());
        model.getModel().get(id).setSalary(user.getSalary());

        Utils.printEditUserMsg(model, pw, id);
    }
}
