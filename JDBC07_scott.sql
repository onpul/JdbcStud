SELECT USER
FROM DUAL;
--==>>SCOTT


SELECT *
FROM TBL_SCORE;
/*
2	������	50	60	70
3	������	60	70	80
4	�̽ÿ�	70	80	90
*/
DESC TBL_SCORE;
/*
SID  NOT NULL NUMBER       
NAME          VARCHAR2(30) 
KOR           NUMBER(3)    
ENG           NUMBER(3)    
MAT           NUMBER(3) 
*/


--���� �Է�
INSERT INTO TBL_SCORE (SID,NAME,KOR,ENG,MAT)
VALUES(SCORESEQ.NEXTVAL,'������',100,100,100);
--> �� �� ����
INSERT INTO TBL_SCORE (SID,NAME,KOR,ENG,MAT) VALUES(SCORESEQ.NEXTVAL,'������',100,100,100)
;
--==>>1 �� ��(��) ���ԵǾ����ϴ�.

--Ȯ��
SELECT *
FROM TBL_SCORE;
/*
2	������	50	60	70
3	������	60	70	80
4	�̽ÿ�	70	80	90
5	������	100	100	100
*/

--Ŀ��
COMMIT;
--Ŀ�� �Ϸ�.

--------------------------------------------------------------------------------
--�ο��� ����
SELECT COUNT(*) AS COUNT
FROM TBL_SCORE;
-->
SELECT COUNT(*) AS COUNT FROM TBL_SCORE
;
--> 4
--------------------------------------------------------------------------------

--���� ��ü ���
SELECT SID, NAME, KOR, ENG, MAT
      ,(KOR + ENG + MAT) AS TOT
      ,(KOR + ENG + MAT) / 3 AS AVG
      ,RANK() OVER(ORDER BY(KOR + ENG + MAT) DESC) AS RANK
FROM TBL_SCORE;
-->
SELECT SID, NAME, KOR, ENG, MAT ,(KOR + ENG + MAT) AS TOT ,(KOR + ENG + MAT) / 3 AS AVG ,RANK() OVER(ORDER BY(KOR + ENG + MAT) DESC) AS RANK FROM TBL_SCORE
;

--------------------------------------------------------------------------------
-- �̸� �˻� ���
SELECT SID, NAME, KOR, ENG, MAT
      ,(KOR + ENG + MAT) AS TOT
      ,(KOR + ENG + MAT) / 3 AS AVG
FROM TBL_SCORE
WHERE NAME = '������';
-->
SELECT SID, NAME, KOR, ENG, MAT ,(KOR + ENG + MAT) AS TOT ,(KOR + ENG + MAT) / 3 AS AVG FROM TBL_SCORE WHERE NAME = '������'
;
--------------------------------------------------------------------------------
--���� ����
UPDATE TBL_SCORE
SET NAME = '������', KOR = 80 , ENG = 80, MAT = 90
WHERE SID = 5;
-->
UPDATE TBL_SCORE SET NAME = '������', KOR = 80 , ENG = 80, MAT = 90 WHERE SID = 5
;

--Ŀ��
COMMIT;

--------------------------------------------------------------------------------
--���� ����

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