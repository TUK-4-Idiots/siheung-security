package com.siheung.siheung_security

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewpagerAdapter(
    private var title: List<String>,
    private var details: List<String>,
    private var images: List<Int>
) : RecyclerView.Adapter<ViewpagerAdapter.Pager2ViewHolder>() {

    // ViewHolder 내부 클래스 정의
    inner class Pager2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // 아이템 뷰에서 사용하는 뷰 요소들을 선언합니다.
        val itemTitle: TextView = itemView.findViewById(R.id.tvTitle) // 제목 텍스트 뷰
        val itemDetails: TextView = itemView.findViewById(R.id.tvAbout) // 세부 내용 텍스트 뷰
        val itemImage: ImageView = itemView.findViewById(R.id.ivImage) // 이미지 뷰
    }

    // 뷰홀더 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Pager2ViewHolder {
        // 아이템 뷰 생성
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_page, parent, false)
        return Pager2ViewHolder(view) // 뷰홀더 반환
    }

    // 아이템 개수 반환
    override fun getItemCount(): Int {
        return title.size // 데이터의 크기만큼 아이템 개수 반환
    }

    // 뷰홀더에 데이터 바인딩
    override fun onBindViewHolder(holder: Pager2ViewHolder, position: Int) {
        holder.itemTitle.text = title[position] // 제목 설정
        holder.itemDetails.text = details[position] // 세부 내용 설정
        holder.itemImage.setImageResource(images[position]) // 이미지 설정

    }
}