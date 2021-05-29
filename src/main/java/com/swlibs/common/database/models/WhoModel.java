package com.swlibs.common.database.models;

import java.io.Serializable;
import java.util.Date;

/**
 *
 */
public interface WhoModel {
    //Создание
    public Date getCreatedAt();
    public Long getCreatedBy();
    //Обновление
    public Date getUpdatedAt();
    public Long getUpdatedBy();
    //Удаление
    public Date getRemovedAt();
    public Long getRemovedBy();
}
