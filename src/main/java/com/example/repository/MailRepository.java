package com.example.repository;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.example.domain.Mail;
@Mapper
public interface MailRepository {
	//トークンでメールテーブルを検索
	List<Mail> findMailByToken(Mail mail);

	//メールアドレスでメールテーブルを検索
	List<Mail> findMailByEmail(Mail mail);
	
	//メールテーブルに挿入
	void insertMail (Mail mail);
	
	//メールテーブルのステータスを更新
	void changeStatusMail(Mail mail);

	//メールテーブルのトークンを更新
	void changeTokenMail(Mail mail);
	
}