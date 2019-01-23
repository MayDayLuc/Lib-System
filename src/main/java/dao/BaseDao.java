package dao;

import java.util.List;

public interface BaseDao {
    public void save(Object bean);

    public Object load(Class c, int id);

    public <T> List<T> getAllList(Class<T> c) ;
}
