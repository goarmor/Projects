package com.company.superandyeyev.atlantteamtask.repository;

/**
 * Created by DIMON on 15.10.2017.
 */

public final class RepositoryProvider {

    private static Repository sRepository;

    public static Repository getRepository() {
        if (sRepository == null) {
            sRepository = new DefaultRepository();
        }
        return sRepository;
    }
}
