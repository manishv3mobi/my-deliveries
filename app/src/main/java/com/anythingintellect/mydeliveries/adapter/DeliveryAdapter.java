package com.anythingintellect.mydeliveries.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.anythingintellect.mydeliveries.R;
import com.anythingintellect.mydeliveries.databinding.ItemDeliveryBinding;
import com.anythingintellect.mydeliveries.model.Delivery;
import com.anythingintellect.mydeliveries.util.Navigator;
import com.anythingintellect.mydeliveries.viewmodel.DeliveryItemViewModel;

import io.realm.RealmResults;

/**
 * Created by ishan.dhingra on 04/09/17.
 */

public class DeliveryAdapter extends RecyclerView.Adapter<DeliveryAdapter.DeliveryViewHolder> {

    private final RealmResults<Delivery> deliveries;
    private final Navigator navigator;

    public DeliveryAdapter(RealmResults<Delivery> deliveries, Navigator navigator) {
        this.deliveries = deliveries;
        this.navigator = navigator;
    }




    @Override
    public DeliveryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemDeliveryBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.item_delivery, parent, false);
        return new DeliveryViewHolder(binding, new DeliveryItemViewModel(navigator));
    }

    @Override
    public void onBindViewHolder(DeliveryViewHolder holder, int position) {
        holder.bind(deliveries.get(position));
    }

    @Override
    public int getItemCount() {
        return deliveries.size();
    }

    static class DeliveryViewHolder extends RecyclerView.ViewHolder {
        private final ItemDeliveryBinding binding;
        private final DeliveryItemViewModel viewModel;
        public DeliveryViewHolder(ItemDeliveryBinding binding, DeliveryItemViewModel viewModel) {
            super(binding.getRoot());
            this.binding = binding;
            this.viewModel = viewModel;
        }

        public void bind(Delivery delivery) {
            viewModel.setDelivery(delivery);
            binding.setVm(viewModel);
            binding.executePendingBindings();
        }
    }

}
