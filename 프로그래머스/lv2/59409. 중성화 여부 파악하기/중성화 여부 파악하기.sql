-- 코드를 입력하세요
SELECT animal_id,name, if(SEX_UPON_INTAKE in ('Neutered Male','Spayed Female') ,'O','X') as '중성화'
from animal_ins;
