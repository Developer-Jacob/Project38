package DB;

import Model.User;

public class DBTest {
public static void main(String[] args) {
	DAO dao = new DAO();
	User user = new User();
	user.setId("123");
	user.setMoney(10000);
	user.setNickName("456");
	user.setPassword("123");
	
	if(dao.insert(user)){
		
	}else{
		System.out.println("동일 아이디 존재");
	}
//	dao.updateMoney("456",500);
//	dao.delete(user);
//	User u =	dao.select("123","pppssswww");
//	System.out.println(u.getNickName());
//	System.out.println(u.getMoney());
			
}
}
