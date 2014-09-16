package pl.axxxon.micro.android.api;

/**
 * Created by mnarowski on 05.09.14.
 */
public interface ICallback<T> {
    void send(T params);
}
