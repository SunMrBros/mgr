package com.my.dao.impl;

import org.springframework.stereotype.Repository;

import com.my.dao.TownDao;
import com.my.po.TownInfo;

import my.utils.hibernate.HibernateDAOImpl;
@Repository("townDao")
public class TownDaoImpl extends HibernateDAOImpl<TownInfo> implements TownDao{

}
