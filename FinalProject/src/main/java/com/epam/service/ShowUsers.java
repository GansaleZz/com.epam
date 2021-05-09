package com.epam.service;

import com.epam.db.dao.impl.UserDaoImpl;
import com.epam.entity.User;
import com.epam.entity.UserRole;
import com.epam.entity.UserStatus;
import com.epam.exceptions.DaoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ShowUsers implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getSession().getAttribute("userRole").equals("CLIENT")){
            response.sendRedirect(ServletDestination.CLIENTHOMEPAGE.getPath());
        }
        PrintWriter printWriter = response.getWriter();
        printWriter.println("<a href=\"http://localhost:8080/controller?command=LOGOUT\">Log Out</a>\n" +
                "\n" +
                "    <a href=\"http://localhost:8080/usersView/admin/home/home.jsp\">Home</a>\n" +
                "\n" +
                "    <a href=\"http://localhost:8080/controller?command=ACTSHOWROOMS\">Rooms</a>\n" +
                "\n" +
                "    <a href=\"http://localhost:8080/controller?command=ACTSHOWPROFILE\">Profile</a>\n" +
                "\n" +
                "    <a href=\"http://localhost:8080/controller?command=ACTCREATEREQUEST\">Create request</a>"+
                "\n" +
                "    <a href=\"http://localhost:8080/controller?command=ACTSHOWUSERS\">Users list</a><br>");
        UserDaoImpl userDao = new UserDaoImpl();
        try {
            List<User> list = userDao.findAllEntities();
            list.stream()
                    .forEach(i -> printWriter.println(
                            "<form action=\"/controller?command=ACTCHANGEUSERSRS\" method = \"post\">"+
                            "<b>Id: </b><input type = \"text\" size =\"5\" name = \"id\" value =\""+i.getId()+"\"  readonly><br>"+
                            " <b>Name: </b> " + i.getName()+ "<br>"+
                            " <b>Email: </b>"+i.getEmail()+ "<br>"+
                            selectedRole(i) +
                            "<b>Status: </b><select name=\"status\">\n" + selectedStatus(i) + "</select><br>"+
                            "<input type = \"submit\" value=\"Submit\" /> </form>"));
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    private String selectedStatus(User user){
        if(user.getStatus().equals(UserStatus.AVAILABLE)){
            return "        <option selected = \"selected\">AVAILABLE</option>\n" +
                    "        <option>BANNED</option>\n";

        }else{
            return "        <option>AVAILABLE</option>\n" +
                    "        <option selected = \"selected\">BANNED</option>\n";
        }
    }

    private String selectedRole(User user){
        String str = "";
        switch(user.getUserRole()){
            case ADMIN -> str = user.getUserRole()+"</br>";
            case MODERATOR -> str = "<b>Status: </b><select name=\"role\">" +
                    "<option selected = \"selected\">MODERATOR</option>" +
                    "<option>CLIENT</option></select><br>";
            case CLIENT -> str = "<b>Status: </b><select name=\"role\">" +
                    "<option>MODERATOR</option>" +
                    "<option selected = \"selected\">CLIENT</option></select><br>";
        }
        return str;
    }
}
