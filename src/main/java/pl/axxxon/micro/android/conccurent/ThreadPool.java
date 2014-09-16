package pl.axxxon.micro.android.conccurent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by mnarowski on 07.09.14.
 */
public class ThreadPool {
    private static ThreadPool sInstance = new ThreadPool();

    private ExecutorService mThreadPoolExecutor = Executors.newFixedThreadPool(3);

    public static ThreadPool getInstance() {
        return sInstance;
    }

    private ThreadPool(){}

    public void execute(Runnable pRunnable){
        mThreadPoolExecutor.execute(pRunnable);
    }

    public void cancel(){
        mThreadPoolExecutor.shutdownNow();
        mThreadPoolExecutor.shutdown();
        mThreadPoolExecutor = Executors.newFixedThreadPool(3);
    }

    public Future executeWithResult(Runnable pRunnable){
        return mThreadPoolExecutor.submit(pRunnable);
    }

}
