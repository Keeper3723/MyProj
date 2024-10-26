package by.leha.web.mappers;

public interface Mapper<From,To>{
    To map(From f);
    From reverseMap(To t);
}
