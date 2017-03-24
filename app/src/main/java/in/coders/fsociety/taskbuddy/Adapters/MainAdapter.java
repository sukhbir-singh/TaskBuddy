package in.coders.fsociety.taskbuddy.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import in.coders.fsociety.taskbuddy.Models.SingleMainPost;
import in.coders.fsociety.taskbuddy.R;

public class MainAdapter extends RecyclerView.Adapter<in.coders.fsociety.taskbuddy.Adapters.MainAdapter.ViewHolder> {

    ArrayList<SingleMainPost> arrayList=new ArrayList<>();
    Context context;

    public MainAdapter(Context context, ArrayList<SingleMainPost> posts){
        this.context = context;
        this.arrayList=posts;
    }

    @Override
    public in.coders.fsociety.taskbuddy.Adapters.MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_mainfeed,parent,false);
        in.coders.fsociety.taskbuddy.Adapters.MainAdapter.ViewHolder view_holder = new in.coders.fsociety.taskbuddy.Adapters.MainAdapter.ViewHolder(view);
        return view_holder;
    }

    @Override
    public void onBindViewHolder(in.coders.fsociety.taskbuddy.Adapters.MainAdapter.ViewHolder holder, int position) {
        if(arrayList!=null){

            if(arrayList.get(position).getTitle()!=null){
                holder.title.setText(arrayList.get(position).getTitle());
                holder.description.setText(arrayList.get(position).getDescription());
                holder.credits.setText("Credits: "+arrayList.get(position).getCredit()+"");
                holder.username.setText(arrayList.get(position).getAuthorName()+"");
                holder.post_peoples_involved.setText("No of people interested: "+arrayList.get(position).getNoOfParticipant()+"");
                holder.button.setVisibility(View.GONE);

                String tags_text="";
                for(int i=0;i<arrayList.get(position).getTags().size();i++){
                    tags_text=tags_text+"#"+arrayList.get(position).getTags().get(i)+" ";
                }

                if(tags_text.length()>=2)
                    holder.tags.setText(tags_text);

                Glide.with(context).load(arrayList.get(position).getAuthorPicUrl()).asBitmap().into(holder.profilePic);
            }
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private ArrayList<String> categoryTitle;
        private int credit;
        private TextView description;
        private TextView noOfParticipant;
        private boolean isPublic;
        private boolean status;
        private ArrayList<String> tags;
        private String title;

        public ViewHolder(View v){
            super(v);
            this.profilePic = (ImageView)v.findViewById(R.id.profilePic);
            this.username = (TextView)v.findViewById(R.id.username);
            this.tags = (TextView)v.findViewById(R.id.post_tags);
            this.title = (TextView)v.findViewById(R.id.post_title);
            this.description = (TextView)v.findViewById(R.id.post_description);
            this.credits = (TextView)v.findViewById(R.id.post_credits);
            this.username = (TextView)v.findViewById(R.id.username);
            this.post_peoples_involved = (TextView)v.findViewById(R.id.post_peoples_involved);
            this.button=(Button)v.findViewById(R.id.wanted_to_work);

        }
    }
}

