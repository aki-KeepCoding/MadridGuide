package info.akixe.madridguide.model.base;

import java.util.List;


public interface IIterable<T> {
    long size();
    T get(long index);
    List<T> all();
}
