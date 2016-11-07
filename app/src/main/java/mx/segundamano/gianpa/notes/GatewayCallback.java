package mx.segundamano.gianpa.notes;

public interface GatewayCallback<T> {
    void onSuccess(T t);
    void onError(Throwable e);
}
