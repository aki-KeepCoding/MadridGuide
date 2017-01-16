package info.akixe.madridguide.model.base;


public interface IFilterable<T> {
    T filter(String query);
}
