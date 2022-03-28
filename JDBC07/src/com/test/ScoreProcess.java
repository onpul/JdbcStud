/*======================
	ScoreProcess.java
=======================*/
package com.test;

import java.util.ArrayList;
import java.util.Scanner;

public class ScoreProcess
{
	//주요 변수 선언
	private ScoreDAO dao;
	
	//생성자
	public ScoreProcess()
	{
		dao = new ScoreDAO();
	}//end ScoreProcess()
	
	
	//성적 입력
	public void prcInsert()
	{
		Scanner sc = new Scanner(System.in);
		
		try
		{
			// 데이터베이스 연결하기
			dao.connection();
			
			// 주요 변수 선언 및 입력 받기
			int count = dao.count();
			
			System.out.printf("%d번 학생 성적 입력(이름 국어 영어 수학) : ", ++count);
			
			String name = sc.next();
			int kor = sc.nextInt();
			int eng = sc.nextInt();
			int mat = sc.nextInt();
			
			// dto 구성하기
			ScoreDTO dto = new ScoreDTO();
			dto.setName(name);
			dto.setKor(kor);
			dto.setEng(eng);
			dto.setMat(mat);
			
			//dto 메소드 호출
			int result = dao.insert(dto);
			
			if (result > 0)
			{
				System.out.println("성적 입력이 완료되었습니다.");
			}			
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		finally 
		{
			try
			{
				dao.unconnection();
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
			
		}
		
		
	}//end prcInsert()
	
	
	
	
	
	//성적 전체 조회
	public void prcSelectAll()
	{
		try
		{
			//데이터베이스 연결하기
			dao.connection();
			
			//주요 변수 선언 및 출력하기
			int count = dao.count();
			System.out.printf("전체 인원 수 : %d",count);
			System.out.println();
			System.out.println("번호   이름   국어   영어   수학   총점   평균   석차");
			for (ScoreDTO dto : dao.selectAll())
			{
				//출력하기
				System.out.println();
				
				System.out.printf("%3d %5s %5d %5d %6d %7d %7.1f %5d \n"
								  ,dto.getSid(), dto.getName()
								  ,dto.getKor(),dto.getEng(),dto.getMat()
								  ,dto.getTot(),dto.getAvg(),dto.getRank());
			}
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		finally 
		{
			try
			{
				dao.unconnection();
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
			
		}
		
	}//end prcSelectAll()
	
	
	
	
	
	//이름 검색 -- 여기에 커넥션 해제가 없어서 계속 에러난거였네...
	public void prcSearchName()
	{
		try
		{
			dao.connection();
			
			//주요 변수 선언 및 입력 받기
			System.out.print("검색할 이름을 입력하세요: ");
			Scanner sc = new Scanner(System.in);
			String name = sc.next();
			System.out.printf("전체 인원 수 : %d",dao.count());
			System.out.println();
			System.out.println("번호   이름   국어   영어   수학   총점   평균   석차");
			
			//dao 메소드 호출
			for (ScoreDTO dto : dao.search(name))
			{
				//출력하기
				System.out.printf("%3d %5s %5d %5d %6d %7d %7.1f %5d \n"
								  ,dto.getSid(), dto.getName()
								  ,dto.getKor(),dto.getEng(),dto.getMat()
								  ,dto.getTot(),dto.getAvg(),dto.getRank());
			}
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		finally 
		{
			try
			{
				dao.unconnection();
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
			
		}
	}//end prcSearchName()
	
	
	
	//성적 수정
	public void prcUpdate()
	{
		try
		{
			//DB 연결
			dao.connection();
			
			//주요변수 선언 및 입력받기
			Scanner sc = new Scanner(System.in);
			System.out.print("수정할 학생번호를 입력해주세요 : ");
			int sid = sc.nextInt();
			ArrayList<ScoreDTO> scoreNew = dao.search(sid);
			
			if (scoreNew.size() == 0 )
				return;
			//대상정보 출력하기
			System.out.println();
			System.out.println("번호   이름   국어   영어   수학   총점   평균   석차");
			for (ScoreDTO dto : dao.search(sid))
			{
				//출력하기
				System.out.printf("%3d %5s %5d %5d %6d %7d %7.1f %5d \n"
								  ,dto.getSid(), dto.getName()
								  ,dto.getKor(),dto.getEng(),dto.getMat()
								  ,dto.getTot(),dto.getAvg(),dto.getRank());
			}
			
			
			ScoreDTO dto2 = scoreNew.get(0);
			String aName = dto2.getName();
			int aKor = dto2.getKor();
			int aEng = dto2.getEng();
			int aMat = dto2.getMat();
			
			
			
			
			//수정정보 받기
			System.out.println("※ 기존 정보를 유지하시려면 『-』 입력.\n");
			System.out.printf("기존 이름 : %s \n",aName);
			System.out.print("수정할 이름 :");
			String name = sc.next();
			if (name.equals("-"))
			{
				name = aName;
			}
			System.out.printf("기존 국어점수 : %d\n",aKor);
			System.out.print("수정할 국어점수 :");
			String kor2 = sc.next();
			int kor = 0;
			if (kor2.equals("-"))
			{
				kor = aKor;
			}
			else
			{
				kor = Integer.parseInt(kor2);
			}
			System.out.printf("기존 영어점수 : %d\n",aEng);
			System.out.print("수정할 영어점수 :");
			String eng2 = sc.next();
			int eng = 0;
			if (eng2.equals("-"))
			{
				eng = aEng;
			}
			else
			{
				eng = Integer.parseInt(eng2);
			}
			System.out.printf("기존 수학점수 : %d\n",aMat);
			System.out.print("수정할 수학점수 :");
			String mat2 = sc.next();
			int mat = 0;
			if (mat2.equals("-"))
			{
				mat = aMat;
			}
			else
			{
				mat = Integer.parseInt(mat2);
			}
			
			//수정정보로 dto 구성하기
			ScoreDTO dto = new ScoreDTO();
			dto.setSid(sid);
			dto.setName(name);
			dto.setKor(kor);
			dto.setEng(eng);
			dto.setMat(mat);
			
			
			//구성된 dto로 dao메소드 호출하기.
			int result = dao.update(dto);
			
			if (result > 0)
				System.out.println(sid + "번 학생의 정보가 수정되었습니다.");
			
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		finally 
		{
			try
			{
				dao.unconnection();
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
			
		}
	
	}//end prcUpdate()
	
	
	
	
	
	
	//성적 삭제하기
	public void prcDelete()
	{
		try
		{
			dao.connection();
			
			Scanner sc = new Scanner(System.in);
			System.out.print("삭제할 학생번호 입력해주세요 : ");
			int sid = sc.nextInt();
			
			
			//출력하기
			System.out.println();
			System.out.println("번호   이름   국어   영어   수학   총점   평균");
			//대상정보 출력하기
			for (ScoreDTO dto : dao.search(sid))
			{
				System.out.printf("%3d %5s %5d %5d %6d %7d %7.1f \n"
								  ,dto.getSid(), dto.getName()
								  ,dto.getKor(),dto.getEng(),dto.getMat()
								  ,dto.getTot(),dto.getAvg());
			}
			
			
			do
			{
				System.out.print(sid + "번 학생 정보를 정말 삭제하시겠습니까? (Y/N) : ");
				String respon = sc.next();
				
				if (respon.equals("Y") || respon.equals("y"))
				{
					int result = dao.delete(sid);
					
					if (result > 0)
					{
						System.out.println(sid + "번 학생 정보가 정상적으로 삭제되었습니다.");
						break;
					}
				}
				else
				{
					System.out.println("삭제가 취소되었습니다.");
					break;
				}
				
		
			} while (true);
	
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		finally 
		{
			try
			{
				dao.unconnection();
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
			
		}
	}
}
