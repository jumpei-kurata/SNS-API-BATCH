package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Notification;
import com.example.domain.User;
import com.example.repository.LikeCommentRepository;

@Service
@Transactional
public class NotificationService {

	@Autowired
	private LikeCommentRepository likeCommentRepository;
	
// 汎用	
	
	/**
	 * 渡したuserデータのIDに沿って、そのユーザーへの通知リストを表示するサービス
	 * 
	 * @param user
	 * @return
	 */
	public List<Notification> showNotificationListByUser(User user){
		// そのユーザーへの通知を取得
		List<Notification> list = likeCommentRepository.findNotificationByUserId(user);
		
		// 現時点でDBに格納されているnoticed カラムを更新
		likeCommentRepository.updateHasNoticed(user);
		
		return list ;
	}
	
}
