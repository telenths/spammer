package com.elv.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Concurrent {

    private List<Callable<?>> callableList = new ArrayList<Callable<?>>();
    private Map<String, Future<?>> mapCallable2Future = new HashMap<String, Future<?>>();

    private List<Runnable> runnableList = new ArrayList<Runnable>();
    
    public <T> String add(Callable<T> c) {
        callableList.add(c);
        return c.toString();
    }
    
    public void submitCallable() {
        ExecutorService executor = Executors.newFixedThreadPool(callableList.size());
        for (Callable<?> c : callableList) {
            Future<?> f = executor.submit(c);
            mapCallable2Future.put(c.toString(), f);
        }
        executor.shutdown();
    }

    public <T> T get(String callableKey, Class<T> T) throws InterruptedException, ExecutionException  {
        Future<?> f = mapCallable2Future.get(callableKey);
        if (f == null)
            return null;
        return (T) f.get();
    }

    public void add(Runnable r) {
    	runnableList.add(r);
    }

    public void submitRunnable() {
        ExecutorService executor = Executors.newFixedThreadPool(runnableList.size());
        for (Runnable c : runnableList) {
        	executor.submit(c);
        }
        executor.shutdown();
    }

}
