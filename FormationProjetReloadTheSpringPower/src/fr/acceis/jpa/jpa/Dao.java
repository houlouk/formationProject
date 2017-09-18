package fr.acceis.jpa.jpa;

import java.util.List;

public interface Dao<T,KEY> {

  public T add(T t);

  public int delete(KEY id);

  public T get(KEY id);

  public List<T> getAll();

  public int update(KEY id, T t);

}
