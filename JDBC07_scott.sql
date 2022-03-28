SELECT USER
FROM DUAL;
--==>>SCOTT


SELECT *
FROM TBL_SCORE;
/*
2	양윤정	50	60	70
3	서민지	60	70	80
4	이시우	70	80	90
*/
DESC TBL_SCORE;
/*
SID  NOT NULL NUMBER       
NAME          VARCHAR2(30) 
KOR           NUMBER(3)    
ENG           NUMBER(3)    
MAT           NUMBER(3) 
*/


--성적 입력
INSERT INTO TBL_SCORE (SID,NAME,KOR,ENG,MAT)
VALUES(SCORESEQ.NEXTVAL,'남주혁',100,100,100);
--> 한 줄 구성
INSERT INTO TBL_SCORE (SID,NAME,KOR,ENG,MAT) VALUES(SCORESEQ.NEXTVAL,'남주혁',100,100,100)
;
--==>>1 행 이(가) 삽입되었습니다.

--확인
SELECT *
FROM TBL_SCORE;
/*
2	양윤정	50	60	70
3	서민지	60	70	80
4	이시우	70	80	90
5	남주혁	100	100	100
*/

--커밋
COMMIT;
--커밋 완료.

--------------------------------------------------------------------------------
--인원수 세기
SELECT COUNT(*) AS COUNT
FROM TBL_SCORE;
-->
SELECT COUNT(*) AS COUNT FROM TBL_SCORE
;
--> 4
--------------------------------------------------------------------------------

--성적 전체 출력
SELECT SID, NAME, KOR, ENG, MAT
      ,(KOR + ENG + MAT) AS TOT
      ,(KOR + ENG + MAT) / 3 AS AVG
      ,RANK() OVER(ORDER BY(KOR + ENG + MAT) DESC) AS RANK
FROM TBL_SCORE;
-->
SELECT SID, NAME, KOR, ENG, MAT ,(KOR + ENG + MAT) AS TOT ,(KOR + ENG + MAT) / 3 AS AVG ,RANK() OVER(ORDER BY(KOR + ENG + MAT) DESC) AS RANK FROM TBL_SCORE
;

--------------------------------------------------------------------------------
-- 이름 검색 출력
SELECT SID, NAME, KOR, ENG, MAT
      ,(KOR + ENG + MAT) AS TOT
      ,(KOR + ENG + MAT) / 3 AS AVG
FROM TBL_SCORE
WHERE NAME = '남주혁';
-->
SELECT SID, NAME, KOR, ENG, MAT ,(KOR + ENG + MAT) AS TOT ,(KOR + ENG + MAT) / 3 AS AVG FROM TBL_SCORE WHERE NAME = '남주혁'
;
--------------------------------------------------------------------------------
--성적 수정
UPDATE TBL_SCORE
SET NAME = '백이진', KOR = 80 , ENG = 80, MAT = 90
WHERE SID = 5;
-->
UPDATE TBL_SCORE SET NAME = '백이진', KOR = 80 , ENG = 80, MAT = 90 WHERE SID = 5
;

--커밋
COMMIT;

--------------------------------------------------------------------------------
--성적 삭제

DELETE
FROM TBL_SCORE
WHERE SID = 2;
-->
DELETE FROM TBL_SCORE WHERE SID = 2
;

COMMIT;




SELECT *
FROM TBL_SCORE;

COMMIT;