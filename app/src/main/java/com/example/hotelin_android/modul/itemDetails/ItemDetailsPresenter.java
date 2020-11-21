package com.example.hotelin_android.modul.itemDetails;

public class ItemDetailsPresenter implements ItemDetailsContract.Presenter{
    private final ItemDetailsContract.View view;

    public ItemDetailsPresenter(ItemDetailsContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public void performLogin(){
        view.redirectToList();
    }

}