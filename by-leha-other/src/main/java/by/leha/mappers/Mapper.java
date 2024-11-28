package by.leha.mappers;

public interface Mapper<F,T> {
    T map(F f);
    F reverseMap(T t);


}
