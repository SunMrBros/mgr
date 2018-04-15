package com.my.dao.impl;

import org.springframework.stereotype.Repository;

import com.my.dao.BaseDao;
import com.my.po.BaseInfo;

import my.utils.hibernate.HibernateDAOImpl;

@Repository("baseDao")
public class BaseDaoImpl extends HibernateDAOImpl<BaseInfo> implements BaseDao {

}
