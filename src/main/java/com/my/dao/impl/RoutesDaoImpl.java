package com.my.dao.impl;

import org.springframework.stereotype.Repository;

import com.my.dao.RoutesDao;
import com.my.po.RoutesInfo;

import my.utils.hibernate.HibernateDAOImpl;
@Repository("routesDao")
public class RoutesDaoImpl extends HibernateDAOImpl<RoutesInfo> implements RoutesDao {

}
