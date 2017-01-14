package info.akixe.madridguide.model.base;

public interface IUpdatable<T> {
    void add(T element);
    void delete(T element);
    void edit(T newElement, long index);
}
