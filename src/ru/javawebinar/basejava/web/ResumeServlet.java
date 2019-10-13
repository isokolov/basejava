package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.Storage;

import java.io.IOException;
import java.io.Writer;

public class ResumeServlet extends javax.servlet.http.HttpServlet {

    private Storage storage = Config.get().getStorage();;

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
//        response.setHeader("Content-Type", "text/html; charset=UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        Writer writer = response.getWriter();
        writer.write(
                "<html>\n" +
                        "<head>\n" +
                        "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                        "    <link rel=\"stylesheet\" href=\"css/style.css\">\n" +
                        "    <title>Список всех резюме</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "<table border=\"1\" cellpadding=\"8\" cellspacing=\"5\">\n" +
                        "    <tr>\n" +
                        "        <th>Uuid</th>\n" +
                        "        <th>Name</th>\n" +
                        "    </tr>\n");
        for (Resume resume : storage.getAllSorted()) {
            writer.write(
                    "<tr>\n" +
                            "     <td>" + resume.getUuid() + "</td>\n" +
                            "     <td>" + resume.getFullName() + "</td>\n" +
                            "</tr>\n");
        }
        writer.write("</table>\n" +
                "</body>\n" +
                "</html>\n");
    }
}
