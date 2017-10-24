package com.company.superandyeyev.atlantteamtask.repository;


import android.content.Context;
import android.graphics.Bitmap;

import com.company.superandyeyev.atlantteamtask.rest.ApiFactory;
import com.company.superandyeyev.atlantteamtask.rest.Model.Comment;
import com.company.superandyeyev.atlantteamtask.rest.Model.Photo;
import com.company.superandyeyev.atlantteamtask.rest.Model.Post;
import com.company.superandyeyev.atlantteamtask.rest.Model.Todo;
import com.company.superandyeyev.atlantteamtask.rest.Model.User;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by DIMON on 15.10.2017.
 */

public class DefaultRepository implements Repository {

    private Random random;


    @Override
    public Observable<Post> getPost(int n) {
        return ApiFactory.getServiceApi().getPost(n)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Comment> getComment(int n) {
        return ApiFactory.getServiceApi().getComment(n)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //сохраняем 'users', при ошибки загрузки отображаем сохраненное
    @Override
    public Observable<List<User>> getUsers() {
        return ApiFactory.getServiceApi().getUsers()
                .flatMap(users -> {
                    Realm.getDefaultInstance().executeTransaction(realm -> {
                        realm.delete(User.class);
                        realm.insert(users);
                    });
                    return Observable.just(users);
                })
                .onErrorResumeNext(throwable -> {
                    Realm realm = Realm.getDefaultInstance();
                    RealmResults<User> users = realm.where(User.class).findAll();
                    return Observable.just(realm.copyFromRealm(users));
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //Picasso кэширует изображение при загрузке, соответственно при отсутствии интернета выгружаем url из realm,
    //а bitmap из Picasso
    @Override
    public Observable<Bitmap> getPhoto(Context context) {
        return ApiFactory.getServiceApi().getPhoto()
                .flatMap(photo -> {
                    try {
                        Realm.getDefaultInstance().executeTransaction(realm -> {
                            realm.delete(Photo.class);
                            realm.insert(photo);
                        });
                        Bitmap bitmap = Picasso.with(context)
                                .load(photo.getUrl())
                                .get();
                        return Observable.just(bitmap);
                    }
                    catch (IOException throwable) {
                        return Observable.error(throwable);
                    }
                })
                .onErrorResumeNext(throwable -> {
                    try {
                        Realm realm = Realm.getDefaultInstance();
                        Photo p = realm.where(Photo.class).findFirst();
                        Bitmap bitmap = Picasso.with(context)
                                .load(p.getUrl())
                                .get();
                        return Observable.just(bitmap);
                    }
                    catch (IOException e) {
                        return Observable.error(throwable);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Todo> getTodo() {
        if (random == null) {
            random = new Random();
        }
        return ApiFactory.getServiceApi().getTodo(random.nextInt(199) + 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
