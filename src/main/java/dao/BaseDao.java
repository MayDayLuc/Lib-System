package dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao {
    public void save(Object bean);

    public void update(Object bean);

    public Object load(Class c, Serializable id);

    public <T> List<T> getAllList(Class<T> c) ;
}
