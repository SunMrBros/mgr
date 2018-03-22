package com.my.dao.impl;

import org.springframework.stereotype.Repository;

import com.my.dao.LunboDao;
import com.my.po.LunboInfo;

import my.utils.hibernate.HibernateDAOImpl;
@Repository("lunboDao")
public class LunboDaoImpl extends HibernateDAOImpl<LunboInfo> implements LunboDao {


}
