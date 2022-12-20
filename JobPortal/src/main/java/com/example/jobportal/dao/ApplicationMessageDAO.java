package com.example.jobportal.dao;

import com.example.jobportal.pojo.ApplicationMessage;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;

@Controller
public class ApplicationMessageDAO extends DAO{

    public ApplicationMessageDAO() {
    }

    public void saveMessage(ApplicationMessage applicationMessage) throws Exception{
        try {
            begin();
            Session session = getSession();
            session.save(applicationMessage);
            commit();
        } catch (Exception e) {
            rollback();
            e.printStackTrace();
            throw new Exception("Could not save application message " + applicationMessage, e);
        }
    }


}
