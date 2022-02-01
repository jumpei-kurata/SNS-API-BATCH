package com.example.repository;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.domain.Timeline;
/**
 * timelinesテーブルのRepository
 * 
 * @author ootomokenji
 *
 */
@Mapper
public interface TimelineRepository {

	// タイムラインを新しい順に50件検索 
	List<Timeline> findAllTimeline(Timeline timeline);

	// 引数のタイムラインIDより古いタイムラインを新しい順に50件検索 
	List<Timeline> findAllTimelineOld(Timeline timeline);
	
	// 1件ロード
	Timeline findTimelineById (Timeline timeline);

	// 投稿ユーザーの最新50件のつぶやきを新しい順に検索
	List<Timeline> findByPostUserId(@Param("requestedUserId") Integer requestedUserId ,@Param("visitingUserId") Integer visitingUserId );
	
	// 閲覧されるユーザーの最新50件のいいねしたつぶやきを新しい順に検索
	List<Timeline> findByRequestedUserIdLike(@Param("requestedUserId") Integer requestedUserId ,@Param("visitingUserId") Integer visitingUserId );

	//タイムラインを登録
	void insertTimeline(Timeline timeline);

	// タイムラインを論理削除
	void updateDelete(Timeline timeline);

	//いいねカウントを+-1
	void updateLikeCount(@Param("id") Integer id, @Param("status") Integer status);
	
	//コメントカウントを+-1
	void updateCommentCount(@Param("id") Integer id, @Param("status") Integer status);
	
}