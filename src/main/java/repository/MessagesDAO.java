package repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import data.vo.Message;

/*
 * Message
 * 
 * DAO : Data Access Object 의 약자로 DB 처리하는 객체를 만들 때 붙여서 만들기도 한다.
 */

public class MessagesDAO extends DAO {

	// 데이터 등록을 처리할 method
	public static int createMessage(String target, String body) {

		SqlSession session = factory.openSession(true);
		Map<String, String> obj = new HashMap<>();
		obj.put("target", target);
		obj.put("body", body);

		int r = session.insert("messages.create", obj);
		
		session.close();

		return r;
	}

	// 특정 동물에 대한 메세지를 읽어오는 것을 처리할 method
	public static List<Message> readMessages(String target) {

		SqlSession session = factory.openSession();
		List<Message> li = session.selectList("messages.readByTarget", target);
		session.close();

		return li;
	}

}
