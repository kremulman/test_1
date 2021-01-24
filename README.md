# Table scheme
### 이번 테스트에서 사용된 테이블은 RejectedFile entity 로 만들어진 한 개의 테이블을 사용합니다.  ㅇㅇㄹㅇㄴ
### 테이블 컬럼은 id, fileType, fileTypeString, expiredAt 의 항목으로 구성되어있으며
### 입력된 데이터에 대한 삭제는 soft delete 방식을 사용하여 expiredAt 항목의 값의 유무를 통해 삭제여부를 판단합니다.
### 삭제 되었던 확장자에 대해서 다시금 차단을 하기 위해 추가하게 되면 새 데이터가 추가되는 것은 아니며 expiredAt 항목의 값을 null로 변경하여 데이터를 복원하는 방식입니다. 동일한 데이터의 중복생성으로 인한 쓰레기데이터의 생성을 방지하고자 함입니다.

