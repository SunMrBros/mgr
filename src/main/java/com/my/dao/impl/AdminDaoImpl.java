package com.my.dao.impl;

import org.springframework.stereotype.Repository;

import com.my.dao.AdminDao;
import com.my.po.AdminInfo;

import my.utils.hibernate.HibernateDAOImpl;
@Repository("adminDao")
public class AdminDaoImpl  extends HibernateDAOImpl<AdminInfo> implements AdminDao {


}
