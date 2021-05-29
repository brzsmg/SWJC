package com.swlibs.common.database.models;

import java.util.Date;

public interface Synchronized {
    public void setSyncId(Long syncId);
    public void setSyncAt(Date date);
    public Date getSyncAt();
}
