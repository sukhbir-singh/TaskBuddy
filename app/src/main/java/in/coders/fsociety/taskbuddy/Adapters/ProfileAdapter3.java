package in.coders.fsociety.taskbuddy.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import in.coders.fsociety.taskbuddy.Models.Circle;
import in.coders.fsociety.taskbuddy.R;

/**
 * Created by sahil on 25/3/17.
 */

public class ProfileAdapter3 extends RecyclerView.Adapter<ProfileAdapter3.viewHolder> {

    private ArrayList<Circle> list =new ArrayList<>();
    private Context context;

    public ProfileAdapter3(ArrayList<Circle> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.circle_layout,parent,false);
        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
       Circle circle = list.get(position);
        if(circle!=null){
            if(circle.getTitle()!=null)
            holder.circleTitle.setText(circle.getTitle());
            holder.circleFriendCount.setText("Number of Member in the Circle is "+circle.getNumberOfMember());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{
        private TextView circleTitle,circleFriendCount;
        public viewHolder(View itemView) {
            super(itemView);
            circleTitle = (TextView) itemView.findViewById(R.id.circleTitle);
            circleFriendCount = (TextView) itemView.findViewById(R.id.circleMember);
        }
    }
}
