package com.company.superandyeyev.vkgroupreader.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.company.superandyeyev.vkgroupreader.CurrentUser;
import com.company.superandyeyev.vkgroupreader.MyApplication;
import com.company.superandyeyev.vkgroupreader.R;
import com.company.superandyeyev.vkgroupreader.consts.ApiConstants;
import com.company.superandyeyev.vkgroupreader.model.Profile;
import com.company.superandyeyev.vkgroupreader.mvp.presenter.MainPresenter;
import com.company.superandyeyev.vkgroupreader.mvp.view.MainView;
import com.company.superandyeyev.vkgroupreader.rest.api.AccountApi;
import com.company.superandyeyev.vkgroupreader.ui.fragment.BaseFragment;
import com.company.superandyeyev.vkgroupreader.ui.fragment.NewsFeedFragment;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainView {

    @InjectPresenter
    MainPresenter mPresenter;

    private Drawer mDrawer;



    private AccountHeader mAccountHeader;

    @Inject
    AccountApi mAccountApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getApplicationComponent().inject(this);

        mPresenter.checkAuth();


    }

    public void setUpDrawer(){
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.screen_name_news)
                .withIcon(GoogleMaterial.Icon.gmd_home);
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName(R.string.screen_name_members)
                .withIcon(GoogleMaterial.Icon.gmd_people);
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName(R.string.screen_name_topics)
                .withIcon(GoogleMaterial.Icon.gmd_record_voice_over);
        PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(4).withName(R.string.screen_name_info)
                .withIcon(GoogleMaterial.Icon.gmd_info);
        PrimaryDrawerItem item5 = new PrimaryDrawerItem().withIdentifier(5).withName(R.string.screen_name_rules)
                .withIcon(GoogleMaterial.Icon.gmd_assignment);

        mAccountHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .build();
        mDrawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withTranslucentStatusBar(true)
                .withActionBarDrawerToggle(true)
                .withAccountHeader(mAccountHeader)
                .addDrawerItems(item1, new SectionDrawerItem().withName("Группа"),
                        item2, item3, item4, item5)
                .withOnDrawerItemClickListener((view, position, drawerItem) -> {
                    mPresenter.drawerItemClick((int) drawerItem.getIdentifier());
                    return false;
                })
                .build();
    }

    @Override
    public void startSignIn() {
        VKSdk.login(this, ApiConstants.DEFAULT_LOGIN_SCOPE);
    }

    @Override
    public void signedIn() {
        Toast.makeText(this, "Current user id: " + CurrentUser.getId(), Toast.LENGTH_LONG).show();
        setContent(new NewsFeedFragment());
        setUpDrawer();
    }

    @Override
    public void showCurrentUser(Profile profile) {
        List<IProfile> profileDrawerItems = new ArrayList<>();
        profileDrawerItems.add(new ProfileDrawerItem().withName(profile.getFullName())
                .withEmail(VKAccessToken.currentToken().email)
                .withIcon(profile.getDisplayProfilePhoto()));

        mAccountHeader.setProfiles(profileDrawerItems);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                // Пользователь успешно авторизовался
                mPresenter.checkAuth();
            }

            @Override
            public void onError(VKError error) {
                // Произошла ошибка авторизации (например, пользователь запретил авторизацию)
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected int getMainContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void showFragmentFromDrawer(BaseFragment baseFragment) {
        setContent(baseFragment);
    }

    @Override
    public void startActivityFromDrawer(Class<?> act) {
        startActivity(new Intent(MainActivity.this, act));
    }
}
