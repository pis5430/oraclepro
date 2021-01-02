package com.javaex.phone;

import java.util.List;
import java.util.Scanner;

public class PhoneApp {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		PhoneDao phoneDao = new PhoneDao();
		List<PersonVo> phoneList = phoneDao.getPersonList();
		
		int personId;
		String name , hp , company ;
		
		
		//시작화면
		System.out.println("****************************************************");
		System.out.println("*              전화번호 관리 프로그램              *");
		System.out.println("****************************************************");
		
		
		while(true){
			//반복구간1
			System.out.println("1.리스트   2.등록   3.수정   4.삭제   5.검색   6.종료");
			System.out.println("-----------------------------------------------------");
			System.out.print(">메뉴번호 : ");
			
			String menu = sc.nextLine();
			
			//1. 리스트
			if("1".equals(menu)) {
				System.out.println("<1.리스트>");
				phoneList = phoneDao.getPersonList(); 
				//리스트 넣어주기 (넣어져있는 리스트를 불러올때는 sql에서 미리 커밋하기)
				
				for(int i=0; i<phoneList.size(); i++) {
					PersonVo vo = phoneList.get(i);
					System.out.println(vo.getPerson_id() + ".   " + vo.getName() + "    " + vo.getHp() + "    " + vo.getCompany());
				}
				System.out.println("");
			}
			
			//2.등록(select)
			if("2".equals(menu)) { 
				System.out.println("<2.등록>");
				System.out.print("이름 > ");
				name = sc.nextLine();
				System.out.print("휴대전화 > ");
				hp = sc.nextLine();
				System.out.print("회사번호 > ");
				company = sc.nextLine();
				
				PersonVo vo = new PersonVo(name,hp,company);
				phoneDao.personInsert(vo);
				
			}
			
			
			//3.수정(update)
			if("3".equals(menu)) { 
				System.out.println("<3.수정>");
				System.out.print("번호 > ");
				personId = sc.nextInt();
				
				sc.nextLine();  //<--개행문자를 받아 처리        

				System.out.print("이름 > ");
			    name = sc.nextLine();
				System.out.print("휴대전화 > ");
			    hp = sc.nextLine();
				System.out.print("회사번호 > ");
				company = sc.nextLine();
				
				PersonVo vo = new PersonVo(personId,name,hp,company);
				phoneDao.personUpdate(vo);
				
			 }
			
			
			//4.삭제(delete)
			if("4".equals(menu)) { 
				System.out.println("<4.삭제>");
				System.out.print("번호 > ");
				personId = sc.nextInt();
			       
				phoneDao.personDelete(personId);
				
			 }//count = pstmt.executeUpdate(); 값이 안들어가서 삭제가 안됬었음.
			
			
			//5.검색(delete)			
			//name,hp,company 3개의 필드 검색
			if("5".equals(menu)) {
	
				System.out.println("<5.검색>");
				System.out.print("검색어 > ");
				String search = sc.nextLine();
				
				phoneList = phoneDao.getSearchList(search); //입력값  
				
				// 검색 결과값 출력
				// 검색 결과값에서 getPerson_id가 0으로 일괄표기됨 --> 고치는법찾기
				// 서브쿼리처럼 정렬한뒤에 출력해 줘야되는지? 셀렉문에 바로 넣었더니 안됨 --> 서브쿼리로 넣어야되는지?
				for(int i =0; i< phoneList.size(); i++) {
					PersonVo vo = phoneList.get(i);
					System.out.println(vo.getPerson_id() + ".   " + vo.getName() + "    " + vo.getHp() + "    " + vo.getCompany());
				}
				
				System.out.println("");

			 }
			
			
			if("6".equals(menu)) { //6. 종료
				System.out.println("****************************************************");
				System.out.println("*                    감사합니다.                   *");
				System.out.println("****************************************************");
				break;
			}else { //1,2,3,4,5,6 외의 값들에 대한 결과
				System.out.println("[다시 입력해주세요]");
				System.out.println("");
			}
				
		}
		
		
		sc.close();
	}

}
