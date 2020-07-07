package adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.stefandy_2201789536.SavedFragment;
import com.example.stefandy_2201789536.SearchFragment;

public class SectionsPagerAdapter extends FragmentStateAdapter {
    public SectionsPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position) {
            case 0:
                return new SearchFragment();
            case 1:
                return new SavedFragment();
            default:
                return new SearchFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}