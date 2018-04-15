package com.my.dao.impl;

import org.springframework.stereotype.Repository;

import com.my.dao.CityDao;
import com.my.po.CityInfo;

import my.utils.hibernate.HibernateDAOImpl;
@Repository("cityDao")
public class CityDaoImpl extends HibernateDAOImpl<CityInfo> implements CityDao{

}
