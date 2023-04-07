package com.example.job_introduce.jobPost;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.job_introduce.company.Company;
import com.example.job_introduce.R;

import java.util.List;

public class JobPostAdapter extends RecyclerView.Adapter<JobPostAdapter.ViewHolder> {
    private Context c;
    private List<JobPost> dataList;

    public JobPostAdapter(Context c, List<JobPost> dataList) {
        this.c = c;
        this.dataList = dataList;
    }
    public interface OnItemClickListener{
        void onItemClick(View v, int pos);
    }

    // 리스너 객체 참조를 저장하는 변수
    private OnItemClickListener mListener = null;

    // OnItemClickListener 리스너 객체 참조를 어댑터에 전달하는 메서드
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener ;
    }

    // onCreateViewHolder : 아이템 뷰를 위한 뷰홀더 객체를 생성하여 리턴
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        Context context = parent.getContext();

//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = LayoutInflater.from(c).inflate(R.layout.listitem_open, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        JobPost item = dataList.get(position);
        Company compId = dataList.get(position).getCompId();

        holder.tvCom.setText(compId.getCompName());
        holder.tvTitle.setText(item.getTitle());
        holder.tvCareer.setText(item.getCareer());
        holder.tvRecType.setText(item.getRecType());
        holder.tvLoc.setText(compId.getLoc());
        holder.tvEndDate.setText(item.getEndDate());

    }

    // getItemCount : 전체 데이터의 개수를 리턴
    @Override
    public int getItemCount() {
        return dataList.size();
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivLogo;
        TextView tvCom, tvTitle, tvCareer, tvRecType, tvLoc, tvEndDate;

        ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        JobPost item = dataList.get(pos);
                             if(mListener != null){
                            mListener.onItemClick(view, pos);
                        }
                    }
                }
            });
        // 뷰 객체에 대한 참조
            ivLogo = itemView.findViewById(R.id.iv_logoImage);
            tvCom = itemView.findViewById(R.id.tv_comname);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvCareer = itemView.findViewById(R.id.tv_career);
            tvRecType = itemView.findViewById(R.id.tv_recType);
            tvLoc = itemView.findViewById(R.id.tv_jobpostlist_loc);
            tvEndDate = itemView.findViewById(R.id.tv_endDate);
        }
    }
}
