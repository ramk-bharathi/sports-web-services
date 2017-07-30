package com.pmc.sports.services;

import com.pmc.sports.beans.NewSportsResponseBean;
import com.pmc.sports.beans.SportsBean;
import com.pmc.sports.daos.Sports;
import com.pmc.sports.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class SportsServices {
    public final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public SportsBean getSport(int id){
        SportsBean bean = null;
        Session session = null;
        Transaction transaction = null;
        try{
            session = sessionFactory.getCurrentSession();
            transaction = session.beginTransaction();

            Sports sport = (Sports) session.get("com.pmc.sports.daos.Sports", id);
            if(sport!=null){
                bean = new SportsBean();
                bean.setSportsId(sport.getId());
                bean.setSportsName(sport.getSportsName());
                bean.setActive(sport.isActive());
            }
            transaction.commit();
        } catch(RuntimeException re){
            if(transaction!=null){
                transaction.rollback();
            }
            re.printStackTrace();
            throw re;
        } finally {
            if(session!=null && session.isOpen()){
                session.close();
            }
        }
        return bean;
    }

    public NewSportsResponseBean newSports(SportsBean bean){
        NewSportsResponseBean responseBean = null;
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.getCurrentSession();
            transaction = session.beginTransaction();

            Sports sport = new Sports();
            sport.setSportsName(bean.getSportsName());
            sport.setActive(bean.isActive());
            session.persist(sport);
            responseBean = new NewSportsResponseBean();
            responseBean.setSportsId(sport.getId());
            responseBean.setMessage("success");

            transaction.commit();
        } catch (RuntimeException re){
            if(transaction != null){
                transaction.rollback();
            }
            re.printStackTrace();
            throw re;
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
        return responseBean;
    }
}
