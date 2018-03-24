package com.my.dao.impl;

import org.springframework.stereotype.Repository;

import com.my.dao.LogDao;
import com.my.po.OpLogInfo;

import my.utils.hibernate.HibernateDAOImpl;
@Repository("logDao")
public class LogDaoImpl extends HibernateDAOImpl<OpLogInfo> implements LogDao{

}
