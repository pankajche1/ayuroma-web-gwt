package com.ayurma.ayuromaweb.server.model;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class CompanyInfo {
	@PrimaryKey
    @Persistent
    private String key;

}
