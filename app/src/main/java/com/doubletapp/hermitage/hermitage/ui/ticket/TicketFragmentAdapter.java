package com.doubletapp.hermitage.hermitage.ui.ticket;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.doubletapp.hermitage.hermitage.model.Ticket;
import com.doubletapp.hermitage.hermitage.utils.Data;

/**
 * Created by navi9 on 22.10.2017.
 */

public class TicketFragmentAdapter extends FragmentStatePagerAdapter {
    public TicketFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Ticket ticket = Data.tickets.get(position);

        return TicketFragment.newInstance(ticket.getDate(), ticket.getTime(), ticket.getCode());
    }

    @Override
    public int getCount() {
        return Data.tickets.size();
    }
}
